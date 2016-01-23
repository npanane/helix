package com.framework.helix.controller;


import com.framework.helix.entity.Staffsuggestion;
import com.framework.helix.entity.User;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.SuggestionService;
import com.framework.helix.service.UserService;
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
import java.util.Date;
import java.util.List;

/**
 * Created by nuwan.n.bandara on 9/3/2014.
 */
@Controller
@RequestMapping("/suggestions")
public class SuggestionController {

    private SuggestionService suggestionService;
    private UserService userService;

    @Autowired
    public void setSuggestionService(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService; }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView init(ModelMap model) {
        model.addAttribute("pageHeader" , "Suggestions");
        return new ModelAndView("suggestionDropBox");
    }

    @RequestMapping(value = "/loadSuggestions", method = RequestMethod.GET)
    public @ResponseBody
    void loadSuggestions(HttpServletResponse response) {

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildSuggestionsResponseXML().asXML());
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Document buildSuggestionsResponseXML() throws HelixServiceException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("suggestions");
        List<Staffsuggestion> staffSuggestionList = suggestionService.getStaffSuggestions();
        if (staffSuggestionList != null) {
            for (Staffsuggestion staffsuggestion : staffSuggestionList) {
                Element element = root.addElement("suggestion");
                element.addAttribute("suggestionId", String.valueOf(staffsuggestion.getIdSuggestion()));
                element.addAttribute("date", (staffsuggestion.getDateCreated() != null && !"".equals(staffsuggestion.getDateCreated()))
                        ? staffsuggestion.getDateCreated().toString().substring(0,10) : null);
                element.addAttribute("by", staffsuggestion.getUser().getUserName());
                element.addAttribute("suggestion", staffsuggestion.getSuggestion());
                if (staffsuggestion.getFlComplete() != null) {
                    element.addAttribute("completed", staffsuggestion.getFlComplete().toString());
                }
            }
        }
        return document;
    }


    @RequestMapping(value = "/saveSuggestion", method = RequestMethod.GET)
    public void saveSuggestion(@RequestParam("userName") String userName,
                               @RequestParam("suggestion") String suggestion){
        try {
            User user=userService.getUser(userName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=dateFormat.parse(dateFormat.format(date));


                Staffsuggestion staffSuggestion = new Staffsuggestion();
                staffSuggestion.setSuggestion(suggestion);
                staffSuggestion.setFlComplete(Boolean.valueOf(false));
                staffSuggestion.setUser(user);
                staffSuggestion.setDateCreated(currentDate);
                suggestionService.saveStaffSuggestion(staffSuggestion);

        } catch (HelixServiceException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/editSuggestion", method = RequestMethod.GET)
    public void editSuggestion(@RequestParam("userName") String userName,
                               @RequestParam("state") String state,@RequestParam("rowId") Integer rowId){
        try {
            User user=userService.getUser(userName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=dateFormat.parse(dateFormat.format(date));


            Staffsuggestion staffSuggestion =suggestionService.getStaffSuggestion(rowId);
            staffSuggestion.setFlComplete(Boolean.valueOf(state));
            staffSuggestion.setIdUserLastUpdated(user);
            staffSuggestion.setDateUpdated(currentDate);
            suggestionService.updateStaffSuggestion(staffSuggestion);

        } catch (HelixServiceException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



}
