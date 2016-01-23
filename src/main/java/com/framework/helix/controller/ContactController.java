package com.framework.helix.controller;

import com.framework.helix.bean.ContactView;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.mock.MockFrontEnd;
import com.framework.helix.service.ClientService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private ClientService clientService;

    @Autowired
    public ContactController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView init(ModelMap model) {
        model.addAttribute("contactTypes", getContactType());
        model.addAttribute("pageHeader" , "Contacts");
        return new ModelAndView("contact", "contact", new ContactView());
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public @ResponseBody void loadContacts(HttpServletResponse response) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            //response.getWriter().write(buildContactResponseXML().asXML());
            response.getWriter().write(MockFrontEnd.getContacts());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadAddresses", method = RequestMethod.GET)
    public @ResponseBody void loadAddresses(HttpServletResponse response) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            //response.getWriter().write(buildClientResponseXML().asXML());
            response.getWriter().write(MockFrontEnd.getAddresses());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadPhones", method = RequestMethod.GET)
    public @ResponseBody void loadPhones(HttpServletResponse response) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            //response.getWriter().write(buildClientResponseXML().asXML());
            response.getWriter().write(MockFrontEnd.getPhones());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/savePhone", method = RequestMethod.GET)
    public @ResponseBody void loadPhones(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            request.getParameter("!nativeeditor_status");request.getParameter("c1");
            //response.getWriter().write(buildClientResponseXML().asXML());
            //response.getWriter().write(MockFrontEnd.getPhones());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<Integer, String> getContactType() {
        Map<Integer, String> contactTypes = new LinkedHashMap<Integer, String>();
        contactTypes.put(1,"General");
        contactTypes.put(2,"Vendor");
        return contactTypes;
    }





}
