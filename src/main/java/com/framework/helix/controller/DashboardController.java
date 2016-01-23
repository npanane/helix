package com.framework.helix.controller;

import com.framework.helix.bean.ContactView;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.mock.MockFrontEnd;
import com.framework.helix.service.ClientService;
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

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView init(ModelMap model) {
        model.addAttribute("pageHeader" , "Dashboard");
        return new ModelAndView("dashboard", "dashboard", new ContactView());
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public @ResponseBody void loadClients(HttpServletResponse response) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            //response.getWriter().write(buildClientResponseXML().asXML());
            response.getWriter().write(MockFrontEnd.getEvents());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadPendingDataEntries", method = RequestMethod.GET)
    public @ResponseBody void loadPendingDataEntries(HttpServletResponse response) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            //response.getWriter().write(buildClientResponseXML().asXML());
            response.getWriter().write(MockFrontEnd.getloadPendingDataEntries());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
