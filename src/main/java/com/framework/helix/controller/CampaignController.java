package com.framework.helix.controller;

import com.framework.helix.bean.CampaignReport;
import com.framework.helix.bean.ClientView;
import com.framework.helix.entity.*;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.*;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by nuwan.n.bandara on 8/19/2014.
 */
@Controller
@RequestMapping("/campaign")
public class CampaignController {

    private CampaignService campaignService;
    private UserService userService;
    private DriveService driveService;
    private CampaignTypeService campaignTypeService;
    private ClientService clientService;


    @Autowired
    public void setCampaignService(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setDriveService(DriveService driveService) {
        this.driveService = driveService;
    }

    @Autowired
    public void setCampaignTypeService(CampaignTypeService campaignTypeService) {
        this.campaignTypeService = campaignTypeService;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView defaultLoad(ModelMap model,
            @RequestParam(value="clientId", required = false, defaultValue = "0") Integer clientId) {
        model.addAttribute("dropDown1", getDriveNames());
        model.addAttribute("dropDown2", getCampaignTypes());
        model.addAttribute("clientDdl", getClients());
        model.addAttribute("selectedClientId", clientId);
        model.addAttribute("pageHeader", "Campaigns");
        return new ModelAndView("campaigns");
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public
    @ResponseBody
    void loadDriveDetails(HttpServletResponse response, @RequestParam("clientId") Integer clientId) {

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildCampaignResponseXML(clientId).asXML());
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadCampaignInformation", method = RequestMethod.GET)
    public
    @ResponseBody
    void loadCampaignInformation(HttpServletResponse response, @RequestParam("campaignId") Integer campaignId) {

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildCampaignInformationResponseXML(campaignId).asXML());
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadOtherInformation", method = RequestMethod.GET)
    public
    @ResponseBody
    void loadOtherInformation(HttpServletResponse response, @RequestParam("campaignId") Integer campaignId) {

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildOtherInformationResponseXML(campaignId).asXML());
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loadQtyInformation", method = RequestMethod.GET)
    public
    @ResponseBody
    void loadQtyInformation(HttpServletResponse response, @RequestParam("campaignId") Integer campaignId) {

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildQtyInformationResponseXML(campaignId).asXML());
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/loadCostInformation", method = RequestMethod.GET)
    public
    @ResponseBody
    void loadCostInformation(HttpServletResponse response, @RequestParam("campaignId") Integer campaignId) {

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(buildCostInformationResponseXML(campaignId).asXML());
        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Document buildCampaignResponseXML(Integer clientId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("campaigns");
        List<Campaign> campaigns = campaignService.getCampaignsByClientId(clientId);
        for (Campaign campaign : campaigns) {
            Element element = root.addElement("campaign");
            element.addAttribute("campaignId", String.valueOf(campaign.getIdCampaign()));
            element.addAttribute("name", campaign.getDrive().getDriveName());
        }
        return document;
    }

    public Document buildCampaignInformationResponseXML(Integer campaignId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("campaign");
        Campaign campaign=campaignService.getCampaign(campaignId);
        if(campaign!=null) {
            Element element = root.addElement("details");
            element.addAttribute("drive",campaign.getDrive().getIdDrive().toString());
            element.addAttribute("year", campaign.getCampaignYear());
            element.addAttribute("formNo", campaign.getFormNo());
            element.addAttribute("type", (campaign.getCampaigntype() != null)
                    ? campaign.getCampaigntype().getIdCampaignType().toString() : "");
        }
        return document;
    }

    public Document buildOtherInformationResponseXML(Integer campaignId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Other");
        Campaign campaign=campaignService.getCampaign(campaignId);
        if(campaign!=null) {
            Element element = root.addElement("details");
            if (campaign.getFlPhoto() != null) {
                element.addAttribute("photo", campaign.getFlPhoto().toString());
            }
            if (campaign.getFlSignature() != null) {
                element.addAttribute("signature", campaign.getFlSignature().toString());
            }
            if (campaign.getFlBadge() != null) {
                element.addAttribute("badge", campaign.getFlBadge().toString());
            }
            if (campaign.getFlCauses() != null) {
                element.addAttribute("causes", campaign.getFlCauses().toString());
            }
            if (campaign.getFlDocLetter() != null) {
                element.addAttribute("letter", campaign.getFlDocLetter().toString());
            }
            if (campaign.getFlDocRD() != null) {
                element.addAttribute("rd", campaign.getFlDocRD().toString());
            }
            if (campaign.getFlDocCRM() != null) {
                element.addAttribute("crm", campaign.getFlDocCRM().toString());
            }

            if (campaign.getFlCPLetter() != null) {
                element.addAttribute("CPLetter", campaign.getFlCPLetter().toString());
            }
            if (campaign.getFlCPRD() != null) {
                element.addAttribute("CPrd", campaign.getFlCPRD().toString());
            }
            if (campaign.getFlCPBadge() != null) {
                element.addAttribute("CPBadge", campaign.getFlCPBadge().toString());
            }

            if (campaign.getFlFPLetter() != null) {
                element.addAttribute("FPLetter", campaign.getFlFPLetter().toString());
            }

            if (campaign.getFlFPRD() != null) {
                element.addAttribute("FPrd", campaign.getFlFPRD().toString());
            }
            if (campaign.getFlFPSticker() != null) {
                element.addAttribute("FPSticker", campaign.getFlFPSticker().toString());
            }
            if (campaign.getFlFPCRM() != null) {
                element.addAttribute("FPcrm", campaign.getFlFPCRM().toString());
            }


        }
        return document;
    }


    public Document buildQtyInformationResponseXML(Integer campaignId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Qty");
        Campaign campaign=campaignService.getCampaign(campaignId);
        if(campaign!=null) {
            Element element = root.addElement("details");
            if (campaign.getBusinessMailed() != null) {
            element.addAttribute("BAmountMailed",campaign.getBusinessMailed().toString());}
            element.addAttribute("BAmountReturned", "0");
            element.addAttribute("BPercentageReturned", "NAN.NAN%");
            if (campaign.getResidentMailed() != null) {
            element.addAttribute("RAmountMailed", campaign.getResidentMailed().toString());}
            element.addAttribute("RAmountReturned", "0");
            element.addAttribute("RPercentageReturned", "NAN.NAN%");
            element.addAttribute("TAmountMailed","0");
            element.addAttribute("TAmountReturned","0");
            element.addAttribute("TPercentageReturned","NAN.NAN%");
        }
        return document;
    }

    public Document buildCostInformationResponseXML(Integer campaignId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Cost");
        Campaign campaign=campaignService.getCampaign(campaignId);
        if(campaign!=null) {
            Element element = root.addElement("details");
            if (campaign.getCostOfLetters() != null) {
            element.addAttribute("letters",campaign.getCostOfLetters().toString());}
            if (campaign.getCostOfData() != null) {
            element.addAttribute("data", campaign.getCostOfData().toString());}
            if (campaign.getCostOfPOBox() != null) {
            element.addAttribute("POBox",campaign.getCostOfPOBox().toString());}
            if (campaign.getCostOfDecals() != null) {
            element.addAttribute("decals", campaign.getCostOfDecals().toString());}
            if (campaign.getCostOfProduction() != null) {
            element.addAttribute("production",campaign.getCostOfProduction().toString());}
            if (campaign.getCostOfEnvelopes() != null) {
            element.addAttribute("envelopes", campaign.getCostOfEnvelopes().toString());}
            if (campaign.getCostOfArtwork() != null) {
            element.addAttribute("artWork",campaign.getCostOfArtwork().toString());}
            if (campaign.getFlBulkRate() != null) {
                element.addAttribute("NonProfitBulkRate", campaign.getFlBulkRate().toString());
            }
            if (campaign.getCostOfPostage() != null) {
            element.addAttribute("postage", campaign.getCostOfPostage().toString());}
            if (campaign.getCostOfPins() != null) {
            element.addAttribute("pins",campaign.getCostOfPins().toString());}
        }
        return document;
    }

    @RequestMapping(value = "/deleteCampaignInfo", method = RequestMethod.GET)
    public @ResponseBody void deleteCampaignInfo(@RequestParam("rowId") Integer rowId) {

        try {
            Campaign campaign=campaignService.getCampaign(rowId);
            campaignService.deleteCampaign(campaign);
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/updateCampaignInfo", method = RequestMethod.GET)
    public @ResponseBody String updateCampaignInfo(@RequestParam("drive") Integer drive,@RequestParam("year") String year,
                                   @RequestParam("formNo") String formNo, @RequestParam("type") Integer type,
                                   @RequestParam("userName") String userName,@RequestParam("rowId") Integer rowId,
                                   HttpServletResponse response) {

        //set encoding explicitly
        response.setCharacterEncoding("UTF-8");
        try {
            User user=userService.getUser(userName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=dateFormat.parse(dateFormat.format(date));

            if(rowId!=null){

                Campaign campaign=campaignService.getCampaign(rowId);
                campaign.setDrive(driveService.getDrive(drive));
                campaign.setCampaignYear(year);
                campaign.setFormNo(formNo);
                campaign.setCampaigntype(campaignTypeService.getCampaignType(type));
                campaign.setIdUserLastUpdated(user);
                campaign.setDateUpdated(currentDate);
                campaignService.updateCampaign(campaign);

                return rowId.toString();
            }
            else{
                Campaign campaignObj=new Campaign();
                campaignObj.setDrive(driveService.getDrive(drive));
                campaignObj.setCampaignYear(year);
                campaignObj.setFormNo(formNo);
                campaignObj.setCampaigntype(campaignTypeService.getCampaignType(type));
                campaignObj.setUser(user);
                campaignObj.setDateCreated(currentDate);
                campaignService.saveCampaign(campaignObj);

                String CampaignId=campaignService.getLastCampaignId();
                return CampaignId;
            }

        } catch (HelixServiceException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    @RequestMapping(value = "/updateOtherInfo", method = RequestMethod.GET)
    public @ResponseBody void updateOtherInfo(@RequestParam("photo") String photo,@RequestParam("signature") String signature,
                                     @RequestParam("badge") String badge, @RequestParam("causes") String causes,
                                     @RequestParam("letter") String letter, @RequestParam("rd") String rd,
                                     @RequestParam("crm") String crm, @RequestParam("CPLetter") String CPLetter,
                                     @RequestParam("CPrd") String CPrd, @RequestParam("CPBadge") String CPBadge,
                                     @RequestParam("FPLetter") String FPLetter, @RequestParam("FPrd") String FPrd,
                                     @RequestParam("FPSticker") String FPSticker, @RequestParam("FPcrm") String FPcrm,
                                     @RequestParam("userName") String userName,@RequestParam("rowId") Integer rowId) {

        try {
            User user=userService.getUser(userName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=dateFormat.parse(dateFormat.format(date));

                Campaign campaign=campaignService.getCampaign(rowId);
                campaign.setFlPhoto(Boolean.valueOf(signature));
                campaign.setFlSignature(Boolean.valueOf(photo));
                campaign.setFlBadge(Boolean.valueOf(badge));
                campaign.setFlCauses(Boolean.valueOf(causes));
                campaign.setFlDocLetter(Boolean.valueOf(letter));
                campaign.setFlDocRD(Boolean.valueOf(rd));
                campaign.setFlDocCRM(Boolean.valueOf(crm));
                campaign.setFlCPLetter(Boolean.valueOf(CPLetter));
                campaign.setFlCPRD(Boolean.valueOf(CPrd));
                campaign.setFlCPBadge(Boolean.valueOf(CPBadge));
                campaign.setFlFPLetter(Boolean.valueOf(FPLetter));
                campaign.setFlFPRD(Boolean.valueOf(FPrd));
                campaign.setFlFPSticker(Boolean.valueOf(FPSticker));
                campaign.setFlFPCRM(Boolean.valueOf(FPcrm));
                campaign.setIdUserLastUpdated(user);
                campaign.setDateUpdated(currentDate);
                campaignService.updateCampaign(campaign);

        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateQtyInfo", method = RequestMethod.GET)
    public @ResponseBody void updateQtyInfo(@RequestParam("BAmountMailed") Integer BAmountMailed,
                                            @RequestParam("BAmountReturned") String BAmountReturned,
                                            @RequestParam("BPercentageReturned") String BPercentageReturned,
                                            @RequestParam("RAmountMailed") Integer RAmountMailed,
                                            @RequestParam("RAmountReturned") String RAmountReturned,
                                            @RequestParam("RPercentageReturned") String RPercentageReturned,
                                            @RequestParam("TAmountMailed") String TAmountMailed,
                                            @RequestParam("TAmountReturned") String TAmountReturned,
                                            @RequestParam("TPercentageReturned") String TPercentageReturned,
                                            @RequestParam("userName") String userName,
                                            @RequestParam("rowId") Integer rowId) {

        try {
            User user=userService.getUser(userName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=dateFormat.parse(dateFormat.format(date));

            Campaign campaign=campaignService.getCampaign(rowId);
            campaign.setBusinessMailed(BAmountMailed);
            campaign.setResidentMailed(RAmountMailed);
            campaign.setIdUserLastUpdated(user);
            campaign.setDateUpdated(currentDate);
            campaignService.updateCampaign(campaign);

        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/updateCostInfo", method = RequestMethod.GET)
    public @ResponseBody void updateCostInfo(@RequestParam("letters") String letters,
                                            @RequestParam("data") String data,
                                            @RequestParam("POBox") String POBox,
                                            @RequestParam("decals") String decals,
                                            @RequestParam("production") String production,
                                            @RequestParam("envelopes") String envelopes,
                                            @RequestParam("artWork") String artWork,
                                            @RequestParam("NonProfitBulkRate") String NonProfitBulkRate,
                                            @RequestParam("postage") String postage,
                                            @RequestParam("pins") String pins,
                                            @RequestParam("userName") String userName,
                                            @RequestParam("rowId") Integer rowId) {

        try {
            User user=userService.getUser(userName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate=dateFormat.parse(dateFormat.format(date));

            Campaign campaign=campaignService.getCampaign(rowId);
            if (!letters.equals("")) {
            campaign.setCostOfLetters(Double.parseDouble(letters));}
            if (!data.equals("") ) {
            campaign.setCostOfData(Double.parseDouble(data));}
            if (!POBox.equals("") ) {
            campaign.setCostOfPOBox(Double.parseDouble(POBox));}
            if (!decals.equals("") ) {
            campaign.setCostOfDecals(Double.parseDouble(decals));}
            if (!production.equals("")) {
            campaign.setCostOfProduction(Double.parseDouble(production));}
            if (!envelopes.equals("")  ) {
            campaign.setCostOfEnvelopes(Double.parseDouble(envelopes));}
            if (!artWork.equals("") ) {
            campaign.setCostOfArtwork(Double.parseDouble(artWork));}
            if (!postage.equals("") ) {
            campaign.setCostOfPostage(Double.parseDouble(postage));}
            if (!pins.equals("") ) {
            campaign.setCostOfPins(Double.parseDouble(pins));}

            campaign.setFlBulkRate(Boolean.valueOf(NonProfitBulkRate));
            campaign.setIdUserLastUpdated(user);
            campaign.setDateUpdated(currentDate);
            campaignService.updateCampaign(campaign);

        } catch (HelixServiceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<Integer, String> getDriveNames() {
        Map<Integer, String> dropDown1 = new LinkedHashMap<Integer, String>();

        try {
            List<Drive> driveList=driveService.getDrives();
            if(driveList!=null) {
                for (Drive drive : driveList) {
                    dropDown1.put(drive.getIdDrive(), drive.getDriveName());
                }
            }
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        return dropDown1;
    }

    private Map<Integer, String> getCampaignTypes() {
        Map<Integer, String> dropDown2 = new LinkedHashMap<Integer, String>();

        try {
            List<Campaigntype> campaignTypeList=campaignTypeService.getCampaignTypes();
            if(campaignTypeList!=null) {
                for (Campaigntype campaigntype : campaignTypeList) {
                    dropDown2.put(campaigntype.getIdCampaignType(), campaigntype.getCampaignType());
                }
            }
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        return dropDown2;
    }

    private Map<Integer, String> getClients() {
        Map<Integer, String> clientDdl = new LinkedHashMap<Integer, String>();

        try {
            List<Client> clients = clientService.getClients() ;
            clientDdl.put(0, "-- Select a Client --");
            if (clients != null) {
                for (Client client : clients) {
                    clientDdl.put(client.getIdClient(), client.getClientName());
                }
            }
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }
        return clientDdl;
    }

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public ModelAndView  print(ModelAndView modelAndView) {

        List<CampaignReport> campaignReportList = new ArrayList<CampaignReport>();
        List<Campaign> campaignList= null;
        try {
            campaignList = campaignService.getCampaigns();
        } catch (HelixServiceException e) {
            e.printStackTrace();
        }

        for(Campaign campaign: campaignList){
            CampaignReport campaignReport =new CampaignReport();
            campaignReport.setName("Campaign Name");
            campaignReport.setFormNumber(campaign.getFormNo());
            if(campaign.getCampaigntype()!=null){
                campaignReport.setCampaignType(campaign.getCampaigntype().getCampaignType());}
            campaignReport.setYear(campaign.getCampaignYear());
            campaignReportList.add(campaignReport);
        }

        JRDataSource ds = new JRBeanCollectionDataSource(campaignReportList);


        // In order to use Spring's built-in Jasper support,
        // We are required to pass our datasource as a map parameter
        // parameterMap is the Model of our application
        Map<String,Object> parameterMap = new HashMap<String,Object>();
        parameterMap.put("datasource", ds);

        // pdfReport is the View of our application
        // This is declared inside the /WEB-INF/jasper-views.xml
        modelAndView = new ModelAndView("pdfReportCampaign", parameterMap);
        // Return the View and the Model combined
        return modelAndView;
    }



}
