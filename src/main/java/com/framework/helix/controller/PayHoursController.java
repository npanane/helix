package com.framework.helix.controller;

import com.framework.helix.bean.ReferencesReport;
import com.framework.helix.entity.Client;
import com.framework.helix.entity.Clientcontact;
import com.framework.helix.entity.Clientreference;
import com.framework.helix.entity.User;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.ClientContactsService;
import com.framework.helix.service.ClientReferenceService;
import com.framework.helix.service.ClientService;
import com.framework.helix.service.UserService;
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
 * Created by nuwan.n.bandara on 9/4/2014.
 */
@Controller
@RequestMapping("/payHours")
public class PayHoursController {

    private ClientReferenceService clientReferenceService;
    private UserService userService;
    private ClientService clientService;
    private ClientContactsService clientContactsService;


    @Autowired
    public void setClientReferenceService(ClientReferenceService clientReferenceService) {
        this.clientReferenceService = clientReferenceService;
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService; }

    @Autowired
    public void setClientService(ClientService clientService){
        this.clientService = clientService; }


    @Autowired
    public void setClientContactsService(ClientContactsService clientContactsService){
        this.clientContactsService = clientContactsService; }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView init(ModelMap model) {
        model.addAttribute("dropDown1", getClientNames());
        model.addAttribute("pageHeader" , "Pay Hours");
        return new ModelAndView("payHours");
    }

    @RequestMapping(value = "/loadReferences", method = RequestMethod.GET)
    public @ResponseBody
    void loadReferences(HttpServletResponse response) {

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildReferencesResponseXML().asXML());
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Document buildReferencesResponseXML() throws HelixServiceException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("references");
        List<Clientreference> clientReferenceList = clientReferenceService.getClientReferences();
        if (clientReferenceList != null) {
            for (Clientreference clientreference : clientReferenceList) {
                Element element = root.addElement("reference");
                element.addAttribute("referenceId", String.valueOf(clientreference.getIdClientreference()));
                element.addAttribute("Name", clientreference.getClient().getClientName());
                element.addAttribute("Contact", clientreference.getContactName());
                element.addAttribute("phoneNumber", clientreference.getPhoneNo());

            }
        }
        return document;
    }

    @RequestMapping(value = "/getClientContacts", method = RequestMethod.GET)
    public @ResponseBody void updateClient( @RequestParam("clientId") Integer clientId,
                                              HttpServletResponse response) {
        System.out.println(clientId);
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildContactNamesResponseXML(clientId).asXML());

        } catch (HelixServiceException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public Document buildContactNamesResponseXML(Integer clientId) throws HelixServiceException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("contactNames");
        List<Clientcontact> clientContactsList=clientContactsService.getClientContacts(clientId);
        if(clientContactsList!=null) {
            for (Clientcontact clientcontact : clientContactsList) {
                Element element = root.addElement("contactName");
                element.addAttribute("Id", String.valueOf(clientcontact.getIdClientContact()));
                element.addAttribute("Name", clientcontact.getFirstName());
                element.addAttribute("phoneNumber", clientcontact.getContact().getMain());

            }
        }
        return document;
    }

    @RequestMapping(value = "/getReferenceInformation", method = RequestMethod.GET)
    public @ResponseBody
    void getReferenceInformation(@RequestParam("rowId") Integer rowId,HttpServletResponse response) {

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(getReferenceInfoResponseXML(rowId).asXML());
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Document getReferenceInfoResponseXML(Integer referenceId) throws HelixServiceException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("reference");
        Clientreference clientreference=clientReferenceService.getClientReference(referenceId);
        if (clientreference != null) {
                Element element = root.addElement("details");
                element.addAttribute("Name", String.valueOf(clientreference.getClient().getIdClient()));
                element.addAttribute("Contact", clientreference.getContactName());
                element.addAttribute("phoneNumber", clientreference.getPhoneNo());
                element.addAttribute("zip", clientreference.getZipCode());
        }
        return document;
    }


  /*  @RequestMapping(value = "/getContactPhones", method = RequestMethod.GET)
    public @ResponseBody void getContactPhones( @RequestParam("contactName") String contactName,
                                            HttpServletResponse response) {

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildContactPhonesResponseXML(contactName).asXML());

        } catch (HelixServiceException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public Document buildContactPhonesResponseXML(String contactName) throws HelixServiceException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("phoneNumber");
        Clientcontact clientContact=clientContactsService.getClientContactByName(contactName);
        if(clientContact!=null) {
                Element element = root.addElement("details");
                element.addAttribute("Id", String.valueOf(clientContact.getIdClientContact()));
                element.addAttribute("phoneNumber", clientContact.getContact().getMain());
        }
        return document;
    }*/






    @RequestMapping(value = "/deleteReference", method = RequestMethod.GET)
    public @ResponseBody void deleteReference(@RequestParam("rowId") Integer rowId) {

        try {
            Clientreference clientreference=clientReferenceService.getClientReference(rowId);
            clientReferenceService.deleteClientReference(clientreference);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/saveReference", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody void saveReference( @RequestParam("clientID") Integer clientID,
                                             @RequestParam("contactName") String contactName,
                                             @RequestParam("phone") String phone,@RequestParam("zip") String zip,
                                             @RequestParam("userName") String userName) {

         try{
             User user=userService.getUser(userName);
             DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
             Date date = new Date();
             Date currentDate=dateFormat.parse(dateFormat.format(date));
             Client client=clientService.getClient(clientID);

                Clientreference clientReference=new Clientreference();
                clientReference.setClient(client);
                clientReference.setContactName(contactName);
                clientReference.setPhoneNo(phone);
                clientReference.setZipCode(zip);
                clientReference.setUser(user);
                clientReference.setDateCreated(currentDate);

                clientReferenceService.saveClientReference(clientReference);

        } catch (HelixServiceException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    @RequestMapping(value = "/updateReference", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody void updateReference(@RequestParam("clientID") Integer clientID,
                                             @RequestParam("contactName") String contactName,
                                             @RequestParam("phone") String phone,@RequestParam("zip") String zip,
                                             @RequestParam("userName") String userName,
                                             @RequestParam("rowId") Integer rowId) {

        try{
            User user=userService.getUser(userName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=dateFormat.parse(dateFormat.format(date));
            Client client=clientService.getClient(clientID);

            Clientreference clientReference=clientReferenceService.getClientReference(rowId);
            clientReference.setClient(client);
            clientReference.setContactName(contactName);
            clientReference.setPhoneNo(phone);
            clientReference.setZipCode(zip);
            clientReference.setIdUserLastUpdated(user);
            clientReference.setDateUpdated(currentDate);
            clientReferenceService.updateClientReference(clientReference);

        } catch (HelixServiceException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    private Map<Integer, String> getClientNames() {
        Map<Integer, String> dropDown1 = new LinkedHashMap<Integer, String>();

        try {
            List<Client> clientList=clientService.getClients();
            if(clientList!=null) {
                for (Client client : clientList) {
                    dropDown1.put(client.getIdClient(), client.getClientName());
                }
            }
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        return dropDown1;
    }

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public ModelAndView  print(ModelAndView modelAndView) {

        List<ReferencesReport> referencesReportList = new ArrayList<ReferencesReport>();
        List<Clientreference> clientReferenceList= null;
        try {
            clientReferenceList = clientReferenceService.getClientReferences();
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }

        for(Clientreference clientReference: clientReferenceList){
            ReferencesReport referencesReport =new ReferencesReport();
            referencesReport.setName(clientReference.getClient().getClientName());
            referencesReport.setContact( clientReference.getContactName());
            referencesReport.setPhoneNumber(clientReference.getPhoneNo());
            referencesReportList.add(referencesReport);
        }

        JRDataSource ds = new JRBeanCollectionDataSource(referencesReportList);


        // In order to use Spring's built-in Jasper support,
        // We are required to pass our datasource as a map parameter
        // parameterMap is the Model of our application
        Map<String,Object> parameterMap = new HashMap<String,Object>();
        parameterMap.put("datasource", ds);

        // pdfReport is the View of our application
        // This is declared inside the /WEB-INF/jasper-views.xml
        modelAndView = new ModelAndView("pdfReportReferences", parameterMap);
        // Return the View and the Model combined
        return modelAndView;
    }



}
