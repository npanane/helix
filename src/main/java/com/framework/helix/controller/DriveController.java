package com.framework.helix.controller;

import com.framework.helix.bean.DriveReport;
import com.framework.helix.entity.Campaign;
import com.framework.helix.entity.Client;
import com.framework.helix.entity.Drive;
import com.framework.helix.entity.User;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.mock.MockFrontEnd;
import com.framework.helix.service.CampaignService;
import com.framework.helix.service.ClientService;
import com.framework.helix.service.DriveService;
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
 * Created by nuwan.n.bandara on 6/11/2014.
 */
@Controller
@RequestMapping("/drive")
public class DriveController {

    private DriveService driveService;
    private UserService userService;
    private CampaignService campaignService;
    private ClientService clientService;

    @Autowired
    public void setDriveService(DriveService driveService) {
        this.driveService = driveService;
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    public void setCampaignService(CampaignService campaignService){
        this.campaignService = campaignService;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView init(ModelMap model) {
        model.addAttribute("pageHeader" , "Drives");
        return new ModelAndView("drives");
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public @ResponseBody void loadDriveDetails(HttpServletResponse response) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildDriveResponseXML().asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadDriveInformation", method = RequestMethod.GET)
    public @ResponseBody
    void loadDriveInformation(HttpServletResponse response,@RequestParam("driveId") Integer driveId) {

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildGetDriveInfoResponseXML(driveId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadCampaignList", method = RequestMethod.GET)
    public @ResponseBody
    void loadCampaignDetails(HttpServletResponse response,@RequestParam("driveId") Integer driveId) {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildCampaignListInfoResponseXML(driveId).asXML());
        }
        catch (HelixServiceException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Document buildDriveResponseXML() throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("drives");
        List<Drive> drives = driveService.getDrives();
        if(drives!=null) {
            for (Drive drive : drives) {
                Element element = root.addElement("drive");
                element.addAttribute("driveId", String.valueOf(drive.getIdDrive()));
                element.addAttribute("name", drive.getDriveName());
            }
        }
        return document;
    }

    public Document buildGetDriveInfoResponseXML(Integer driveId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("drive");
        Drive drive = driveService.getDrive(driveId);

            Element element = root.addElement("details");
            element.addAttribute("driveId",String.valueOf(drive.getIdDrive()));
            element.addAttribute("description", drive.getDriveName());
            if(drive.getDriveMonth()!=null){
            element.addAttribute("month", String.valueOf(drive.getDriveMonth()));}
            if(drive.getDriveYear()!=null){
            element.addAttribute("year", String.valueOf(drive.getDriveYear()));}
            element.addAttribute("inProcess", String.valueOf(drive.getFlInProcess()));
        return document;
    }

    public Document buildCampaignListInfoResponseXML(Integer driveId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("campaigns");
        List<Campaign> campaignList=campaignService.getDriveCampaignList(driveId);
        if(campaignList!=null) {
            for (Campaign campaign:campaignList) {
                Element element = root.addElement("campaign");
                element.addAttribute("campaignId",campaign.getIdCampaign().toString() );
                element.addAttribute("edit", "<a href='client'> C </a> | <a href='campaign?clientId="
                        + campaign.getClient().getIdClient() + "'> C </a> | <a href='#'> D </a> "
                        + "| <a href='#'> F </a>");
                element.addAttribute("client", campaign.getClient().getClientName());
                element.addAttribute("year", campaign.getCampaignYear());
                element.addAttribute("type", (campaign.getCampaigntype() != null)
                        ? campaign.getCampaigntype().getCampaignType() : "");
                element.addAttribute("amount", "");
                element.addAttribute("ccAmount", "");
                element.addAttribute("count", "");
                element.addAttribute("average", "");
                element.addAttribute("response", "");
            }
        }
        return document;
    }

    @RequestMapping(value = "/deleteDrive", method = RequestMethod.GET)
    public @ResponseBody void deleteDrive(@RequestParam("rowId") Integer rowId) {

        try {
            Drive drive=driveService.getDrive(rowId);
            driveService.deleteDrive(drive);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/saveOrUpdateDriveInfo", method = RequestMethod.GET)
    public @ResponseBody String saveOrUpdateDriveInfo(@RequestParam("description") String description,
                @RequestParam("month") Integer month,@RequestParam("year") Integer year,
                @RequestParam("totalAmount") String totalAmount, @RequestParam("totalCount") String totalCount,
                @RequestParam("totalCCAmount") String totalCCAmount,@RequestParam("averageAmount") String averageAmount,
                @RequestParam("Response") String Response,@RequestParam("inProcess") String inProcess,
                @RequestParam("userName") String userName,
                @RequestParam("driveId") Integer driveId,HttpServletResponse response) {
        //set encoding explicitly
        response.setCharacterEncoding("UTF-8");
        try {
            User user = userService.getUser(userName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate = dateFormat.parse(dateFormat.format(date));
            if (driveId != null) {
                // update drive info
                Drive drive = driveService.getDrive(driveId);
                drive.setDriveName(description);
                drive.setDriveMonth(month);
                drive.setDriveYear(year);
                drive.setFlInProcess(Boolean.valueOf(inProcess));
                drive.setIdUserLastUpdated(user);
                drive.setDateUpdated(currentDate);
                driveService.updateDrive(drive);
                return driveId.toString();
            } else {
                // add drive info
                Drive drive = new Drive();
                drive.setDriveName(description);
                drive.setDriveMonth(month);
                drive.setDriveYear(year);
                drive.setFlInProcess(Boolean.valueOf(inProcess));
                drive.setUser(user);
                drive.setDateCreated(currentDate);
                // set campaign
                drive.setCampaigns(buildCampaigns(drive));
                driveService.saveDrive(drive);
                String driveIdStr = driveService.getLastDriveId();
                return driveIdStr;
            }
        } catch (HelixServiceException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Campaign> buildCampaigns(Drive drive) {
        List<Campaign> campaigns = new ArrayList<Campaign>();
        try {
            List<Client> clients = clientService.getClients();
            for (Client client : clients) {
                Campaign campaign = new Campaign();
                campaign.setDrive(drive);
                campaign.setClient(client);
                campaign.setCampaignYear(String.valueOf(drive.getDriveYear()));
                campaigns.add(campaign);
            }
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        return campaigns;
    }


    @RequestMapping(value = "/addNewCampaignInfo", method = RequestMethod.GET)
    public void addNewCampaignInfo(@RequestParam("client") String client,@RequestParam("year") String year,
                                   @RequestParam("driveId") String driveId) {

        // further more
        System.out.println("client:"+client+" year:"+year+ " driveId:"+driveId);
    }


    @RequestMapping(value = "/deleteCampaignInfo", method = RequestMethod.GET)
    public @ResponseBody void deleteCampaignInfo(@RequestParam("rowId") Integer rowId) {

        // further more
        System.out.println("rowId:"+rowId );
    }

    @RequestMapping(value = "/updateCampaignInfo", method = RequestMethod.GET)
    public @ResponseBody void updateCampaignInfo(@RequestParam("campaignId") Integer campaignId,
                         @RequestParam("client") String client,@RequestParam("year") String year,
                         @RequestParam("driveId") Integer driveId) {

        // further more
        System.out.println("campaignId:"+campaignId+" driveId:"+driveId+"client:" );
    }


    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public ModelAndView  print(ModelAndView modelAndView) {

        List<DriveReport> driveReportList = new ArrayList<DriveReport>();
        List<Drive> driveList= null;
        try {
            driveList = driveService.getDrives();
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }

        for(Drive drive: driveList){
            DriveReport driveReport =new DriveReport();
            driveReport.setName(drive.getDriveName());
            if(drive.getDriveMonth()!=null){
            driveReport.setMonth(drive.getDriveMonth().toString());}
            if(drive.getDriveYear()!=null){
            driveReport.setYear(drive.getDriveYear().toString());}
            driveReportList.add(driveReport);
        }

        JRDataSource ds = new JRBeanCollectionDataSource(driveReportList);


        // In order to use Spring's built-in Jasper support,
        // We are required to pass our datasource as a map parameter
        // parameterMap is the Model of our application
        Map<String,Object> parameterMap = new HashMap<String,Object>();
        parameterMap.put("datasource", ds);

        // pdfReport is the View of our application
        // This is declared inside the /WEB-INF/jasper-views.xml
        modelAndView = new ModelAndView("pdfReportDrive", parameterMap);
        // Return the View and the Model combined
        return modelAndView;
    }


}
