package com.framework.helix.controller;

import com.framework.helix.bean.ClientView;
import com.framework.helix.bean.LeadsReport;
import com.framework.helix.entity.*;
import com.framework.helix.entity.User;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.*;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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

/**
 * Created by nuwan.n.bandara on 8/13/2014.
 */

@Controller
@RequestMapping("/lead")
    public class LeadController {

    private ClientService clientService;
    private ContactService contactService;
    private AddressService addressService;
    private ClientContactsService clientContactsService;
    private ClientEventsService clientEventsService;
    private UserService userService;
    private ClientStatusService clientStatusService;
    private MasterCampaignService masterCampaignService;
    private EmployeeService employeeService;

    @Autowired
    public void setClientService(ClientService clientService) { this.clientService = clientService; }

    @Autowired
    public void setContactService(ContactService contactService){ this.contactService = contactService; }

    @Autowired
    public void setAddressService(AddressService addressService) { this.addressService = addressService; }

    @Autowired
    public void setClientContactsService(ClientContactsService clientContactsService){
        this.clientContactsService = clientContactsService; }

    @Autowired
    public void setClientEventsService(ClientEventsService clientEventsService){
        this.clientEventsService = clientEventsService; }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService; }

    @Autowired
    public void setClientStatusService(ClientStatusService clientStatusService){
        this.clientStatusService = clientStatusService; }

    @Autowired
    public void setMasterCampaignService(MasterCampaignService masterCampaignService){
        this.masterCampaignService = masterCampaignService; }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService){
        this.employeeService = employeeService; }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView init(ModelMap model) {
        model.addAttribute("Rep", getEmployeeName());
        model.addAttribute("pageHeader" , "Leads");
        return new ModelAndView("leads", "leads", new ClientView());
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public @ResponseBody
    void loadDriveDetails(HttpServletResponse response) {

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildLeadResponseXML().asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadLeadInformation", method = RequestMethod.GET)
    public @ResponseBody void loadLeadInformation(HttpServletResponse response,@RequestParam("leadId") Integer leadId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildLeadInformationResponseXML(leadId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadPhoneInformation", method = RequestMethod.GET)
    public @ResponseBody void loadPhoneInformation(HttpServletResponse response,@RequestParam("leadId") Integer leadId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildPhoneInformationResponseXML(leadId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadMailingAddressInfo", method = RequestMethod.GET)
    public @ResponseBody void loadMailingAddressInfo(HttpServletResponse response,@RequestParam("leadId") Integer leadId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildMailingAddressResponseXML(leadId).asXML());
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


    @RequestMapping(value = "/loadSelectedContact", method = RequestMethod.GET)
    public @ResponseBody void loadSelectedVendorContact(HttpServletResponse response,@RequestParam("rowId") Integer rowId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildSelectedContactResponseXML(rowId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadNotes", method = RequestMethod.GET)
    public @ResponseBody void loadNotes(HttpServletResponse response,@RequestParam("rowId") Integer rowId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildNotesResponseXML(rowId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadProposals", method = RequestMethod.GET)
    public @ResponseBody void loadProposals(HttpServletResponse response,@RequestParam("rowId") Integer rowId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildProposalResponseXML(rowId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadContractInfo", method = RequestMethod.GET)
    public @ResponseBody void loadContractInfo(HttpServletResponse response,@RequestParam("rowId") Integer rowId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildContractResponseXML(rowId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Document buildLeadResponseXML() throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("leads");
        List<Client> clients = clientService.getClients();
        for(Client client : clients) {
            Element element = root.addElement("lead");
            element.addAttribute("leadId", String.valueOf(client.getIdClient()));
            element.addAttribute("name",client.getClientName());
        }
        return document;

    }

    public Document buildLeadInformationResponseXML(Integer leadId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("lead");
        Client client=clientService.getClient(leadId);

            Element element = root.addElement("details");
            element.addAttribute("leadId",String.valueOf(client.getIdClient()));
            element.addAttribute("name", client.getClientName());
            element.addAttribute("acronym", client.getCoClient());
            if (client.getContact()!=null){
            element.addAttribute("website", client.getContact().getWebURL());}
            if (client.getEmployee()!=null){
            element.addAttribute("rep",String.valueOf(client.getEmployee().getIdEmployee()));}
            element.addAttribute("forProfit", "true");
            if (client.getClientstatus()!=null){
            element.addAttribute("status", String.valueOf(client.getClientstatus().getIdClientStatus()));}
            element.addAttribute("jurisdiction", client.getJurisdiction());

            List<MasterCampaign> masterCampaignList =masterCampaignService.getCampaignDetailsCI(leadId);
            if(masterCampaignList !=null) {
                for (MasterCampaign masterCampaign : masterCampaignList) {
                    element.addAttribute("friendsOf", masterCampaign.getFriendsOf());
                    element.addAttribute("campaignId", String.valueOf(masterCampaign.getIdCampaign()));
                }
            }

            element.addAttribute("baseForm", "baseForm1");

        return document;
    }

    public Document buildPhoneInformationResponseXML(Integer leadId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("phone");
        Client client=clientService.getClient(leadId);
        Element element = root.addElement("details");
        if(client.getContact()!=null) {
            Contact contact=contactService.getContact(client.getContact().getIdContact());
            element.addAttribute("main",contact.getMain());
            element.addAttribute("fax", contact.getFax());
            element.addAttribute("dispatch", contact.getDispatch());

        }
        return document;
    }

    public Document buildMailingAddressResponseXML(Integer leadId) throws HelixServiceException {
        Client client=clientService.getClient(leadId);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("address");
        if(client.getAddress()!=null) {
            Address address=addressService.getAddress(client.getAddress().getIdAddress());
            Element element = root.addElement("details");
            element.addAttribute("city", address.getCity());
            element.addAttribute("zip",address.getZipCode());
            element.addAttribute("state", address.getState().getState());
            element.addAttribute("address",address.getStreetName());
        }
        return document;
    }

    public Document buildContactsResponseXML(Integer rowId) throws HelixServiceException {
        List<Clientcontact> clientContactsList=clientContactsService.getClientContacts(rowId);

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("contacts");
        if(clientContactsList!=null) {
            for (Clientcontact clientContact : clientContactsList) {
                Element element = root.addElement("contact");
                element.addAttribute("contactId",String.valueOf(clientContact.getIdClientContact()));
                element.addAttribute("name", clientContact.getFirstName()+" "+clientContact.getLastName());
                element.addAttribute("title", clientContact.getTitle());
                element.addAttribute("phone", clientContact.getContact().getMain());
                element.addAttribute("home",  clientContact.getContact().getHome());
                element.addAttribute("mobile", clientContact.getContact().getMobile());
                element.addAttribute("other", clientContact.getContact().getOther());
                element.addAttribute("fax", clientContact.getContact().getFax());
                element.addAttribute("email",clientContact.getContact().getEmail());
            }
        }
        return document;
    }

    public Document buildSelectedContactResponseXML(Integer rowId) throws HelixServiceException {
        Clientcontact clientContact=clientContactsService.getClientContact(rowId);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("contacts");
        if(clientContact!=null) {
            Element element = root.addElement("contact");
            element.addAttribute("firstName", clientContact.getFirstName());
            element.addAttribute("lastName", clientContact.getFirstName());
            element.addAttribute("title",clientContact.getTitle());
            element.addAttribute("main", clientContact.getContact().getMain());
            element.addAttribute("home", clientContact.getContact().getHome());
            element.addAttribute("mobile", clientContact.getContact().getMobile());
            element.addAttribute("other", clientContact.getContact().getOther());
            element.addAttribute("fax", clientContact.getContact().getFax());
            element.addAttribute("email", clientContact.getContact().getEmail());
            element.addAttribute("alertEmail", clientContact.getContact().getEmailAlt());
            element.addAttribute("note",clientContact.getNotes());
        }

        return document;
    }

    public Document buildNotesResponseXML(Integer rowId) throws HelixServiceException {
        List<Clientevent> clientEventsList=clientEventsService.getClientEvents(rowId);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("notes");
        if(clientEventsList!=null) {
            for (Clientevent clientEvent : clientEventsList) {
                Element element = root.addElement("note");
                element.addAttribute("noteId",String.valueOf(clientEvent.getIdClientEvent()));
                if(clientEvent.getAlarmDate()!=null){
                  element.addAttribute("date", clientEvent.getAlarmDate().toString().substring(0, 10));}
                element.addAttribute("by", clientEvent.getUser().getUserName());
                element.addAttribute("note", clientEvent.getNotes());
                element.addAttribute("alert", String.valueOf(clientEvent.getFlReminder()));
                if(clientEvent.getDateCreated()!=null){
                  element.addAttribute("createDate", clientEvent.getDateCreated().toString().substring(0, 10));}
            }
        }
        return document;
    }


    public Document buildProposalResponseXML(Integer rowId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("proposals");
        Element element = root.addElement("proposal");
        element.addAttribute("proposalId","1");
        element.addAttribute("date", "2014/01/24");
        element.addAttribute("residents", "by");
        element.addAttribute("businesses", "note");
        element.addAttribute("projectedProfit", "1");

        return document;
    }


    public Document buildContractResponseXML(Integer rowId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("contract");
        Element element = root.addElement("details");
        element.addAttribute("contractId","1");
        element.addAttribute("commencementDate", "2014/01/24");
        element.addAttribute("terminationDate", "2014/11/02");
        element.addAttribute("terminationBuffer", "terminationBuffer");
        element.addAttribute("clientPre", "clientPre");
        element.addAttribute("renewPeriod", "1");
        element.addAttribute("charity", "true");
        element.addAttribute("renewType", "1");
        element.addAttribute("telemarketing", "true");

        return document;
    }

    @RequestMapping(value = "/deleteLeadInfo", method = RequestMethod.GET)
    public @ResponseBody void deleteClient(HttpServletResponse response,@RequestParam("rowId") Integer rowId) {

        try {
            Client client=clientService.getClient(rowId);
            clientService.deleteClient(client);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/updateLeadInfo", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String updateLeadInfo(@RequestParam("name") String name,@RequestParam("acronym") String acronym,
                             @RequestParam("website") String website,@RequestParam("rep") Integer rep,
                             @RequestParam("forProfit") String forProfit,
                             @RequestParam("status") Integer status,@RequestParam("jurisdiction") String jurisdiction,
                             @RequestParam("friendsOf") String friendsOf,@RequestParam("baseForm") String baseForm,
                             @RequestParam("campaignId") Integer campaignId,@RequestParam("userName") String userName,
                             @RequestParam("idLead") Integer idLead,HttpServletResponse response) {

        //set encoding explicitly
        response.setCharacterEncoding("UTF-8");
        try {
            User user=userService.getUser(userName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=dateFormat.parse(dateFormat.format(date));

            Clientstatus clientstatus=clientStatusService.getClientStatus(status);
            Employee employee=employeeService.getEmployee(rep);

            if(idLead!=null){
                Client updateClient=clientService.getClient(idLead);

                if(updateClient.getContact()!=null) {
                    Contact contact = updateClient.getContact();
                    contact.setWebURL(website);
                    contact.setIdUserLastUpdated(user);
                    contact.setDateUpdated(currentDate);
                    contactService.updateContact(contact);

                    updateClient.setClientName(name);
                    updateClient.setCoClient(acronym);
                    updateClient.setJurisdiction(jurisdiction);
                    updateClient.setIdUserLastUpdated(user);
                    updateClient.setDateUpdated(currentDate);
                    updateClient.setClientstatus(clientstatus);
                    updateClient.setEmployee(employee);
                    clientService.updateClient(updateClient);
                }
                else{
                    Contact contact=new Contact();
                    contact.setWebURL(website);
                    contact.setUser(user);
                    contact.setDateCreated(currentDate);
                    contactService.saveContact(contact);

                    updateClient.setClientName(name);
                    updateClient.setCoClient(acronym);
                    updateClient.setJurisdiction(jurisdiction);
                    updateClient.setIdUserLastUpdated(user);
                    updateClient.setDateUpdated(currentDate);
                    updateClient.setClientstatus(clientstatus);
                    updateClient.setEmployee(employee);
                    String lastId=contactService.getLastContactId();
                    updateClient.setContact(contactService.getContact(Integer.parseInt(lastId)));
                    clientService.updateClient(updateClient);

                }

                if(campaignId!=null){

                    MasterCampaign masterCampaign =masterCampaignService.getCampaignDetails(campaignId);
                    masterCampaign.setFriendsOf(friendsOf);
                    masterCampaign.setIdUserLastUpdated(user);
                    masterCampaign.setDateUpdated(currentDate);
                    masterCampaignService.updateCampaignDetails(masterCampaign);

                }
                else{
                    Address address = new Address();
                    address.setUser(user);
                    address.setDateCreated(currentDate);
                    addressService.saveAddress(address);

                    Contact contact2 =new Contact();
                    contact2.setUser(user);
                    contact2.setDateCreated(currentDate);
                    contactService.saveContact(contact2);

                    String lastAddressId=addressService.getLastAddressId();
                    String lastContactId=contactService.getLastContactId();

                    MasterCampaign masterCampaign =new MasterCampaign();
                    masterCampaign.setFriendsOf(friendsOf);
                    masterCampaign.setUser(user);
                    masterCampaign.setDateCreated(currentDate);
                    masterCampaign.setClient(clientService.getClient(idLead));
                    masterCampaign.setAddress(addressService.getAddress(Integer.parseInt(lastAddressId)));
                    masterCampaign.setContact(contactService.getContact(Integer.parseInt(lastContactId)));
                    masterCampaignService.saveCampaignDetails(masterCampaign);
                }

                return idLead.toString();
            }
            else{


                Contact contact=new Contact();
                contact.setWebURL(website);
                contact.setUser(user);
                contact.setDateCreated(currentDate);
                contactService.saveContact(contact);

                Client clientObj=new Client();
                clientObj.setClientName(name);
                clientObj.setCoClient(acronym);
                clientObj.setJurisdiction(jurisdiction);
                clientObj.setUser(user);
                clientObj.setDateCreated(currentDate);
                clientObj.setClientstatus(clientstatus);
                clientObj.setEmployee(employee);
                String lastId=contactService.getLastContactId();
                clientObj.setContact(contactService.getContact(Integer.parseInt(lastId)));
                clientService.saveClient(clientObj);


                Address address = new Address();
                address.setUser(user);
                address.setDateCreated(currentDate);
                addressService.saveAddress(address);

                Contact contact2 =new Contact();
                contact2.setUser(user);
                contact2.setDateCreated(currentDate);
                contactService.saveContact(contact2);

                String lastAddressId=addressService.getLastAddressId();
                String lastContactId=contactService.getLastContactId();
                String LastClientId=clientService.getLastClientsId();

                MasterCampaign masterCampaign =new MasterCampaign();
                masterCampaign.setFriendsOf(friendsOf);
                masterCampaign.setUser(user);
                masterCampaign.setDateCreated(currentDate);
                masterCampaign.setClient(clientService.getClient(Integer.parseInt(LastClientId)));
                masterCampaign.setAddress(addressService.getAddress(Integer.parseInt(lastAddressId)));
                masterCampaign.setContact(contactService.getContact(Integer.parseInt(lastContactId)));
                masterCampaignService.saveCampaignDetails(masterCampaign);

                return LastClientId;
            }

        } catch (HelixServiceException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
      }


    @RequestMapping(value = "/updatePhoneInfo", method = RequestMethod.GET)
    public @ResponseBody void updatePhoneInfo(@RequestParam("main") String main,@RequestParam("fax") String fax,
                                                @RequestParam("dispatch") String dispatch,
                                                @RequestParam("userName") String userName,
                                                @RequestParam("idLead") Integer idLead,
                                                HttpServletResponse response) {
        try {
            User user=userService.getUser(userName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=dateFormat.parse(dateFormat.format(date));
            Client client = clientService.getClient(idLead);
            Contact getContactDetails=client.getContact();

            if(getContactDetails==null) {
                Contact contact = new Contact();
                contact.setMain(main);
                contact.setFax(fax);
                contact.setDispatch(dispatch);
                contact.setUser(user);
                contact.setDateCreated(currentDate);
                contactService.saveContact(contact);

                String lastId=contactService.getLastContactId();
                client.setContact(contactService.getContact(Integer.parseInt(lastId)));
                client.setIdUserLastUpdated(user);
                client.setDateUpdated(currentDate);
                clientService.updateClient(client);

            }
            else {
                getContactDetails.setMain(main);
                getContactDetails.setFax(fax);
                getContactDetails.setDispatch(dispatch);
                getContactDetails.setIdUserLastUpdated(user);
                getContactDetails.setDateUpdated(currentDate);
                contactService.updateContact(getContactDetails);
            }
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/updateMailingAddressInfo", method = RequestMethod.GET)
    public @ResponseBody void updateMailingAddressInfo(@RequestParam("address") String street,
                                              @RequestParam("city") String city,
                                              @RequestParam("state") String state,
                                              @RequestParam("zip") String zip,
                                              @RequestParam("userName") String userName,
                                              @RequestParam("idLead") Integer idLead,
                                              HttpServletResponse response) {


        try {
            User user=userService.getUser(userName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=dateFormat.parse(dateFormat.format(date));
            Client client = clientService.getClient(idLead);
            Address getAddressDetails = client.getAddress();
            if(getAddressDetails==null) {
                Address address=new Address();
                address.setCity(city);
                address.setZipCode(zip);
                address.setStreetName(street);
                if (state != null && state !=  "0") {
                    // get state
                    State stateObj = addressService.getState(Integer.parseInt(state));
                    address.setState(stateObj);
                }
                address.setUser(user);
                address.setDateCreated(currentDate);
                addressService.saveAddress(address);

                String lastId=addressService.getLastAddressId();
                client.setAddress(addressService.getAddress(Integer.parseInt(lastId)));
                client.setIdUserLastUpdated(user);
                client.setDateUpdated(currentDate);
                clientService.updateClient(client);
            }
            else {

                getAddressDetails.setCity(city);
                getAddressDetails.setZipCode(zip);
                getAddressDetails.setStreetName(street);
                if (state != null && state !=  "0") {
                    // get state
                    State stateObj = addressService.getState(Integer.parseInt(state));
                    getAddressDetails.setState(stateObj);
                }
                getAddressDetails.setIdUserLastUpdated(user);
                getAddressDetails.setDateUpdated(currentDate);
                addressService.updateAddress(getAddressDetails);
            }
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }


    }


    @RequestMapping(value = "/deleteContactInfo", method = RequestMethod.GET)
    public void deleteContactInfo(@RequestParam("rowId") Integer rowId) {

        try {
            clientContactsService.deleteClientContact(rowId);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }

    }

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
            User user=userService.getUser(userName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=dateFormat.parse(dateFormat.format(date));

            Contact contact=new Contact();
            contact.setMain(CCMain);
            contact.setHome(CCHome);
            contact.setMobile(CCMobile);
            contact.setOther(CCOther);
            contact.setFax(CCFax);
            contact.setEmail(CCEmail);
            contact.setEmailAlt(CCAltEmail);
            contact.setUser(user);
            contact.setDateCreated(currentDate);
            contactService.saveContact(contact);

            String lastContactId=contactService.getLastContactId();

            Clientcontact clientcontact=new Clientcontact();
            clientcontact.setFirstName(firstName);
            clientcontact.setLastName(lastName);
            clientcontact.setTitle(title);
            clientcontact.setNotes(CCNotes);
            clientcontact.setUser(user);
            clientcontact.setDateCreated(currentDate);
            clientcontact.setClient(clientService.getClient(rowID));
            clientcontact.setContact(contactService.getContact(Integer.parseInt(lastContactId)));
            clientContactsService.saveClientContact(clientcontact);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @RequestMapping(value = "/updateContactInfo", method = RequestMethod.GET)
    public @ResponseBody void updateContactInfo(@RequestParam("rowID") Integer rowID,
                                                @RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,
                                                @RequestParam("title") String title,@RequestParam("CCMain") String CCMain,
                                                @RequestParam("CCHome") String CCHome,@RequestParam("CCMobile") String CCMobile,
                                                @RequestParam("CCOther") String CCOther,@RequestParam("CCFax") String CCFax,
                                                @RequestParam("CCEmail") String CCEmail,@RequestParam("CCAltEmail") String CCAltEmail,
                                                @RequestParam("CCNotes") String CCNotes,@RequestParam("userName") String userName,
                                                @RequestParam("contactId") Integer contactId ) {
        try {
            User user=userService.getUser(userName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=dateFormat.parse(dateFormat.format(date));

            Contact contact=clientContactsService.getClientContact(contactId).getContact();
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

            Clientcontact clientContact=clientContactsService.getClientContact(contactId);
            clientContact.setFirstName(firstName);
            clientContact.setLastName(lastName);
            clientContact.setTitle(title);
            clientContact.setNotes(CCNotes);
            clientContact.setIdUserLastUpdated(user);
            clientContact.setDateUpdated(currentDate);
            clientContactsService.updateClientContact(clientContact);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addNewNote", method = RequestMethod.GET)
    public void addNewNote(@RequestParam("dateCreated") String dateCreated,@RequestParam("alarmDate") String alarmDate,
                           @RequestParam("note") String note,@RequestParam("alarm") String alarm,
                           @RequestParam("userName") String userName,
                           @RequestParam("rowId") Integer rowId) {
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateCreatedValue = (dateCreated != null && !dateCreated.equals("")) ? formatter.parse(dateCreated) : null;
            Date alarmDateDateValue = (alarmDate != null && !alarmDate.equals("")) ? formatter.parse(alarmDate) : null;


            Clientevent clientevent=new Clientevent();
            clientevent.setDateCreated(dateCreatedValue);
            clientevent.setAlarmDate(alarmDateDateValue);
            clientevent.setNotes(note);
            clientevent.setFlReminder(Boolean.valueOf(alarm));
            clientevent.setClient(clientService.getClient(rowId));
            clientevent.setUser(userService.getUser(userName));

            clientEventsService.saveClientEvent(clientevent);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/deleteNote", method = RequestMethod.GET)
    public @ResponseBody void deleteNote(@RequestParam("rowId") Integer rowId) {

        try {
            clientEventsService.deleteClientEvent(rowId);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateNote", method = RequestMethod.GET)
    public @ResponseBody void updateNote(@RequestParam("noteId") Integer noteId,@RequestParam("note") String note,
                                         @RequestParam("dateCreated") String dateCreated,@RequestParam("alarmDate") String alarmDate,
                                         @RequestParam("alarm") String alarm,@RequestParam("userName") String userName,
                                         @RequestParam("rowId") Integer rowId) {

        try{
            User user=userService.getUser(userName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=formatter.parse(formatter.format(date));

            Date dateCreatedValue = (dateCreated != null && !dateCreated.equals("")) ? formatter.parse(dateCreated) : null;
            Date alarmDateDateValue = (alarmDate != null && !alarmDate.equals("")) ? formatter.parse(alarmDate) : null;


            Clientevent clientevent=clientEventsService.getClientEvent(noteId);
            clientevent.setDateCreated(dateCreatedValue);
            clientevent.setAlarmDate(alarmDateDateValue);
            clientevent.setNotes(note);
            clientevent.setFlReminder(Boolean.valueOf(alarm));
            clientevent.setClient(clientService.getClient(rowId));
            clientevent.setUser(user);
            clientevent.setIdUserLastUpdated(user);
            clientevent.setDateUpdated(currentDate);
            clientEventsService.updateClientEvent(clientevent);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/addNewProposal", method = RequestMethod.GET)
    public void addNewProposal(@RequestParam("date") String date,@RequestParam("residents") String residents,
                           @RequestParam("businesses") String businesses,
                           @RequestParam("projectedProfit") String projectedProfit,
                           @RequestParam("userName") String userName,
                           @RequestParam("rowId") Integer rowId) {
        System.out.println(rowId);

    }

    @RequestMapping(value = "/deleteProposal", method = RequestMethod.GET)
    public @ResponseBody void deleteProposal(@RequestParam("rowId") Integer rowId) {

        System.out.println(rowId);
    }

    @RequestMapping(value = "/updateProposal", method = RequestMethod.GET)
    public @ResponseBody void updateProposal(@RequestParam("proposalId") Integer proposalId,@RequestParam("date") String date,
                                 @RequestParam("residents") String residents,@RequestParam("businesses") String businesses,
                                 @RequestParam("projectedProfit") String projectedProfit,
                                 @RequestParam("userName") String userName,
                                 @RequestParam("rowId") Integer rowId) {

        System.out.println(rowId);

    }

    @RequestMapping(value = "/updateContractInfo", method = RequestMethod.GET)
    public @ResponseBody void updateContractInfo(@RequestParam("commencementDate") String commencementDate,
                           @RequestParam("terminationDate") String terminationDate,
                           @RequestParam("terminationBuffer") String terminationBuffer,
                           @RequestParam("clientPre") String clientPre,@RequestParam("renewPeriod") String renewPeriod,
                           @RequestParam("charity") String charity,@RequestParam("renewType") String renewType,
                           @RequestParam("telemarketing") String telemarketing,@RequestParam("userName") String userName,
                           @RequestParam("idLead") Integer idLead, HttpServletResponse response) {


        System.out.println(idLead);

    }

    private Map<Integer, String> getEmployeeName() {
        Map<Integer, String> Rep = new LinkedHashMap<Integer, String>();

        try {
            List<Employee> employeeList=employeeService.getEmployees();
            if(employeeList!=null) {
                for (Employee employee : employeeList) {
                    Rep.put(employee.getIdEmployee(), employee.getFirstName());
                }
            }
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        return Rep;
    }

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public ModelAndView  print(ModelAndView modelAndView) {

        List<LeadsReport> leadsReportList = new ArrayList<LeadsReport>();
        List<Client> clientList= null;
        try {
            clientList = clientService.getClients();
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }

        for(Client client: clientList){
            LeadsReport leadsReport =new LeadsReport();
            leadsReport.setName(client.getClientName());
            if (client.getClientstatus()!=null){
            leadsReport.setStatus(client.getClientstatus().getStatus());}
            leadsReport.setAcronym(client.getCoClient());
            if (client.getContact()!=null){
            leadsReport.setWebsite(client.getContact().getWebURL());}
            leadsReportList.add(leadsReport);
        }

        JRDataSource ds = new JRBeanCollectionDataSource(leadsReportList);


        // In order to use Spring's built-in Jasper support,
        // We are required to pass our datasource as a map parameter
        // parameterMap is the Model of our application
        Map<String,Object> parameterMap = new HashMap<String,Object>();
        parameterMap.put("datasource", ds);

        // pdfReport is the View of our application
        // This is declared inside the /WEB-INF/jasper-views.xml
        modelAndView = new ModelAndView("pdfReportLeads", parameterMap);
        // Return the View and the Model combined
        return modelAndView;
    }
}
