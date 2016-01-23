package com.framework.helix.controller;


import com.framework.helix.bean.ContactView;
import com.framework.helix.bean.VendorReport;
import com.framework.helix.dao.ContactDao;
import com.framework.helix.entity.*;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.*;


import com.framework.helix.util.DateUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/vendor")
public class VendorController {

    private static Logger logger = Logger.getLogger(VendorController.class);

    private VendorService vendorService;
    private ContactService contactService;
    private AddressService addressService;
    private VendorTypesService vendorTypesService;
    private VendorContactService vendorContactService;
    private UserService userService;

    @Autowired
    public void setVendorService(VendorService vendorService) { this.vendorService = vendorService; }

    @Autowired
    public void setContactService(ContactService contactService){ this.contactService = contactService; }

    @Autowired
    public void setAddressService(AddressService addressService) { this.addressService = addressService; }

    @Autowired
    public void setVendorTypesService(VendorTypesService vendorTypesService) {
        this.vendorTypesService = vendorTypesService; }

    @Autowired
    public void setVendorContactService(VendorContactService vendorContactService) {
        this.vendorContactService = vendorContactService; }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView init(ModelMap model) {
        model.addAttribute("pageHeader" , "Vendors");
        model.addAttribute("contactTypes", getContactType());
        model.addAttribute("stateDdl", getStates());
        return new ModelAndView("vendor", "vendor", new ContactView());
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public @ResponseBody void loadVendors(HttpServletResponse response) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
       try {
            response.getWriter().write(buildVendorResponseXML().asXML());
       }
       catch (HelixServiceException e) {
            e.printStackTrace();
       }
       catch (IOException e) {
            e.printStackTrace();
       }
    }

    @RequestMapping(value = "/loadVendorDetails", method = RequestMethod.GET)
    public @ResponseBody void loadVendorDetails(HttpServletResponse response,@RequestParam("vendorId") Integer vendorId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildVendorDetailsResponseXML(vendorId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadVendorPhoneDetails", method = RequestMethod.GET)
    public @ResponseBody void loadVendorPhoneDetails(HttpServletResponse response,@RequestParam("vendorId") Integer vendorId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildVendorPhoneDetailsResponseXML(vendorId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadContacts", method = RequestMethod.GET)
    public @ResponseBody void loadContacts(HttpServletResponse response,@RequestParam("rowId") Integer rowId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildContactsResponseXML(rowId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadSelectedVendorContact", method = RequestMethod.GET)
    public @ResponseBody void loadSelectedVendorContact(HttpServletResponse response,@RequestParam("rowId") Integer rowId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildSelectedVendorContactResponseXML(rowId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This...
     * @param rowId
     * @return void
     * @throws com.framework.helix.exception.HelixServiceException
     */
    @RequestMapping(value = "/deleteVendor", method = RequestMethod.GET)
    public @ResponseBody void deleteVendor(@RequestParam("rowId") Integer rowId) {

        try {
            Vendor vendor=vendorService.getVendor(rowId);
            vendorService.deleteVendor(vendor);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
    }

    /**
     * This...
     * @param name,accNumber,address1,address2,type,city,state,zip,website,vendorId
     * @return String
     */
    @RequestMapping(value = "/updateVendor", method = RequestMethod.GET)
    public @ResponseBody String updateVendor(@RequestParam("name") String name,@RequestParam("accNumber") String accNumber,
            @RequestParam("address1") String address1,@RequestParam("address2") String address2,
            @RequestParam("type") String type,@RequestParam("city") String city,
            @RequestParam("state") String state,@RequestParam("zip") String zip,
            @RequestParam("website") String website,@RequestParam("vendorId") Integer vendorId,
            @RequestParam("userName") String userName) {
        try {
            User user=userService.getUser(userName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=dateFormat.parse(dateFormat.format(date));
            if(vendorId==null) {

                Contact contact = new Contact();
                contact.setWebURL(website);
                contact.setUser(user);
                contact.setDateCreated(currentDate);
                contactService.saveContact(contact);

                Address address=new Address();
                address.setStreetName(address2);
                address.setCity(city);
                if (state != null && state !=  "0") {
                    // get state
                    State stateObj = addressService.getState(Integer.parseInt(state));
                    address.setState(stateObj);
                }
                address.setZipCode(zip);
                address.setUser(user);
                address.setDateCreated(currentDate);
                address.setFlStatus(true);
                addressService.saveAddress(address);

                Vendortype vendortype=new Vendortype();
                vendortype.setVendorType(type);
                vendortype.setUser(user);
                vendortype.setDateCreated(currentDate);
                vendorTypesService.saveVendorTypes(vendortype);

                String lastContactId=contactService.getLastContactId();
                String lastAddressId=addressService.getLastAddressId();
                String lastVendorTypeId=vendorTypesService.getLastVendorTypeId();

                Vendor vendor=new Vendor();
                vendor.setVendorName(name);
                vendor.setAccountNo(accNumber);
                vendor.setUser(user);
                vendor.setDateCreated(currentDate);
                vendor.setContact(contactService.getContact(Integer.parseInt(lastContactId)));
                vendor.setAddress(addressService.getAddress(Integer.parseInt(lastAddressId)));
                vendor.setVendortype(vendorTypesService.getVendorTypes(Integer.parseInt(lastVendorTypeId)));
                vendorService.saveVendor(vendor);

                String lastVendorId=vendorService.getLastVendorId();
                return lastVendorId;
            }
            else {
                Vendor vendor = vendorService.getVendor(vendorId);

                Contact contact=vendor.getContact();
                contact.setWebURL(website);
                contact.setIdUserLastUpdated(user);
                contact.setDateUpdated(currentDate);
                contactService.updateContact(contact);
                Address address = vendor.getAddress();
                if (address == null) {
                    address = new Address();
                    address.setFlStatus(true);
                    address.setUser(user);
                    address.setDateCreated(currentDate);
                }
                address.setStreetName(address2);
                address.setCity(city);
                if (state != null && state !=  "0") {
                    // get state
                    State stateObj = addressService.getState(Integer.parseInt(state));
                    address.setState(stateObj);
                }
                address.setZipCode(zip);
                address.setIdUserLastUpdated(user);
                address.setDateUpdated(currentDate);
                addressService.saveOrUpdateAddress(address);

                Vendortype vendorType=vendor.getVendortype();
                vendorType.setVendorType(type);
                vendorType.setIdUserLastUpdated(user);
                vendorType.setDateUpdated(currentDate);
                vendorTypesService.updateVendorTypes(vendorType);

                vendor.setAddress(address);
                vendor.setVendorName(name);
                vendor.setAccountNo(accNumber);
                vendor.setIdUserLastUpdated(user);
                vendor.setDateUpdated(currentDate);
                vendorService.updateVendor(vendor);

                return vendorId.toString();
            }
        } catch (HelixServiceException e) {
            e.printStackTrace();
                return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;

        }

    }

    /**
     * This...
     * @param mainPhone,fax,vendorId
     * @return void
     */
    @RequestMapping(value = "/savePhoneInfo", method = RequestMethod.GET)
    public void savePhoneInfo(@RequestParam("mainPhone") String mainPhone, @RequestParam("fax") String fax,
                              @RequestParam("vendorId") Integer vendorId,@RequestParam("userName") String userName) {
      try {
          User user=userService.getUser(userName);
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          Date date = new Date();
          Date currentDate=dateFormat.parse(dateFormat.format(date));

          Contact contact=vendorService.getVendor(vendorId).getContact();
          contact.setMain(mainPhone);
          contact.setFax(fax);
          contact.setIdUserLastUpdated(user);
          contact.setDateUpdated(currentDate);
          contactService.updateContact(contact);
      }
      catch (HelixServiceException e) {
          e.printStackTrace();
      }
      catch (Exception e){
          e.printStackTrace();
      }
    }

    /**
     * This...
     * @param
     * @return void
     */
    @RequestMapping(value = "/addNewContactInfo", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void addNewContactInfo(@RequestParam("rowID") Integer rowID,
            @RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,
            @RequestParam("title") String title,@RequestParam("CCMain") String CCMain,
            @RequestParam("CCHome") String CCHome,@RequestParam("CCMobile") String CCMobile,
            @RequestParam("CCOther") String CCOther,@RequestParam("CCFax") String CCFax,
            @RequestParam("CCAltEmail") String CCAltEmail,@RequestParam("CCEmail") String CCEmail,
            @RequestParam("userName") String userName,@RequestParam("CCNotes") String CCNotes) {
        try {
            User user = userService.getUser(userName);
            Date date = new Date();
            Date currentDate = DateUtil.getSimpleDateFormat().parse(DateUtil.getSimpleDateFormat().format(date));
            Contact contact = new Contact();
            VendorContact vendorContact = new VendorContact();
            // contact
            contact.setMain(CCMain);
            contact.setHome(CCHome);
            contact.setMobile(CCMobile);
            contact.setOther(CCOther);
            contact.setFax(CCFax);
            contact.setEmail(CCEmail);
            contact.setEmailAlt(CCAltEmail);
            contact.setUser(user);
            contact.setDateCreated(currentDate);
            // vendor contact
            vendorContact.setVendor(vendorService.getVendor(rowID));
            vendorContact.setFirstName(firstName);
            vendorContact.setLastName(lastName);
            vendorContact.setTitle(title);
            vendorContact.setNotes(CCNotes);
            vendorContact.setUser(user);
            vendorContact.setDateCreated(currentDate);
            vendorContact.setContact(contact);
            List<VendorContact> vendorContactList = new ArrayList<VendorContact>();
            vendorContactList.add(vendorContact);
            contact.setVendorContacts(vendorContactList);
            contactService.saveOrUpdateContact(contact);
        } catch (Exception e) {
            logger.error(e);
        }
    }
    /**
     * This...
     * @param rowId
     * @return void
     */
    @RequestMapping(value = "/deleteContactInfo", method = RequestMethod.GET)
    public @ResponseBody void deleteContactInfo(@RequestParam("rowId") Integer rowId) {
        try {
            vendorContactService.deleteVendorContact(rowId);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }

    }

    /**
     * This...
     * @param
     * @return void
     */
    @RequestMapping(value = "/updateContactInfo", method = RequestMethod.GET)
    public @ResponseBody void updateContactInfo(@RequestParam("rowID") Integer rowID,
                @RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,
                @RequestParam("title") String title,@RequestParam("CCMain") String CCMain,
                @RequestParam("CCHome") String CCHome,@RequestParam("CCMobile") String CCMobile,
                @RequestParam("CCOther") String CCOther,@RequestParam("CCFax") String CCFax,
                @RequestParam("CCEmail") String CCEmail,@RequestParam("CCAltEmail") String CCAltEmail,
                @RequestParam("CCNotes") String CCNotes,@RequestParam("userName") String userName,
                @RequestParam("vendorContactId") Integer vendorContactId ) {
        try {
            User user = userService.getUser(userName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=dateFormat.parse(dateFormat.format(date));

            Contact contact=vendorContactService.getVendorContact(vendorContactId).getContact();
            contact.setMain(CCMain);
            contact.setHome(CCHome);
            contact.setMobile(CCMobile);
            contact.setOther(CCOther);
            contact.setFax(CCFax);
            contact.setEmail(CCEmail);
            contact.setEmailAlt(CCAltEmail);
            contact.setIdUserLastUpdated(user);
            contact.setDateUpdated(currentDate);
            contactService.updateContact(contact);

            VendorContact vendorContact =vendorContactService.getVendorContact(vendorContactId);
            vendorContact.setFirstName(firstName);
            vendorContact.setLastName(lastName);
            vendorContact.setTitle(title);
            vendorContact.setNotes(CCNotes);
            vendorContact.setIdUserLastUpdated(user);
            vendorContact.setDateUpdated(currentDate);
            vendorContactService.updateVendorContact(vendorContact);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Map<Integer, String> getContactType() {
        Map<Integer, String> contactTypes = new LinkedHashMap<Integer, String>();
        contactTypes.put(1, "General");
        contactTypes.put(2, "Vendor");
        return contactTypes;
    }

    /**
     * This...
     * @param
     * @return Document
     */
    public Document buildVendorResponseXML() throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("vendors");
        List<Vendor> vendors = vendorService.getVendors();
        for(Vendor vendor : vendors) {
            Element element = root.addElement("vendor");
            element.addAttribute("vendorId", String.valueOf(vendor.getIdVendor()));
            element.addAttribute("vendorName", vendor.getVendorName());
      }
        return document;
    }

    /**
     * This...
     * @param vendorId
     * @return Document
     */
    public Document buildVendorDetailsResponseXML(Integer vendorId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("vendor");
        Vendor vendor=vendorService.getVendor(vendorId);
        Element element = root.addElement("details");
        element.addAttribute("idVendor",vendor.getIdVendor().toString());
        element.addAttribute("vendorName",vendor.getVendorName());
        element.addAttribute("accountNo",vendor.getAccountNo());
        element.addAttribute("address1","sdsdsd");
        element.addAttribute("type", ((vendor.getVendortype() != null)
                ? vendor.getVendortype().getVendorType() : ""));
        if (vendor.getAddress() != null) {
            element.addAttribute("address2", vendor.getAddress().getStreetName());
            element.addAttribute("city", vendor.getAddress().getCity());
            element.addAttribute("state", ((vendor.getAddress().getState() != null)
                    ? String.valueOf(vendor.getAddress().getState().getIdState()) : "0"));
            element.addAttribute("zip", vendor.getAddress().getZipCode());
        }
        element.addAttribute("website",vendor.getContact().getWebURL());
        return document;
    }

    /**
     * This...
     * @param vendorId
     * @return Document
     */
    public Document buildVendorPhoneDetailsResponseXML(Integer vendorId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("vendor");
        Vendor vendor=vendorService.getVendor(vendorId);
        Element element = root.addElement("phoneDetails");
        element.addAttribute("idVendor",vendor.getIdVendor().toString());
        element.addAttribute("mainPhone",vendor.getContact().getMain());
        element.addAttribute("fax",vendor.getContact().getFax());
        return document;
    }


    /**
     * This...
     * @param rowId
     * @return Document
     */
    public Document buildContactsResponseXML(Integer rowId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("contacts");
        List<VendorContact> vendorContactList=vendorContactService.getVendorContacts(rowId);
        if(vendorContactList!=null) {
            for (VendorContact vendorContact : vendorContactList) {
                Element element = root.addElement("contact");
                element.addAttribute("clientId", vendorContact.getIdVendorContact().toString());
                element.addAttribute("name", vendorContact.getFirstName() + " " + vendorContact.getLastName());
                element.addAttribute("title", vendorContact.getTitle());
                if (vendorContact.getContact() != null) {
                    element.addAttribute("phone", vendorContact.getContact().getMain());
                    element.addAttribute("home", vendorContact.getContact().getHome());
                    element.addAttribute("mobile", vendorContact.getContact().getMobile());
                    element.addAttribute("other", vendorContact.getContact().getOther());
                    element.addAttribute("fax", vendorContact.getContact().getFax());
                    element.addAttribute("email", vendorContact.getContact().getEmail());
                }
            }
        }
        return document;
    }

    public Document buildSelectedVendorContactResponseXML(Integer rowId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("vendorContacts");
        VendorContact vendorContact=vendorContactService.getVendorContact(rowId);
        if(vendorContact!=null) {
            Element element = root.addElement("contact");
            element.addAttribute("firstName",vendorContact.getFirstName());
            element.addAttribute("lastName",vendorContact.getLastName());
            element.addAttribute("title", vendorContact.getTitle());
            element.addAttribute("main",vendorContact.getContact().getMain());
            element.addAttribute("home",vendorContact.getContact().getHome());
            element.addAttribute("mobile", vendorContact.getContact().getMobile());
            element.addAttribute("other", vendorContact.getContact().getOther());
            element.addAttribute("fax",vendorContact.getContact().getFax());
            element.addAttribute("email",vendorContact.getContact().getEmail());
            element.addAttribute("alertEmail",vendorContact.getContact().getEmailAlt());
            element.addAttribute("note",vendorContact.getNotes());

        }
        return document;
    }

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public ModelAndView  print(ModelAndView modelAndView) {

        List<VendorReport> vendorReportList = new ArrayList<VendorReport>();
        List<Vendor> vendorList= null;
        try {
            vendorList = vendorService.getVendors();
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }

        for(Vendor vendor: vendorList){
            VendorReport vendorReport =new VendorReport();
            vendorReport.setVendorId(vendor.getIdVendor());
            vendorReport.setVendorName(vendor.getVendorName());
            vendorReport.setVendorType(vendor.getVendortype().getVendorType());
            vendorReport.setWebsite(vendor.getContact().getWebURL());
            vendorReportList.add(vendorReport);
        }

        JRDataSource ds = new JRBeanCollectionDataSource(vendorReportList);


        // In order to use Spring's built-in Jasper support,
        // We are required to pass our datasource as a map parameter
        // parameterMap is the Model of our application
        Map<String,Object> parameterMap = new HashMap<String,Object>();
        parameterMap.put("datasource", ds);

        // pdfReport is the View of our application
        // This is declared inside the /WEB-INF/jasper-views.xml
        modelAndView = new ModelAndView("pdfReportVendor", parameterMap);
        // Return the View and the Model combined
        return modelAndView;
    }

    private Map<Integer, String> getStates() {
        Map<Integer, String> stateDdl = new LinkedHashMap<Integer, String>();
        try {
            List<State> states = addressService.getStates() ;
            stateDdl.put(0, "-- State --");
            if (states != null) {
                for (State state : states) {
                    stateDdl.put(state.getIdState(), state.getState());
                }
            }
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        return stateDdl;
    }

}
