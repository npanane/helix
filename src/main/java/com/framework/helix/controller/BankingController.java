package com.framework.helix.controller;

import com.framework.helix.bean.AddressBookReport;
import com.framework.helix.entity.*;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.AddressService;
import com.framework.helix.service.ContactService;
import com.framework.helix.service.PersonalContactService;
import com.framework.helix.service.UserService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by nuwan.n.bandara on 6/3/2014.
 */
@Controller
@RequestMapping("/banking")
public class BankingController {

    private AddressService addressService;
    private UserService userService;
    private PersonalContactService personalContactService;
    private ContactService contactService;

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPersonalContactService(PersonalContactService personalContactService) {
        this.personalContactService = personalContactService;
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView init(ModelMap model) {
        model.addAttribute("pageHeader" , "Banking");
        return new ModelAndView("banking");
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public @ResponseBody void loadAddresses(HttpServletResponse response) {

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildAddressResponseXML().asXML());
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadAddressDetails", method = RequestMethod.GET)
    public @ResponseBody void loadAddressDetails(HttpServletResponse response, @RequestParam("personalContactId") Integer personalContactId) {

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildAddressDetailsResponseXML(personalContactId).asXML());
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadContactDetails", method = RequestMethod.GET)
    public @ResponseBody void loadContactDetails(HttpServletResponse response, @RequestParam("personalContactId") Integer personalContactId) {

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildContactDetailsResponseXML(personalContactId).asXML());
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadNoteDetails", method = RequestMethod.GET)
    public @ResponseBody void loadNoteDetails(HttpServletResponse response, @RequestParam("personalContactId") Integer personalContactId) {

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildNoteDetailsResponseXML(personalContactId).asXML());
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Document buildAddressResponseXML() throws HelixServiceException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("personalContacts");
        List<Personalcontact> personalContacts = personalContactService.getPersonalContact();
        if (personalContacts != null) {
            for (Personalcontact personalContact : personalContacts) {
                Element element = root.addElement("personalContact");
                element.addAttribute("personalContactId", String.valueOf(personalContact.getIdPersonalcontact()));
                element.addAttribute("name", personalContact.getContactPerson());
            }
        }
        return document;
    }

    public Document buildAddressDetailsResponseXML(Integer personalContactId) throws HelixServiceException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("personalContact");
        Personalcontact personalContact = personalContactService.getPersonalContact(personalContactId);
        Element element = root.addElement("details");
        element.addAttribute("personalContactId", String.valueOf(personalContact.getIdPersonalcontact()));
        element.addAttribute("name", personalContact.getBusinessName());
        element.addAttribute("contactPerson", personalContact.getContactPerson());
        if (personalContact.getAddress() != null) {
            element.addAttribute("address1", personalContact.getAddress().getStreetName());
            element.addAttribute("address2", personalContact.getAddress().getAptUnitSuit());
            element.addAttribute("city", personalContact.getAddress().getCity());
            element.addAttribute("state", personalContact.getAddress().getState().getState());
            element.addAttribute("zip", personalContact.getAddress().getZipCode());
        }
        if (personalContact.getContact() != null) {
            element.addAttribute("website", personalContact.getContact().getWebURL());
            element.addAttribute("email", personalContact.getContact().getEmail());

        }
        return document;
    }

    public Document buildContactDetailsResponseXML(Integer personalContactId) throws HelixServiceException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("personalContact");
        Personalcontact personalContact = personalContactService.getPersonalContact(personalContactId);

        Element element = root.addElement("details");
        element.addAttribute("personalContactId", String.valueOf(personalContact.getIdPersonalcontact()));
        if (personalContact.getContact() != null) {
            element.addAttribute("cell", personalContact.getContact().getMobile());
            element.addAttribute("home", personalContact.getContact().getHome());
            element.addAttribute("fax", personalContact.getContact().getFax());
        }

        return document;
    }

    public Document buildNoteDetailsResponseXML(Integer personalContactId) throws HelixServiceException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("note");
        Personalcontact personalContact = personalContactService.getPersonalContact(personalContactId);
        Element element = root.addElement("details");
        element.addAttribute("note", personalContact.getNotes());
        return document;
    }

    @RequestMapping(value = "/deleteAddress", method = RequestMethod.GET)
    public
    @ResponseBody
    void deleteAddress(@RequestParam("rowId") Integer rowId) {
        try {
            Personalcontact personalContact = personalContactService.getPersonalContact(rowId);
            personalContactService.deletePersonalContact(personalContact);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateAddress", method = RequestMethod.GET)
    public
    @ResponseBody
    String updateAddress(@RequestParam("name") String name,@RequestParam("contactPerson") String contactPerson,
                         @RequestParam("address2") String address2,@RequestParam("address1") String address1,
                         @RequestParam("email") String email, @RequestParam("city") String city,
                         @RequestParam("state") String state, @RequestParam("zip") String zip,
                         @RequestParam("website") String website, @RequestParam("userName") String userName,
                         @RequestParam("rowId") Integer rowId, HttpServletResponse response) {

        //set encoding explicitly
        response.setCharacterEncoding("UTF-8");
        try {
            User user = userService.getUser(userName);
            Employee employee= user.getEmployee();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate = dateFormat.parse(dateFormat.format(date));
            if (rowId == null) {
                Address address = new Address();
                address.setStreetName(address1);
                address.setAptUnitSuit(address2);
                address.setCity(city);
                if (state != null && state !=  "0") {
                    // get state
                    State stateObj = addressService.getState(Integer.parseInt(state));
                    address.setState(stateObj);
                }
                address.setZipCode(zip);
                address.setUser(user);
                address.setDateCreated(currentDate);
                addressService.saveAddress(address);

                Contact contact = new Contact();
                contact.setWebURL(website);
                contact.setEmail(email);
                contact.setUser(user);
                contact.setDateCreated(currentDate);
                contactService.saveContact(contact);

                Personalcontact personalContact = new Personalcontact();
                personalContact.setBusinessName(name);
                personalContact.setContactPerson(contactPerson);
                personalContact.setDateCreated(currentDate);
                personalContact.setUser(user);
                personalContact.setEmployee(employee);
                String addressId = addressService.getLastAddressId();
                String contactId = contactService.getLastContactId();
                personalContact.setAddress(addressService.getAddress(Integer.parseInt(addressId)));
                personalContact.setContact(contactService.getContact(Integer.parseInt(contactId)));
                personalContactService.savePersonalContact(personalContact);

                String newPersonalContactId = personalContactService.getLastPersonalContactId();
                return newPersonalContactId;
            } else {
                Personalcontact personalContact = personalContactService.getPersonalContact(rowId);
                personalContact.setBusinessName(name);
                personalContact.setContactPerson(contactPerson);
                personalContact.setIdUserLastUpdated(user);
                personalContact.setDateUpdated(currentDate);
                personalContact.setEmployee(employee);
                personalContactService.updatePersonalContact(personalContact);

                Address address = personalContact.getAddress();
                address.setStreetName(address1);
                address.setAptUnitSuit(address2);
                address.setCity(city);
                if (state != null && state !=  "0") {
                    // get state
                    State stateObj = addressService.getState(Integer.parseInt(state));
                    address.setState(stateObj);
                }
                address.setZipCode(zip);
                address.setIdUserLastUpdated(user);
                address.setDateUpdated(currentDate);
                addressService.updateAddress(address);

                Contact contact = personalContact.getContact();
                contact.setWebURL(website);
                contact.setEmail(email);
                contact.setIdUserLastUpdated(user);
                contact.setDateUpdated(currentDate);
                contactService.updateContact(contact);
                return rowId.toString();
            }
        } catch (HelixServiceException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    @RequestMapping(value = "/savePhoneInfo", method = RequestMethod.GET)
    public void savePhoneInfo(@RequestParam("cell") String cell, @RequestParam("home") String home,
                              @RequestParam("fax") String fax, @RequestParam("rowId") Integer rowId,
                              @RequestParam("userName") String userName) {
        try {
            User user = userService.getUser(userName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate = dateFormat.parse(dateFormat.format(date));

            Personalcontact personalContact = personalContactService.getPersonalContact(rowId);
            Contact contact = personalContact.getContact();
            contact.setMobile(cell);
            contact.setHome(home);
            contact.setFax(fax);
            contact.setIdUserLastUpdated(user);
            contact.setDateUpdated(currentDate);
            contactService.updateContact(contact);

        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/saveNoteInfo", method = RequestMethod.GET)
    public void saveNoteInfo( @RequestParam("note") String note, @RequestParam("rowId") Integer rowId,
                              @RequestParam("userName") String userName) {
        try {
            User user = userService.getUser(userName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate = dateFormat.parse(dateFormat.format(date));

            Personalcontact personalContact = personalContactService.getPersonalContact(rowId);
            personalContact.setNotes(note);
            personalContact.setIdUserLastUpdated(user);
            personalContact.setDateUpdated(currentDate);
            personalContactService.updatePersonalContact(personalContact);

        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public ModelAndView  print(ModelAndView modelAndView) {

        List<AddressBookReport> addressBookReportList = new ArrayList<AddressBookReport>();
        List<Personalcontact> personalContactList= null;
        try {
            personalContactList = personalContactService.getPersonalContact();
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }

        for(Personalcontact personalcontact: personalContactList){
            AddressBookReport addressBookReport =new AddressBookReport();
            addressBookReport.setName(personalcontact.getBusinessName());
            addressBookReport.setContactPerson(personalcontact.getContactPerson());
            addressBookReport.setWebsite(personalcontact.getContact().getWebURL());
            addressBookReport.setEmail(personalcontact.getContact().getEmail());
            addressBookReportList.add(addressBookReport);
        }

        JRDataSource ds = new JRBeanCollectionDataSource(addressBookReportList);


        // In order to use Spring's built-in Jasper support,
        // We are required to pass our datasource as a map parameter
        // parameterMap is the Model of our application
        Map<String,Object> parameterMap = new HashMap<String,Object>();
        parameterMap.put("datasource", ds);

        // pdfReport is the View of our application
        // This is declared inside the /WEB-INF/jasper-views.xml
        modelAndView = new ModelAndView("pdfReportAddressBook", parameterMap);
        // Return the View and the Model combined
        return modelAndView;
    }




}