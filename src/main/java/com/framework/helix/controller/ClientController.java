package com.framework.helix.controller;

import com.framework.helix.bean.ClientReport;
import com.framework.helix.bean.ClientView;
import com.framework.helix.entity.*;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.*;
import com.framework.helix.service.ArithmeticCalculator;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/client")
public class ClientController {

    private static final String dfs = "yyyy-MM-dd";
    private static DateFormat df = new SimpleDateFormat(dfs);

    private ClientService clientService;
    private AddressService addressService;
    private ContactService contactService;
    private MasterCampaignService masterCampaignService;
    private PostOfficeService postOfficeService;
    private PickUpMethodsService pickUpMethodsService;
    private NoticeOfIntentService noticeOfIntentService;
    private RenewTypesService renewTypesService;
    private RenewPeriodsService renewPeriodsService;
    private ClientContactsService clientContactsService;
    private InstructionsService instructionsService;
    private ClientEventsService clientEventsService;
    private UserService userService;
    private ClientUpLoadsService clientUpLoadsService;
    private ClientStatusService clientStatusService;

    private static final Logger logger = Logger.getLogger(ClientController.class);
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setPostOfficeService(PostOfficeService postOfficeService) {
        this.postOfficeService = postOfficeService;
    }

    @Autowired
    public void setMasterCampaignService(PickUpMethodsService pickUpMethodsService) {
        this.pickUpMethodsService = pickUpMethodsService;
    }

    @Autowired
    public void setPickUpMethodsService(MasterCampaignService masterCampaignService) {
        this.masterCampaignService = masterCampaignService;
    }

    @Autowired
    public void setNoticeOfIntentService(NoticeOfIntentService noticeOfIntentService) {
        this.noticeOfIntentService = noticeOfIntentService;
    }

    @Autowired
    public void setRenewTypesService(RenewTypesService renewTypesService) {
        this.renewTypesService = renewTypesService;
    }

    @Autowired
    public void setRenewPeriodsService(RenewPeriodsService renewPeriodsService) {
        this.renewPeriodsService = renewPeriodsService;
    }

    @Autowired
    public void setClientContactsService(ClientContactsService clientContactsService) {
        this.clientContactsService = clientContactsService;
    }

    @Autowired
    public void setInstructionsService(InstructionsService instructionsService) {
        this.instructionsService = instructionsService;
    }

    @Autowired
    public void setClientEventsService(ClientEventsService clientEventsService) {
        this.clientEventsService = clientEventsService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setClientUpLoadsService(ClientUpLoadsService clientUpLoadsService) {
        this.clientUpLoadsService = clientUpLoadsService;
    }

    @Autowired
    public void set(ClientStatusService clientStatusService) {
        this.clientStatusService = clientStatusService;
    }

    private ArithmeticCalculator arithmeticCalculator;

    @Autowired
    public void setArithmeticCalculator(ArithmeticCalculator arithmeticCalculator) {
        this.arithmeticCalculator = arithmeticCalculator;
    }

    @Autowired
    ServletContext servletContext;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView init(ModelMap model) {
        model.addAttribute("clientTypeDdl", getClientTypes());
        model.addAttribute("seasons", getSeasons());
        model.addAttribute("campaignContacts", getCampaignContacts());
        model.addAttribute("pageHeader" , "Clients");
        model.addAttribute("stateDdl", getStates());
        model.addAttribute("mailingAddStateDdl", getStates());
        model.addAttribute("poStateDdl", getStates());
        model.addAttribute("masterCampaignStateDdl", getStates());
        model.addAttribute("renewTypeDdl", getRenewType());
        return new ModelAndView("client", "client", new ClientView());
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public @ResponseBody void loadClients(HttpServletResponse response) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(clientService.buildClientResponseXML().asXML());
        }
        catch (HelixServiceException e) {
            System.out.print(e.getStackTrace());
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.print(e.getStackTrace());
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadClientContacts", method = RequestMethod.GET)
    public @ResponseBody void loadClientContacts(HttpServletResponse response,
            @RequestParam("ClientId") Integer ClientId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(clientService.buildClientContactsResponseXML(ClientId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadContacts", method = RequestMethod.GET)
    public @ResponseBody void loadContacts(HttpServletResponse response, @RequestParam("rowId") Integer rowId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(clientContactsService.buildContactsResponseXML(rowId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadClientAddress", method = RequestMethod.GET)
    public @ResponseBody void loadClientAddress(HttpServletResponse response,
            @RequestParam("ClientId") Integer ClientId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(clientService.buildClientAddressResponseXML(ClientId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadCampaignDetails", method = RequestMethod.GET)
    public @ResponseBody void loadCampaignDetails(HttpServletResponse response,
            @RequestParam("ClientId") Integer ClientId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            Document document = masterCampaignService.buildCampaignDetailsResponseXML(ClientId);
            response.getWriter().write(document.asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadPostOfficeDetails", method = RequestMethod.GET)
    public @ResponseBody void loadPostOfficeDetails(HttpServletResponse response,
            @RequestParam("ClientId") Integer ClientId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(postOfficeService.buildPostOfficeDetailsResponseXML(ClientId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadMailContractDetails", method = RequestMethod.GET)
    public @ResponseBody void loadMailContractDetails(HttpServletResponse response,
            @RequestParam("ClientId") Integer ClientId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(clientService.buildMailContractDetailsResponseXML(ClientId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/loadNoticeOfIntentDetails", method = RequestMethod.GET)
    public @ResponseBody void loadNoticeOfIntentDetails(HttpServletResponse response,
            @RequestParam("ClientId") Integer ClientId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(noticeOfIntentService.buildNoticeOfIntentDetailsResponseXML(ClientId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadNotes", method = RequestMethod.GET)
    public @ResponseBody void loadNotes(HttpServletResponse response,
            @RequestParam("rowId") Integer rowId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            Document document = clientEventsService.buildNotesResponseXML(rowId);
            response.getWriter().write(document.asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadNews", method = RequestMethod.GET)
    public @ResponseBody void loadNews(HttpServletResponse response,
            @RequestParam("rowId") Integer rowId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(clientService.buildNewsResponseXML(rowId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadInstruction", method = RequestMethod.GET)
    public @ResponseBody void loadInstruction(HttpServletResponse response,
            @RequestParam("rowId") Integer rowId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(instructionsService.buildInstructionsResponseXML(rowId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadUploadFiles", method = RequestMethod.GET)
    public @ResponseBody void loadUploadFiles(HttpServletResponse response,
            @RequestParam("rowId") Integer rowId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(clientUpLoadsService.buildUploadFilesResponseXML(rowId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadSelectedClientContact", method = RequestMethod.GET)
    public @ResponseBody void loadSelectedClientContact(HttpServletResponse response,
            @RequestParam("rowId") Integer rowId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(clientContactsService.buildSelectedClientContactResponseXML(rowId).asXML());
        }
        catch (HelixServiceException e) {
            logger.error(e);
        }
        catch (IOException e) {
            logger.error(e);
        }
    }

    @RequestMapping(value = "/deleteClient", method = RequestMethod.GET)
    public @ResponseBody void deleteClient(HttpServletResponse response, @RequestParam("rowId") Integer rowId) {
        try {
            Client client=clientService.getClient(rowId);
            clientService.deleteClient(client);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/saveOrUpdateClient", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String saveOrUpdateClient(@ModelAttribute("clients") Client client,
            @RequestParam("userName") String userName,
            HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        try {
            Date date = new Date();
            Date currentDate = dateFormat.parse(dateFormat.format(date));
            Clientstatus clientstatus = clientStatusService.getClientStatus(1);
            User user = userService.getUser(userName);

            Client clientInstance = null;

            if (client.getIdClient() != null) {
                clientInstance = clientService.getClient(client.getIdClient());
                clientInstance.setClientName(client.getClientName());
                clientInstance.setCoClient(client.getCoClient());
                clientInstance.setJurisdiction(client.getJurisdiction());
                clientInstance.setIdUserLastUpdated(user);
                clientInstance.setDateUpdated(currentDate);
                clientInstance.setClientstatus(clientstatus);
            }
            else {
                clientInstance = client;
                clientInstance.setUser(user);
                clientInstance.setDateCreated(currentDate);
                clientInstance.setClientstatus(clientstatus);
            }

            if (client.getIdClientType() != null && client.getIdClientType() != 0) {
                ClientType clientType = new ClientType();
                clientType.setIdClientType(client.getIdClientType());
                clientInstance.setClientType(clientType);
            }
            else {
                clientInstance.setClientType(null);
            }
            clientService.saveOrUpdateClient(clientInstance);
            return String.valueOf(clientInstance.getIdClient());

        } catch (HelixServiceException e) {
            logger.error(e);
            return null;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @RequestMapping(value = "/saveOrUpdatePhoneNumbers", method = RequestMethod.GET)
    public void updatePhoneInfo(@RequestParam("clientId") Integer clientId,@RequestParam("mainPhone") String mainPhone,
            @RequestParam("fax") String fax, @RequestParam("dispatch") String dispatch,
            @RequestParam("userName") String userName,@RequestParam("IdContact") Integer IdContact) {
        Date date = new Date();
        try {
            Date currentDate = dateFormat.parse(dateFormat.format(date));
            User user = userService.getUser(userName);
            if (IdContact == null || IdContact == 0) {
                Contact contact = new Contact();
                contact.setMain(mainPhone);
                contact.setFax(fax);
                contact.setDispatch(dispatch);
                contact.setUser(user);
                contact.setDateCreated(currentDate);
                contactService.saveContact(contact);

                Client client = clientService.getClient(clientId);
                client.setContact(contactService.getContact(contact.getIdContact()));
                client.setIdUserLastUpdated(user);
                client.setDateUpdated(currentDate);
                clientService.updateClient(client);
            }
            else {
                Contact contact = contactService.getContact(IdContact);
                contact.setMain(mainPhone);
                contact.setFax(fax);
                contact.setDispatch(dispatch);
                contact.setIdUserLastUpdated(user);
                contact.setDateUpdated(currentDate);
                contactService.updateContact(contact);
            }
        } catch (HelixServiceException e) {
            logger.error(e);
        }catch (Exception e) {
            logger.error(e);
        }
    }

    @RequestMapping(value = "/saveContact", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void saveContact(@RequestParam("rowID") Integer rowID,@RequestParam("rank") String rank,
            @RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,
            @RequestParam("title") String title,@RequestParam("CCAddress") String CCAddress,
            @RequestParam("CCCity") String CCCity,@RequestParam("CCState") String CCState,
            @RequestParam("CCZip") String CCZip,@RequestParam("CCMain") String CCMain,
            @RequestParam("CCHome") String CCHome,@RequestParam("CCMobile") String CCMobile,
            @RequestParam("CCEmailCheck") String CCEmailCheck,@RequestParam("CCOther") String CCOther,
            @RequestParam("CCFaxCheck") String CCFaxCheck,@RequestParam("CCFax") String CCFax,
            @RequestParam("CCMailCheck") String CCMailCheck,@RequestParam("CCEmail") String CCEmail,
            @RequestParam("CCAltEmail") String CCAltEmail,@RequestParam("CCNotes") String CCNotes,
            @RequestParam("userName") String userName ) {
        try {
            User user = userService.getUser(userName);
            Date date = new Date();
            Date currentDate = dateFormat.parse(dateFormat.format(date));
            Address address = null;
            if (StringUtils.isNotBlank(CCAddress) || StringUtils.isNotBlank(CCCity)
                    || StringUtils.isNotBlank(CCState) || StringUtils.isNotBlank(CCZip)) {
                address = new Address();
                address.setStreetName(CCAddress);
                address.setCity(CCCity);
                if (StringUtils.isNotEmpty(CCState)) {
                    State state = addressService.getState(Integer.parseInt(CCState));
                    address.setState(state);
                }
                address.setFlStatus(false);
                address.setZipCode(CCZip);
                address.setUser(user);
                address.setDateCreated(currentDate);
                addressService.saveAddress(address);
            }

            Contact contact = new Contact();
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

            String lastAddressId = addressService.getLastAddressId();
            String lastContactId = contactService.getLastContactId();
            Clientcontact clientContact = new Clientcontact();
            clientContact.setRank(rank);
            clientContact.setFirstName(firstName);
            clientContact.setLastName(lastName);
            clientContact.setTitle(title);
            clientContact.setNotes(CCNotes);
            clientContact.setFlMonthlyStatementByEmail(Boolean.valueOf(CCEmailCheck));
            clientContact.setFlMonthlyStatementByMail(Boolean.valueOf(CCMailCheck));
            clientContact.setFlMonthlyStatementByFax(Boolean.valueOf(CCFaxCheck));
            clientContact.setFlStatus(true);
            clientContact.setUser(user);
            clientContact.setDateCreated(currentDate);
            clientContact.setClient(clientService.getClient(rowID));
            clientContact.setAddress(addressService.getAddress(Integer.parseInt(lastAddressId)));
            clientContact.setContact(contactService.getContact(Integer.parseInt(lastContactId)));
            if (address != null) {
                clientContact.setAddress(address);
            }
            clientContactsService.saveClientContact(clientContact);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/saveOrUpdateContact", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody void saveOrUpdateContact(@RequestParam("rowID") Integer rowID,@RequestParam("rank") String rank,
        @RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,
        @RequestParam("title") String title,@RequestParam("CCAddress") String CCAddress,
        @RequestParam("CCCity") String CCCity,@RequestParam("CCState") String CCState,
        @RequestParam("CCZip") String CCZip,@RequestParam("CCMain") String CCMain,
        @RequestParam("CCHome") String CCHome,@RequestParam("CCMobile") String CCMobile,
        @RequestParam("CCEmailCheck") String CCEmailCheck,@RequestParam("CCOther") String CCOther,
        @RequestParam("CCFaxCheck") String CCFaxCheck,@RequestParam("CCFax") String CCFax,
        @RequestParam("CCMailCheck") String CCMailCheck,@RequestParam("CCEmail") String CCEmail,
        @RequestParam("CCAltEmail") String CCAltEmail,@RequestParam("CCNotes") String CCNotes,
        @RequestParam("clientContactId") Integer clientContactId,
        @RequestParam("userName") String userName  ) {
        try {
            User user = userService.getUser(userName);
            Date date = new Date();
            Date currentDate = dateFormat.parse(dateFormat.format(date));
            Address address = clientContactsService.getClientContact(clientContactId).getAddress();
            if (address != null) {
                address.setStreetName(CCAddress);
                address.setCity(CCCity);
                if (CCState != null && CCState !=  "0") {
                    State state = addressService.getState(Integer.parseInt(CCState));
                    address.setState(state);
                }
                address.setZipCode(CCZip);
                address.setIdUserLastUpdated(user);
                address.setDateUpdated(currentDate);
                addressService.updateAddress(address);
                System.out.println("Updating an address on updateContactInfo()");
            }
            else if (StringUtils.isNotBlank(CCAddress) || StringUtils.isNotBlank(CCCity)
                    || StringUtils.isNotBlank(CCState) || StringUtils.isNotBlank(CCZip)) {
                address = new Address();
                address.setStreetName(CCAddress);
                address.setCity(CCCity);
                if (CCState != null && CCState !=  "0") {
                    State state = addressService.getState(Integer.parseInt(CCState));
                    address.setState(state);
                }
                address.setZipCode(CCZip);
                address.setFlStatus(false);
                address.setUser(user);
                address.setDateCreated(currentDate);
                addressService.saveAddress(address);
                System.out.println("Adding an address on updateContactInfo()");
            }
            Contact contact = clientContactsService.getClientContact(clientContactId).getContact();
            if (contact == null) {
                contact = new Contact();
                contact.setUser(user);
                contact.setDateCreated(currentDate);
            }
            contact.setMain(CCMain);
            contact.setHome(CCHome);
            contact.setMobile(CCMobile);
            contact.setOther(CCOther);
            contact.setFax(CCFax);
            contact.setEmail(CCEmail);
            contact.setEmailAlt(CCAltEmail);
            contact.setIdUserLastUpdated(user);
            contact.setDateUpdated(currentDate);
            contactService.saveOrUpdateContact(contact);

            Clientcontact clientContact = clientContactsService.getClientContact(clientContactId);
            clientContact.setRank(rank);
            clientContact.setFirstName(firstName);
            clientContact.setLastName(lastName);
            clientContact.setTitle(title);
            clientContact.setNotes(CCNotes);
            clientContact.setFlMonthlyStatementByEmail(Boolean.valueOf(CCEmailCheck));
            clientContact.setFlMonthlyStatementByMail(Boolean.valueOf(CCMailCheck));
            clientContact.setFlMonthlyStatementByFax(Boolean.valueOf(CCFaxCheck));
            clientContact.setIdUserLastUpdated(user);
            clientContact.setDateUpdated(currentDate);
            if (address != null) {
                clientContact.setAddress(address);
            }
            clientContactsService.updateClientContact(clientContact);
        } catch (HelixServiceException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
    public void deleteContactInfo(@RequestParam("rowId") Integer rowId) {
        try {
            clientContactsService.deleteClientContact(rowId);
            logger.info("Clinet contact id " + rowId + " successfully removed.");
        } catch (HelixServiceException e) {
            logger.error(e);
        }
    }

    @RequestMapping(value = "/updateAddressInfo", method = RequestMethod.GET)
    public void updateMailingAddress(@RequestParam("clientId") Integer clientId,
        @RequestParam("address1") String address1,
        @RequestParam("city") String city,@RequestParam("state") String state,
        @RequestParam("zip") String zip,@RequestParam("IdAddress") Integer IdAddress,
        @RequestParam("userName") String userName ) {
        try {
            User user=userService.getUser(userName);
            Date date = new Date();
            Date currentDate=dateFormat.parse(dateFormat.format(date));
            if (IdAddress == null) {
                Address address = new Address();
                address.setCity(city);
                address.setZipCode(zip);
                address.setStreetName(address1);
                if (state != null && state !=  "0") {
                    // get state
                    State stateObj = addressService.getState(Integer.parseInt(state));
                    address.setState(stateObj);
                }
                address.setUser(user);
                address.setDateCreated(currentDate);
                addressService.saveAddress(address);
                Client client = clientService.getClient(clientId);
                String lastId=addressService.getLastAddressId();
                client.setAddress(addressService.getAddress(Integer.parseInt(lastId)));
                client.setIdUserLastUpdated(user);
                client.setDateUpdated(currentDate);
                clientService.updateClient(client);
            }
            else {
                Address address = addressService.getAddress(IdAddress);
                address.setCity(city);
                address.setZipCode(zip);
                address.setStreetName(address1);
                if (state != null && state !=  "0") {
                    // get state
                    State stateObj = addressService.getState(Integer.parseInt(state));
                    address.setState(stateObj);
                }
                address.setIdUserLastUpdated(user);
                address.setDateUpdated(currentDate);
                addressService.saveOrUpdateAddress(address);
            }
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/updateCampaignInfo", method = RequestMethod.GET)
    public @ResponseBody void updateCampaignInfo(@RequestParam("clientId") Integer clientId,
            @RequestParam("IdCampaign") Integer IdCampaign,
            @RequestParam("rank") String rank,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("title") String title,
            @RequestParam("phone") String phone,
            @RequestParam("MCaddress") String MCaddress,
            @RequestParam("MCcity") String MCcity,
            @RequestParam("MCstate") String MCstate,
            @RequestParam("MCzip") String MCzip,
            @RequestParam("taxID") String taxID,
            @RequestParam("friendsOf") String friendsOf,
            @RequestParam("season") String season,
            @RequestParam("clientWebsite") String clientWebsite,
            @RequestParam("baseForm") String baseForm,
            @RequestParam("donationWebsite") String donationWebsite,
            @RequestParam("letterType") String letterType,
            @RequestParam("pictureTitle") String pictureTitle,
            @RequestParam("client") String clientPercent,
            @RequestParam("bankAcct") String bankAcct,
            @RequestParam("location") String location,
            @RequestParam("taxDeductible") String taxDeductible,
            @RequestParam("NEPVoicemail") String NEPVoicemail,
            @RequestParam("campaignContactId") Integer campaignContactId,
            @RequestParam("campaignAddressId") Integer campaignAddressId,
            @RequestParam("userName") String userName,
            HttpServletResponse response) {

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");

        try {
            User user = userService.getUser(userName);
            Date date = new Date();
            Date currentDate = dateFormat.parse(dateFormat.format(date));
            if (IdCampaign == null || IdCampaign.equals(0)) {
                Address address = new Address();
                address.setCity(MCcity);
                address.setZipCode(MCzip);
                address.setStreetName(MCaddress);
                if (StringUtils.isNotBlank(MCstate) && MCstate !=  "0") {
                    State stateObj = addressService.getState(Integer.parseInt(MCstate));
                    address.setState(stateObj);
                }
                address.setUser(user);
                address.setDateCreated(currentDate);
                //addressService.saveAddress(address);

                Contact contact = new Contact();
                contact.setMain(phone);
                contact.setWebURL(clientWebsite);
                contact.setUser(user);
                contact.setDateCreated(currentDate);
                //contactService.saveContact(contact);

                MasterCampaign masterCampaign = new MasterCampaign();
                masterCampaign.setRank(rank);
                masterCampaign.setFirstName(firstName);
                masterCampaign.setLastName(lastName);
                masterCampaign.setTitle(title);
                masterCampaign.setNEPVoiceMail(Boolean.valueOf(NEPVoicemail));
                masterCampaign.setTaxID(taxID);
                masterCampaign.setFriendsOf(friendsOf);
                masterCampaign.setSeason(season);
                masterCampaign.setDonationWebsite(donationWebsite);
                masterCampaign.setBaseFormNo(baseForm);
                masterCampaign.setLetterType(letterType);
                masterCampaign.setPictureTitle(pictureTitle);
                masterCampaign.setClientPercent(clientPercent);
                masterCampaign.setBankAccountNo(bankAcct);
                masterCampaign.setLocationNo(location);
                masterCampaign.setFlTaxDeductible(Boolean.valueOf(taxDeductible));
                masterCampaign.setUser(user);
                masterCampaign.setDateCreated(currentDate);
                masterCampaign.setClient(clientService.getClient(clientId));
                /*masterCampaign.setAddress(addressService.getAddress(address.getIdAddress()));
                masterCampaign.setContact(contactService.getContact(contact.getIdContact()));*/
                masterCampaign.setAddress(address);
                masterCampaign.setContact(contact);
                masterCampaignService.saveCampaignDetails(masterCampaign);
            }
            else {
                Address address = addressService.getAddress(campaignAddressId, true);
                address.setCity(MCcity);
                address.setZipCode(MCzip);
                address.setStreetName(MCaddress);
                if (StringUtils.isNotBlank(MCstate) && MCstate !=  "0") {
                    State stateObj = addressService.getState(Integer.parseInt(MCstate));
                    address.setState(stateObj);
                }
                address.setIdUserLastUpdated(user);
                address.setDateUpdated(currentDate);
                addressService.saveOrUpdateAddress(address);

                Contact contact = contactService.getContact(campaignContactId, true);
                contact.setMain(phone);
                contact.setWebURL(clientWebsite);
                if (contact.getIdContact() != null) {
                    contact.setIdUserLastUpdated(user);
                    contact.setDateUpdated(currentDate);
                }
                else {
                    contact.setUser(user);
                    contact.setDateCreated(currentDate);
                }
                contactService.saveOrUpdateContact(contact);

                MasterCampaign masterCampaign = masterCampaignService.getCampaignDetails(IdCampaign);
                masterCampaign.setRank(rank);
                masterCampaign.setFirstName(firstName);
                masterCampaign.setLastName(lastName);
                masterCampaign.setTitle(title);
                masterCampaign.setNEPVoiceMail(Boolean.valueOf(NEPVoicemail));
                masterCampaign.setTaxID(taxID);
                masterCampaign.setFriendsOf(friendsOf);
                masterCampaign.setSeason(season);
                masterCampaign.setDonationWebsite(donationWebsite);
                masterCampaign.setBaseFormNo(baseForm);
                masterCampaign.setLetterType(letterType);
                masterCampaign.setPictureTitle(pictureTitle);
                masterCampaign.setClientPercent(clientPercent);
                masterCampaign.setBankAccountNo(bankAcct);
                masterCampaign.setLocationNo(location);
                masterCampaign.setFlTaxDeductible(Boolean.valueOf(taxDeductible));
                masterCampaign.setIdUserLastUpdated(user);
                masterCampaign.setDateUpdated(currentDate);
                masterCampaign.setClient(clientService.getClient(clientId));
                masterCampaignService.updateCampaignDetails(masterCampaign);
            }
        } catch (HelixServiceException e) {
            logger.equals(e);
            response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
            try {
                response.getWriter().write(buildErrorMessage(HttpServletResponse.SC_NOT_IMPLEMENTED,
                        e.getMessage()).asXML());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            logger.equals(e);
            response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
            try {
                response.getWriter().write(buildErrorMessage(HttpServletResponse.SC_NOT_IMPLEMENTED,
                        "Internal server error.").asXML());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public Document buildErrorMessage(int code, String message) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("error");
        root.addAttribute("code", String.valueOf(code));
        root.addAttribute("message", message);
        return document;
    }

    @RequestMapping(value = "/updatePostOfficeInfo", method = RequestMethod.GET)
    public void updatePostOfficeInfo(@RequestParam("clientId") Integer clientId,
            @RequestParam("lastCalled") String lastCalled,@RequestParam("lastPickUp") String lastPickUp,
            @RequestParam("lastPaidOn") String lastPaidOn,  @RequestParam("expirationDate") String expirationDate,
            @RequestParam("renewalPeriod") String renewalPeriod,@RequestParam("boxSize") String boxSize,
            @RequestParam("renewalCost") String renewalCost,@RequestParam("ghost") String ghost,
            @RequestParam("notes") String notes,@RequestParam("tempHideAlert") String tempHideAlert,
            @RequestParam("postOfficeAddress") String postOfficeAddress,@RequestParam("POcity") String POcity,
            @RequestParam("POstate") String POstate,@RequestParam("POzip") String POzip,
            @RequestParam("postOfficePhone") String postOfficePhone,@RequestParam("contact1") String contact1,
            @RequestParam("postOfficeFax") String postOfficeFax,@RequestParam("contact2") String contact2,
            @RequestParam("postAddressId") Integer postAddressId,@RequestParam("postContactId") Integer postContactId,
            @RequestParam("postPickUpId") Integer postPickUpId,@RequestParam("IdPostOffice") Integer IdPostOffice,
            @RequestParam("userName") String userName, HttpServletResponse response) {
        try {
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");

            User user = userService.getUser(userName);
            Date date = new Date();
            Date currentDate = df.parse(df.format(date));
            Date lastCalledDate = (lastCalled != null && !lastCalled.equals("") && !lastCalled.equals(dfs))
                    ? df.parse(lastCalled) : null;
            Date lastPickUpDate = (lastPickUp != null && !lastPickUp.equals("") && !lastPickUp.equals(dfs))
                    ? df.parse(lastPickUp) : null;
            Date lastPaidOnDate = (lastPaidOn != null && !lastPaidOn.equals("") && !lastPaidOn.equals(dfs))
                    ? df.parse(lastPaidOn) : null;
            Date expirationDateValue = (expirationDate != null && !expirationDate.equals("")
                    && !expirationDate.equals(dfs)) ? df.parse(expirationDate) : null;
            BigDecimal decimalRenewalCost = (renewalCost != null && !"".equals(renewalCost)) ? new BigDecimal(renewalCost) : null;
            if (IdPostOffice == null) {
                Address address = new Address();
                address.setCity(POcity);
                address.setZipCode(POzip);
                address.setStreetName(postOfficeAddress);
                if (POstate != null && POstate !=  "0") {
                    // get state
                    State stateObj = addressService.getState(Integer.parseInt(POstate));
                    address.setState(stateObj);
                }
                address.setUser(user);
                address.setDateCreated(currentDate);
                addressService.saveAddress(address);
                Contact contact =new Contact();
                contact.setMain(postOfficePhone);
                contact.setFax(postOfficeFax);
                contact.setUser(user);
                contact.setDateCreated(currentDate);
                contactService.saveContact(contact);
                String lastAddressId=addressService.getLastAddressId();
                String lastContactId=contactService.getLastContactId();
                Postoffice postoffice=new Postoffice();
                postoffice.setPickupmethod(pickUpMethodsService.getPickUpMethod(postPickUpId));
                postoffice.setDateLastCalled(lastCalledDate);
                postoffice.setDateLastPickup(lastPickUpDate);
                postoffice.setDateLastPaid(lastPaidOnDate);
                postoffice.setDateExpiration(expirationDateValue);
                postoffice.setRenewalPeriod(Integer.parseInt(renewalPeriod));
                postoffice.setBoxSize(boxSize);
                postoffice.setRenewalCost(decimalRenewalCost);
                postoffice.setGhostNo(ghost);
                postoffice.setNotes(notes);
                postoffice.setFlHideAlert(Boolean.valueOf(tempHideAlert));
                postoffice.setContactPerson1(contact1);
                postoffice.setContactPerson2(contact2);
                postoffice.setUser(user);
                postoffice.setDateCreated(currentDate);
                postoffice.setAddress(addressService.getAddress(Integer.parseInt(lastAddressId)));
                postoffice.setContact(contactService.getContact(Integer.parseInt(lastContactId)));
                postoffice.setClient(clientService.getClient(clientId));
                postOfficeService.savePostOfficeDetails(postoffice);
            }
            else {
                Address address = addressService.getAddress(postAddressId);
                address.setCity(POcity);
                address.setZipCode(POzip);
                address.setStreetName(postOfficeAddress);
                if (POstate != null && POstate !=  "0") {
                    // get state
                    State stateObj = addressService.getState(Integer.parseInt(POstate));
                    address.setState(stateObj);
                }
                address.setIdUserLastUpdated(user);
                address.setDateUpdated(currentDate);
                addressService.updateAddress(address);
                Contact contact = contactService.getContact(postContactId);
                contact.setMain(postOfficePhone);
                contact.setFax(postOfficeFax);
                contact.setIdUserLastUpdated(user);
                contact.setDateUpdated(currentDate);
                contactService.updateContact(contact);
                Postoffice postoffice=postOfficeService.getPostOfficeDetails(IdPostOffice);
                postoffice.setPickupmethod(pickUpMethodsService.getPickUpMethod(postPickUpId));
                postoffice.setDateLastCalled(lastCalledDate);
                postoffice.setDateLastPickup(lastPickUpDate);
                postoffice.setDateLastPaid(lastPaidOnDate);
                postoffice.setDateExpiration(expirationDateValue);
                postoffice.setRenewalPeriod(Integer.parseInt(renewalPeriod));
                postoffice.setBoxSize(boxSize);
                postoffice.setRenewalCost(decimalRenewalCost);
                postoffice.setGhostNo(ghost);
                postoffice.setNotes(notes);
                postoffice.setFlHideAlert(Boolean.valueOf(tempHideAlert));
                postoffice.setContactPerson1(contact1);
                postoffice.setContactPerson2(contact2);
                postoffice.setIdUserLastUpdated(user);
                postoffice.setDateUpdated(currentDate);
                postOfficeService.updatePostOfficeDetails(postoffice);
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateMailContractInfo", method = RequestMethod.GET)
    public void updateMailContractInfo(@RequestParam("clientId") Integer clientId,
            @RequestParam("commencementDate") String commencementDate,
            @RequestParam("terminationDate") String terminationDate,
            @RequestParam("terminationBuff") String terminationBuff,
            @RequestParam("charity") String charity,
            @RequestParam("renewPeriod") Integer renewPeriod,
            @RequestParam("telemarketing") String telemarketing,
            @RequestParam("renewType") Integer renewType,
            @RequestParam("hideContractAlerts") String hideContractAlerts,
            @RequestParam("cancelLetterReceived") String cancelLetterReceived,
            @RequestParam("cancellationDate") String cancellationDate,
            @RequestParam("userName") String userName, HttpServletResponse response) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            User user = userService.getUser(userName);

            Date date = new Date();
            Date currentDate = df.parse(df.format(date));
            Date commencementDateValue = (commencementDate != null && !commencementDate.equals(""))
                    ? df.parse(commencementDate) : null;
            Date terminationDateValue = (terminationDate != null && !terminationDate.equals(""))
                    ? df.parse(terminationDate) : null;
            Date cancellationDateValue = (cancellationDate != null && !cancellationDate.equals(""))
                    ? df.parse(cancellationDate) : null;
            Client client=clientService.getClient(clientId);
            client.setCommencementDate(commencementDateValue);
            client.setTerminationDate(terminationDateValue);
            client.setCancellationDate(cancellationDateValue);
            client.setTerminationBuffer((terminationBuff != null && !terminationBuff.equals(""))
                    ? Integer.parseInt(terminationBuff) : null);
            client.setFlCharity(Boolean.valueOf(charity));
            client.setFlTelemarketing(Boolean.valueOf(telemarketing));
            client.setFlHideContractAlert(Boolean.valueOf(hideContractAlerts));
            client.setFlCancellationLetter(Boolean.valueOf(cancelLetterReceived));
            client.setIdUserLastUpdated(user);
            client.setDateUpdated(currentDate);
            //client.setRenewperiods(renewPeriodsService.getRenewPeriods(renewPeriod));
            client.setRenewtype(renewTypesService.getRenewTypes(renewType));
            clientService.updateClient(client);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateNOIInfo", method = RequestMethod.GET)
    public @ResponseBody void updateNOIInfo(@RequestParam("clientId") Integer clientId,
        @RequestParam("regNum") String regNum,
        @RequestParam("NOIemial") String NOIemial,@RequestParam("activity") String activity,
        @RequestParam("beginningDate") String beginningDate,
        @RequestParam("NOItelemarketing") String NOItelemarketing,
        @RequestParam("endingDate") String endingDate,
        @RequestParam("IdNoticeOfIntent") String IdNoticeOfIntent,
        @RequestParam("userName") String userName,
        HttpServletResponse response) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");

        try {
            User user = userService.getUser(userName);
            Date date = new Date();
            Date currentDate = df.parse(df.format(date));
            Date beginningDateValue = (beginningDate != null && !beginningDate.equals("")) ? df.parse(beginningDate) : null;
            Date endingDateValue = (endingDate != null && !endingDate.equals("")) ? df.parse(endingDate) : null;
            if (StringUtils.isBlank(IdNoticeOfIntent) || IdNoticeOfIntent.equals("0")) {
                Noticeofintent noticeofintent = new Noticeofintent();
                noticeofintent.setRegistrationNo(regNum);
                noticeofintent.setEmail(NOIemial);
                noticeofintent.setActivity(activity);
                noticeofintent.setBeginningDate(beginningDateValue);
                noticeofintent.setFlTelemarketing(Boolean.valueOf(NOItelemarketing));
                noticeofintent.setEndingDate(endingDateValue);
                noticeofintent.setUser(user);
                noticeofintent.setDateCreated(currentDate);
                noticeofintent.setClient(clientService.getClient(clientId));
                noticeofintent.setFlStatus(false);
                noticeOfIntentService.saveNoticeDetails(noticeofintent);
            }
            else {
                Noticeofintent noticeofintent = noticeOfIntentService.getNoticeDetails(Integer.valueOf(IdNoticeOfIntent));
                noticeofintent.setRegistrationNo(regNum);
                noticeofintent.setEmail(NOIemial);
                noticeofintent.setActivity(activity);
                noticeofintent.setBeginningDate(beginningDateValue);
                noticeofintent.setFlTelemarketing(Boolean.valueOf(NOItelemarketing));
                noticeofintent.setEndingDate(endingDateValue);
                noticeofintent.setIdUserLastUpdated(user);
                noticeofintent.setDateUpdated(currentDate);
                noticeofintent.setClient(clientService.getClient(clientId));
                noticeofintent.setFlStatus(false);
                noticeOfIntentService.updateNoticeDetails(noticeofintent);
            }
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }

   /* @RequestMapping(value = "/addNewNote", method = RequestMethod.GET)
    public void addNewNote(@RequestParam("dateCreated") String dateCreated, @RequestParam("alarmDate") String alarmDate,
            @RequestParam("note") String note,@RequestParam("alarm") String alarm,
            @RequestParam("userName") String userName,
            @RequestParam("clientId") Integer clientId) {
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateCreatedValue = (dateCreated != null && !dateCreated.equals("")) ? formatter.parse(dateCreated) : null;
            Date alarmDateDateValue = (alarmDate != null && !alarmDate.equals("")) ? formatter.parse(alarmDate) : null;
            Clientevent clientevent=new Clientevent();
            clientevent.setDateCreated(dateCreatedValue);
            clientevent.setAlarmDate(alarmDateDateValue);
            clientevent.setNotes(note);
            clientevent.setFlReminder(Boolean.valueOf(alarm));
            clientevent.setClient(clientService.getClient(clientId));
            clientevent.setUser(userService.getUser(userName));
            clientEventsService.saveClientEvent(clientevent);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    @RequestMapping(value = "/deleteNote", method = RequestMethod.GET)
    public @ResponseBody void deleteNote(@RequestParam("rowId") Integer rowId) {
        try {
            clientEventsService.deleteClientEvent(rowId);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/saveOrUpdateNote", method = RequestMethod.GET)
    public @ResponseBody void updateNote(@RequestParam("noteId") Integer noteId,@RequestParam("note") String note,
        @RequestParam("dateCreated") String dateCreated,@RequestParam("alarmDate") String alarmDate,
        @RequestParam("alarm") String alarm,@RequestParam("userName") String userName,
        @RequestParam("clientId") Integer clientId, HttpServletResponse response) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            User user = userService.getUser(userName);
            Date alarmDateDateValue = (alarmDate != null && !alarmDate.equals("")) ? df.parse(alarmDate) : null;
            Clientevent clientevent = null;
            if (noteId == null || noteId.equals(0)) {
                clientevent = new Clientevent();
                Date dateCreatedValue = (dateCreated != null && !dateCreated.equals("")) ? df.parse(dateCreated) : null;
                clientevent.setDateCreated(dateCreatedValue);
                clientevent.setAlarmDate(alarmDateDateValue);
                clientevent.setNotes(note);
                clientevent.setFlReminder(Boolean.valueOf(alarm));
                clientevent.setClient(clientService.getClient(clientId));
                clientevent.setUser(userService.getUser(userName));
                clientevent.setFlStatus(false);
            }
            else {
                Date currentDate = df.parse(df.format(new Date()));
                clientevent = clientEventsService.getClientEvent(noteId);
                clientevent.setAlarmDate(alarmDateDateValue);
                clientevent.setNotes(note);
                clientevent.setFlReminder(Boolean.valueOf(alarm));
                clientevent.setClient(clientService.getClient(clientId));
                clientevent.setUser(user);
                clientevent.setIdUserLastUpdated(user);
                clientevent.setDateUpdated(currentDate);
            }
            clientEventsService.saveOrUpdateClientEvent(clientevent);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }

    /*@RequestMapping(value = "/addNewInstruction", method = RequestMethod.GET)
    public void addNewInstruction(@RequestParam("instruction") String instruction,
        @RequestParam("userName") String userName,
        @RequestParam("clientId") Integer clientId) {
        try{
            User user = userService.getUser(userName);
            Date date = new Date();
            Date currentDate = df.parse(df.format(date));
            Clientselectioninstruction clientInstruction=new Clientselectioninstruction();
            clientInstruction.setInstruction(instruction);
            clientInstruction.setUser(user);
            clientInstruction.setDateCreated(currentDate);
            clientInstruction.setClient(clientService.getClient(clientId));
            instructionsService.saveInstruction(clientInstruction);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @RequestMapping(value = "/deleteInstruction", method = RequestMethod.GET)
    public @ResponseBody void deleteInstruction(@RequestParam("rowId") Integer rowId) {
        try {
            instructionsService.deleteInstruction(rowId);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/saveOrUpdateInstruction", method = RequestMethod.GET)
    public @ResponseBody void updateInstruction(@RequestParam("instructionId") Integer instructionId,
            @RequestParam("instruction") String instruction,
            @RequestParam("userName") String userName,
            @RequestParam("clientId") Integer clientId,
            HttpServletResponse response) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            Date date = new Date();
            Date currentDate = df.parse(df.format(date));
            User user = userService.getUser(userName);
            Clientselectioninstruction clientInstruction = null;
            if (instructionId == 0 || instructionId.equals(0)) {
                clientInstruction=new Clientselectioninstruction();
                clientInstruction.setInstruction(instruction);
                clientInstruction.setUser(user);
                clientInstruction.setDateCreated(currentDate);
                clientInstruction.setClient(clientService.getClient(clientId));
                clientInstruction.setFlStatus(false);
            }
            else {
                clientInstruction = instructionsService.getInstruction(instructionId);
                clientInstruction.setInstruction(instruction);
                clientInstruction.setIdUserLastUpdated(user);
                clientInstruction.setDateUpdated(currentDate);
                clientInstruction.setClient(clientService.getClient(clientId));
            }
            instructionsService.saveOrUpdateInstruction(clientInstruction);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @RequestMapping(value = "/deleteUploadFile", method = RequestMethod.GET)
    public @ResponseBody void deleteUploadFile(@RequestParam("rowId") Integer rowId) {
        try {
            clientUpLoadsService.deleteClientUpload(rowId);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
    }


    /**
     * Upload single file using Spring Controller
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public @ResponseBody String uploadFileHandler(MultipartHttpServletRequest fileRequest,
            HttpServletResponse response) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        Iterator<String> itr =  fileRequest.getFileNames();
        MultipartFile file = fileRequest.getFile(itr.next());

        String rootPath = "C:\\apache-tomcat-7.0.47\\webapps\\upload_files\\";//System.getProperty("catalina.home");
        //String fileName = ((CommonsMultipartFile) file).getFileItem().getName();
        String fileName = ((CommonsMultipartFile) file).getFileItem().getName().split("\\\\")[((CommonsMultipartFile) file).getFileItem().getName().split("\\\\").length-1];
       // String noSpaceFileName = fileName.replaceAll(" ", "%20");
        //String[] fileDetails=new String[2];
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                // Creating the directory to store file
                File dir = new File(rootPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                return serverFile.getAbsolutePath();
            } catch (Exception e) {
                System.out.println("You failed to upload " + fileName + " => " + e.getMessage());
                return null;
            }
        }
        else {
            System.out.println( "You failed to upload " + fileName + " because the file was empty.");
               return  null;
        }
    }



    @RequestMapping(value = "/saveFileUploadDetails", method = RequestMethod.GET)
    public @ResponseBody void saveFileUploadDetails(@RequestParam("clientId") Integer clientId,
        @RequestParam("absolutePath") String absolutePath,
        @RequestParam("userName") String userName, HttpServletResponse response) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            User user=userService.getUser(userName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate = formatter.parse(formatter.format(date));
            if (absolutePath != "") {
                String[] tokens = absolutePath.split("\\\\");
                String fileName = tokens[tokens.length - 1];
                String noSpaceFileName = fileName.replaceAll(" ", "%20");
                //String fileNameUri = "<a href=upload_files/" + noSpaceFileName + " />" + fileName + "</a>";
                String fileNameUri = "<a href=client/downloadFile?name=" + noSpaceFileName + " />" + fileName + "</a>";
                DocumentType documentType = new DocumentType();
                documentType.setDocTypeId(8);
                documentType.setDocType("Other");
                documentType.setFlStatus(true);

                Clientupload clientupload = new Clientupload();
                clientupload.setFileName(fileNameUri);
                clientupload.setFilePath(absolutePath);
                clientupload.setClient(clientService.getClient(clientId));
                clientupload.setUser(user);
                clientupload.setDateCreated(currentDate);
                clientupload.setDocumentType(documentType);
                clientUpLoadsService.saveClientUpload(clientupload);
            }
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public void doDownload(HttpServletRequest request,HttpServletResponse response, @RequestParam("name") String name)
            throws IOException {
        /**
         * Size of a byte buffer to read/write file
         */
        final int BUFFER_SIZE = 4096;
        /**
         * Path of the file to be downloaded, relative to application's directory
         */
       // get absolute path of the application
       ServletContext context = request.getSession().getServletContext();
       /*String appPath =context.getRealPath("");
       System.out.println("appPath = " + appPath);
*/
       // construct the complete absolute path of the file
       String fullPath = "C:\\apache-tomcat-7.0.47\\webapps\\upload_files\\"+name;
       File downloadFile = new File(fullPath);
       FileInputStream inputStream = new FileInputStream(downloadFile);

       // get MIME type of the file
       String mimeType = context.getMimeType(fullPath);
       if (mimeType == null) {
           // set to binary type if MIME mapping not found
           mimeType = "application/octet-stream";
       }
       System.out.println("MIME type: " + mimeType);

       // set content attributes for the response
       response.setContentType(mimeType);
       response.setContentLength((int) downloadFile.length());

       // set headers for the response
       String headerKey = "Content-Disposition";
       String headerValue = String.format("attachment; filename=\"%s\"",
               downloadFile.getName());
       response.setHeader(headerKey, headerValue);

       // get output stream of the response
       OutputStream outStream = response.getOutputStream();

       byte[] buffer = new byte[BUFFER_SIZE];
       int bytesRead = -1;

       // write bytes read from the input stream into the output stream
       while ((bytesRead = inputStream.read(buffer)) != -1) {
           outStream.write(buffer, 0, bytesRead);
       }

       inputStream.close();
       outStream.close();

   }

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public ModelAndView  print(ModelAndView modelAndView) {
// Retrieve our data from a custom data provider
        // Our data comes from a DAO layer


        List<ClientReport> clientReportList = new ArrayList<ClientReport>();
        List<Client> clientList= null;
        try {
            clientList = clientService.getClients();
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }

        for(Client client: clientList){
          if(client.getClientstatus().getStatus().equals("Client")){
            ClientReport clientReport =new ClientReport();
            clientReport.setJurisdiction(client.getJurisdiction());
            clientReport.setAcronym(client.getCoClient());
            clientReport.setClientId(client.getIdClient());
            clientReport.setClientName(client.getClientName());
            clientReportList.add(clientReport);
          }
        }

        JRDataSource ds = new JRBeanCollectionDataSource(clientReportList);


        // In order to use Spring's built-in Jasper support,
        // We are required to pass our datasource as a map parameter
        // parameterMap is the Model of our application
        Map<String,Object> parameterMap = new HashMap<String,Object>();
        parameterMap.put("datasource", ds);

        // pdfReport is the View of our application
        // This is declared inside the /WEB-INF/jasper-views.xml
        modelAndView = new ModelAndView("pdfReportClient", parameterMap);
        // Return the View and the Model combined
        return modelAndView;
    }


/*    public Document buildContactsResponseXML(String rowId) throws HelixServiceException {
        int clientID=Integer.parseInt(rowId);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("contacts");
        Client client = clientService.getClient(clientID);
        List<Contact> contacts=new ArrayList<Contact>();
        contacts=client.getContacts();

        for(Contact contact : contacts) {
            Element element = root.addElement("contact");
            element.addAttribute("clientId", String.valueOf(contact.getContactId()));
            element.addAttribute("name", contact.getName());
            element.addAttribute("title",contact.getTitle());
            element.addAttribute("phone",contact.getPrimaryPhone());
            element.addAttribute("home", contact.getHomePhone());
            element.addAttribute("mobile",contact.getMobile());
            element.addAttribute("other",contact.getOtherPhone());
            element.addAttribute("fax", contact.getFax());
            element.addAttribute("email",contact.getEmail());

        }

        return document;
    }*/

    /*
    private List<String> uploadFiles(List<MultipartFile> multipartFiles) {
        List<String> fileNames = new ArrayList<String>();
        if(multipartFiles != null && multipartFiles.size() > 0) {
            for (MultipartFile multipartFile : multipartFiles) {
                //String fileName = multipartFile.getOriginalFilename();
                String fileName = String.valueOf(Calendar.getInstance().getTimeInMillis());
                fileNames.add(fileName);
                try {
                    InputStream is = multipartFile.getInputStream();
                    File dFile = new File("C:\\apache-tomcat-7.0.40\\webapps\\helix-product-images\\" + fileName + ".jpg");
                    OutputStream os = new FileOutputStream(dFile);
                    byte[] b = new byte[2048];
                    int length;
                    while ((length = is.read(b)) != -1) {
                        os.write(b, 0, length);
                    }
                    is.close();
                    os.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return fileNames;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView saveOrUpdateClient(@ModelAttribute("product") @Validated ProductBean productBean,
            BindingResult result, ModelMap model) {
        Product product = new Product();
        product.setCategory(new Category());
        product.setSubCategory(new SubCategory());
        if (productBean.getProductId() != null &&  !productBean.getProductId().equals(0)) {
            product.setProductId(productBean.getProductId());
        }
        product.getCategory().setClientId(productBean.getClientId());
        product.getSubCategory().setSubCategoryId(productBean.getSubCategoryId());
        product.setName(productBean.getName());
        product.setPrice(Double.valueOf(productBean.getPrice()));
        product.setInfo(productBean.getInfo());
        product.setSourceUrl(productBean.getSourceUrl());
        // Add upload images
        List<ProductImage> productImages = new ArrayList<ProductImage>();
        List<String> fileNames = uploadFiles(productBean.getFiles());
        for (String fileName : fileNames) {
            ProductImage productImage = new ProductImage();
            productImage.setProduct(product);
            productImage.setName(fileName);
            productImages.add(productImage);
        }
        product.setProductImages(productImages);
        try {
            productService.saveOrUpdateProduct(product);
        }
        catch (DataAccessException e) {
            e.printStackTrace();
        }
        // populate controls
        model.addAttribute("message", "GRIP Product");
        model.addAttribute("categories", getCategories());
        model.addAttribute("subCategories", getSubCategories());
        return new ModelAndView("manage-product", "product", productBean);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody void deleteClient(@RequestParam Integer id) {
        productService.deleteProduct(productService.getProduct(id));
    }

    private Map<Integer, String> getCategories() {
        Map<Integer, String> categories = new LinkedHashMap<Integer, String>();
        for (Category category : productService.getCategories()) {
            categories.put(category.getClientId(), category.getName());
        }
        return categories;
    }

    private Map<Integer, String> getSubCategories() {
        Map<Integer, String> categories = new LinkedHashMap<Integer, String>();
        for (SubCategory subCategory : productService.getSubCategories()) {
            categories.put(subCategory.getSubCategoryId(), subCategory.getName()
                    + " [" + subCategory.getCategory().getName() + "]");
        }
        return categories;
    }*/

    private Map<Integer, String> getClientTypes() {
        Map<Integer, String> clientTypesMap = new LinkedHashMap<Integer, String>();
        try {
            List<ClientType> clientTypes = clientService.getClientType();
            clientTypesMap.put(0, "-- Select --");
            for (ClientType clientType : clientTypes) {
                clientTypesMap.put(clientType.getIdClientType(), clientType.getClientType());
            }
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        return clientTypesMap;
    }

    private Map<Integer, String> getSeasons() {
        Map<Integer, String> seasons = new LinkedHashMap<Integer, String>();
        seasons.put(1, "Spring");
        seasons.put(2, "Summer");
        seasons.put(3, "Fall");
        seasons.put(4, "Winter");
        return seasons;
    }

    private Map<Integer, String> getCampaignContacts() {
        Map<Integer, String> campaignContacts = new LinkedHashMap<Integer, String>();
        campaignContacts.put(1, "Cpl. Kris Bisbing (Chairman)");
        campaignContacts.put(2, "Edward Flores (Board Member)");
        campaignContacts.put(3, "Sgt. Brad Geyer (PA President)");
        campaignContacts.put(4, "Holly Molnar (Treasurer)");
        campaignContacts.put(5, "Det. Frank Nunes (Vice President)");
        return campaignContacts;
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

    private Map<Integer, String> getRenewType() {
        Map<Integer, String> stateDdl = new LinkedHashMap<Integer, String>();
        try {
            List<Renewtype> renewtypes = renewTypesService.getRenewTypes();
            stateDdl.put(0, "-- State --");
            if (renewtypes != null) {
                for (Renewtype renewtype : renewtypes) {
                    stateDdl.put(renewtype.getIdRenewType(), renewtype.getRenewType());
                }
            }
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        return stateDdl;
    }
}
