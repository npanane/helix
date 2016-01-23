package com.framework.helix.controller;

import com.framework.helix.entity.*;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.*;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Nuwan on 9/17/2014.
 */

@Controller
@RequestMapping("/poBoxPickUps")
public class POBoxPickUpsController {

    private ClientService clientService;
    private PostOfficeService postOfficeService;
    private PickUpMethodsService pickUpMethodsService;
    private MasterCampaignService masterCampaignService;
    private UserService userService;
    private ContactService contactService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setPostOfficeService(PostOfficeService postOfficeService) {
        this.postOfficeService = postOfficeService;
    }

    @Autowired
    public void setPickUpMethodsService(PickUpMethodsService pickUpMethodsService) {
        this.pickUpMethodsService = pickUpMethodsService;
    }

    @Autowired
    public void setMasterCampaignService(MasterCampaignService masterCampaignService) {
        this.masterCampaignService = masterCampaignService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView init(ModelMap model) {
        model.addAttribute("pageHeader" , "PO Box Pickups");
        return new ModelAndView("poBoxPickUps");
    }

    @RequestMapping(value = "/loadSpringPOBoxInfo", method = RequestMethod.GET)
    public @ResponseBody
    void loadSpringPOBoxInfo(HttpServletResponse response) {

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildSpringPOBoxInfoResponseXML().asXML());
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Document buildSpringPOBoxInfoResponseXML() throws HelixServiceException {
        Calendar cal = Calendar.getInstance();
        String currentYear=Integer.toString(cal.get(Calendar.YEAR));

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("clients");

        try {
            List<Client> clients = clientService.getClientsForPoBoxPickup("Spring", 2014);
            for (Client client : clients) {
                Element element = root.addElement("client");
                element.addAttribute("posOfficeId", String.valueOf("Post Office Id"));
                element.addAttribute("client", client.getClientName());
                element.addAttribute("box", "Box");
                element.addAttribute("phone", "Phone");
                element.addAttribute("contact", "postOffice.getContactPerson1()");
                element.addAttribute("pickedUp", "postOffice.getDateLastPickup()");
                element.addAttribute("notes", "postOffice.getNotes()");
            }
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
          /*List<Mastercampaign> masterCampaignList=masterCampaignService.getCampaignDetailsForSpring();
          for(Mastercampaign mastercampaign:masterCampaignList){


               List<Postoffice> postOfficeList= postOfficeService.getPostOfficeDetailsForDrivable(mastercampaign.getClient().getIdClient(),currentYear);
                   for(Postoffice postOffice:postOfficeList){
                         if(postOffice.getDateLastPickup().toString().substring(0,4).equals(currentYear) ) {
                             Element element = root.addElement("client");
                             element.addAttribute("posOfficeId", String.valueOf(postOffice.getIdPostoffice()));
                             element.addAttribute("client", mastercampaign.getClient().getClientName());
                             element.addAttribute("box", postOffice.getAddress().getStreetName());
                             element.addAttribute("phone", postOffice.getContact().getMain());
                             element.addAttribute("contact", postOffice.getContactPerson1());
                             element.addAttribute("pickedUp", (postOffice.getDateLastPickup() != null && !"".equals(postOffice.getDateLastPickup()))
                                     ? postOffice.getDateLastPickup().toString().substring(0, 10) : null);
                             element.addAttribute("notes", postOffice.getNotes());
                         }

                   }

          }
          */
        return document;
    }

    @RequestMapping(value = "/loadFallPOBoxInfo", method = RequestMethod.GET)
    public @ResponseBody
    void loadFallPOBoxInfo(HttpServletResponse response) {

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildFallPOBoxInfoResponseXML().asXML());
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Document buildFallPOBoxInfoResponseXML() throws HelixServiceException {
        Calendar cal = Calendar.getInstance();
        String currentYear=Integer.toString(cal.get(Calendar.YEAR));

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("clients");
        List<MasterCampaign> masterCampaignList=masterCampaignService.getCampaignDetailsForFall();
        for(MasterCampaign masterCampaign :masterCampaignList){

                List<Postoffice> postOfficeList= postOfficeService.getPostOfficeDetailsForDrivable(masterCampaign.getClient().getIdClient(),currentYear);
                for(Postoffice postOffice:postOfficeList){
                      if(postOffice.getDateLastPickup().toString().substring(0,4).equals(currentYear) ) {

                          Element element = root.addElement("client");
                          element.addAttribute("posOfficeId", String.valueOf(postOffice.getIdPostoffice()));
                          element.addAttribute("client", masterCampaign.getClient().getClientName());
                          element.addAttribute("box", postOffice.getAddress().getStreetName());
                          element.addAttribute("phone", postOffice.getContact().getMain());
                          element.addAttribute("contact", postOffice.getContactPerson1());
                          element.addAttribute("pickedUp", (postOffice.getDateLastPickup() != null && !"".equals(postOffice.getDateLastPickup()))
                                  ? postOffice.getDateLastPickup().toString().substring(0, 10) : null);
                          element.addAttribute("notes", postOffice.getNotes());
                      }

                }

        }

        return document;
    }

    @RequestMapping(value = "/editSPhoneNumber", method = RequestMethod.GET)
    public void editSPhoneNumber(@RequestParam("phoneNo") String phoneNo,
                                       @RequestParam("userName") String userName,
                                       @RequestParam("rowId") Integer rowId  ) {

        try{
            User user=userService.getUser(userName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=formatter.parse(formatter.format(date));

            Postoffice postOffice=postOfficeService.getPostOfficeDetails(rowId);
            Contact contact=postOffice.getContact();
            contact.setMain(phoneNo);
            contact.setIdUserLastUpdated(user);
            contact.setDateUpdated(currentDate);
            contactService.updateContact(contact);

        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @RequestMapping(value = "/editSContactName", method = RequestMethod.GET)
    public void editSContactName(@RequestParam("contactName") String contactName,
                                 @RequestParam("userName") String userName,
                                 @RequestParam("rowId") Integer rowId  ) {

        try{
            User user=userService.getUser(userName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=formatter.parse(formatter.format(date));

            Postoffice postOffice=postOfficeService.getPostOfficeDetails(rowId);
            postOffice.setContactPerson1(contactName);
            postOffice.setIdUserLastUpdated(user);
            postOffice.setDateUpdated(currentDate);
            postOfficeService.updatePostOfficeDetails(postOffice);

        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }


    }


    @RequestMapping(value = "/editSDate", method = RequestMethod.GET)
    public void editSDate(@RequestParam("date") String pickedUpDate,
                          @RequestParam("userName") String userName,
                          @RequestParam("rowId") Integer rowId  ) {

        try{
            User user=userService.getUser(userName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=formatter.parse(formatter.format(date));
            Date date2 = (pickedUpDate != null && !pickedUpDate.equals("") && !pickedUpDate.equals("yyyy-mm-dd"))
                    ? formatter.parse(pickedUpDate) : null;

            Postoffice postOffice=postOfficeService.getPostOfficeDetails(rowId);
            postOffice.setDateLastPickup(date2);
            postOffice.setIdUserLastUpdated(user);
            postOffice.setDateUpdated(currentDate);
            postOfficeService.updatePostOfficeDetails(postOffice);

        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @RequestMapping(value = "/editSNote", method = RequestMethod.GET)
    public void editSNote(@RequestParam("note") String note,
                          @RequestParam("userName") String userName,
                          @RequestParam("rowId") Integer rowId  ) {

        try{
            User user=userService.getUser(userName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=formatter.parse(formatter.format(date));

            Postoffice postOffice=postOfficeService.getPostOfficeDetails(rowId);
            postOffice.setNotes(note);
            postOffice.setIdUserLastUpdated(user);
            postOffice.setDateUpdated(currentDate);
            postOfficeService.updatePostOfficeDetails(postOffice);

        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }


    }


    @RequestMapping(value = "/editFPhoneNumber", method = RequestMethod.GET)
    public void editFPhoneNumber(@RequestParam("phoneNo") String phoneNo,
                                 @RequestParam("userName") String userName,
                                 @RequestParam("rowId") Integer rowId  ) {

        try{
            User user=userService.getUser(userName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=formatter.parse(formatter.format(date));

            Postoffice postOffice=postOfficeService.getPostOfficeDetails(rowId);
            Contact contact=postOffice.getContact();
            contact.setMain(phoneNo);
            contact.setIdUserLastUpdated(user);
            contact.setDateUpdated(currentDate);
            contactService.updateContact(contact);

        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @RequestMapping(value = "/editFContactName", method = RequestMethod.GET)
    public void editFContactName(@RequestParam("contactName") String contactName,
                                 @RequestParam("userName") String userName,
                                 @RequestParam("rowId") Integer rowId  ) {

        try{
            User user=userService.getUser(userName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=formatter.parse(formatter.format(date));

            Postoffice postOffice=postOfficeService.getPostOfficeDetails(rowId);
            postOffice.setContactPerson1(contactName);
            postOffice.setIdUserLastUpdated(user);
            postOffice.setDateUpdated(currentDate);
            postOfficeService.updatePostOfficeDetails(postOffice);

        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }


    }


    @RequestMapping(value = "/editFDate", method = RequestMethod.GET)
    public void editFDate(@RequestParam("date") String pickedUpDate,
                          @RequestParam("userName") String userName,
                          @RequestParam("rowId") Integer rowId  ) {

        try{
            User user=userService.getUser(userName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=formatter.parse(formatter.format(date));
            Date date2 = (pickedUpDate != null && !pickedUpDate.equals("") && !pickedUpDate.equals("yyyy-mm-dd"))
                    ? formatter.parse(pickedUpDate) : null;

            Postoffice postOffice=postOfficeService.getPostOfficeDetails(rowId);
            postOffice.setDateLastPickup(date2);
            postOffice.setIdUserLastUpdated(user);
            postOffice.setDateUpdated(currentDate);
            postOfficeService.updatePostOfficeDetails(postOffice);

        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @RequestMapping(value = "/editFNote", method = RequestMethod.GET)
    public void editFNote(@RequestParam("note") String note,
                          @RequestParam("userName") String userName,
                          @RequestParam("rowId") Integer rowId  ) {

        try{
            User user=userService.getUser(userName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=formatter.parse(formatter.format(date));

            Postoffice postOffice=postOfficeService.getPostOfficeDetails(rowId);
            postOffice.setNotes(note);
            postOffice.setIdUserLastUpdated(user);
            postOffice.setDateUpdated(currentDate);
            postOfficeService.updatePostOfficeDetails(postOffice);

        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }


    }




}
