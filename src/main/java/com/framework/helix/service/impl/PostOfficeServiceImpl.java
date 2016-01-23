package com.framework.helix.service.impl;

import com.framework.helix.dao.PostOfficeDao;
import com.framework.helix.entity.Postoffice;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.PostOfficeService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/8/2014.
 */
public class PostOfficeServiceImpl implements PostOfficeService{

    private static final String dfs = "MM-dd-yyyy";
    private static DateFormat df = new SimpleDateFormat(dfs);

    private PostOfficeDao postOfficeDao;

    @Autowired
    public void setMasterCampaignDao(PostOfficeDao postOfficeDao) {
        this.postOfficeDao = postOfficeDao;
    }

    public void deletePostOfficeDetails(int idPostoffice) throws HelixServiceException {
        try {
            postOfficeDao.deletePostOfficeDetails(idPostoffice);
        }
        catch (Exception e) {
            throw new HelixServiceException("Unable to delete post office details.", e);
        }
    }

    public void savePostOfficeDetails(Postoffice postoffice) throws HelixServiceException {
        try {
            postOfficeDao.savePostOfficeDetails(postoffice);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save post office details.", e);
        }
    }

    public void  updatePostOfficeDetails(Postoffice postoffice) throws HelixServiceException {
        try {
            postOfficeDao.updatePostOfficeDetails(postoffice);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to update post office details.", e);
        }
    }

    public void saveOrUpdatePostOfficeDetails(Postoffice postoffice) throws HelixServiceException {
        try {
            postOfficeDao.saveOrUpdatePostOfficeDetails(postoffice);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save or update post office details.", e);
        }
    }

    public void deletePostOfficeDetails(Postoffice postoffice) throws HelixServiceException {
        try {
            postOfficeDao.deletePostOfficeDetails(postoffice);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to delete post office details.", e);
        }
    }

    public Postoffice getPostOfficeDetails(Integer idPostoffice) throws HelixServiceException {
        try {
            return postOfficeDao.getPostOfficeDetails(idPostoffice);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get post office details.", e);
        }
    }

    public List<Postoffice> getPostOfficeDetails() throws HelixServiceException {
        try {
            return postOfficeDao.getPostOfficeDetails();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get post office details.", e);
        }
    }

    public String getLastPostOfficeDetailsId() throws HelixServiceException {
        try {
            return postOfficeDao.getLastPostOfficeDetailsId();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get postofficeID .", e);
        }
    }

    public List<Postoffice> getPostOfficeDetailsCI(Integer idClient) throws HelixServiceException {
        try {
            return postOfficeDao.getPostOfficeDetailsByClientId(idClient);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get post office details.", e);
        }
    }

    public List<Postoffice> getPostOfficeDetailsForDrivable(Integer idClient,String currentYear) throws HelixServiceException {
        try {
            return postOfficeDao.getPostOfficeDetailsForDrivable(idClient,currentYear);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get post office details.", e);
        }
    }

    public List<Postoffice> getPostOfficeDetailsForReShip(Integer idClient,String currentYear) throws HelixServiceException {
        try {
            return postOfficeDao.getPostOfficeDetailsForReShip(idClient,currentYear);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get post office details.", e);
        }
    }

    public Document buildPostOfficeDetailsResponseXML(Integer ClientId) throws HelixServiceException {
        List<Postoffice> postOfficeList= getPostOfficeDetailsCI(ClientId);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("postOffice");
        if (postOfficeList!=null) {
            for (Postoffice postoffice : postOfficeList) {
                Element element = root.addElement("details");
                element.addAttribute("IdPostOffice", String.valueOf(postoffice.getIdPostoffice()));
                element.addAttribute("lastCall", (postoffice.getDateLastCalled() != null && !"".equals(postoffice.getDateLastCalled()))
                        ? df.format(postoffice.getDateLastCalled()) : null);
                element.addAttribute("lastPickup", (postoffice.getDateLastPickup() != null && !"".equals(postoffice.getDateLastPickup()))
                        ? df.format(postoffice.getDateLastPickup()) : null );
                element.addAttribute("lastPaid", (postoffice.getDateLastPaid() != null && !"".equals(postoffice.getDateLastPaid()))
                        ? df.format(postoffice.getDateLastPaid()) : null);
                element.addAttribute("dateExpiration", (postoffice.getDateExpiration() != null && !"".equals(postoffice.getDateExpiration()))
                        ? df.format(postoffice.getDateExpiration()) : null);
                element.addAttribute("renewalPeriod",postoffice.getRenewalPeriod().toString() );
                element.addAttribute("boxSize", postoffice.getBoxSize());
                element.addAttribute("renewalCost", String.valueOf(postoffice.getRenewalCost()));
                element.addAttribute("ghost", postoffice.getGhostNo());
                element.addAttribute("notes", postoffice.getNotes());
                element.addAttribute("flHideAlert", String.valueOf(postoffice.getFlHideAlert()));
                element.addAttribute("address", (postoffice.getAddress() != null)
                        ? postoffice.getAddress().getStreetName() : "");
                element.addAttribute("city", ( (postoffice.getAddress() != null && postoffice.getAddress().getCity() != null)
                        ? postoffice.getAddress().getCity() : ""));
                element.addAttribute("POstate", ((postoffice.getAddress() != null && postoffice.getAddress().getState() != null)
                        ? String.valueOf(postoffice.getAddress().getState().getIdState()) : "0"));
                element.addAttribute("zip", ((postoffice.getAddress() != null && postoffice.getAddress().getZipCode() != null)
                        ? postoffice.getAddress().getZipCode() : ""));
                element.addAttribute("postOfficePhone",((postoffice.getContact() != null
                        && postoffice.getContact().getMain() != null) ? postoffice.getContact().getMain() : null));
                element.addAttribute("postOfficeFax", ((postoffice.getContact() != null
                        && postoffice.getContact().getFax() != null) ? postoffice.getContact().getFax() : null));
                element.addAttribute("contact1", postoffice.getContactPerson1());
                element.addAttribute("contact2", postoffice.getContactPerson2());
                element.addAttribute("postAddressId", (postoffice.getAddress() != null && postoffice.getAddress().getIdAddress() != null)
                        ? String.valueOf(postoffice.getAddress().getIdAddress()) : null);
                element.addAttribute("postContactId", (postoffice.getContact() != null && postoffice.getContact().getIdContact() != null)
                        ? String.valueOf(postoffice.getContact().getIdContact()) : null);
                element.addAttribute("postPickUpId", (postoffice.getPickupmethod() != null && postoffice.getPickupmethod().getIdPickupMethod() != null)
                        ? String.valueOf(postoffice.getPickupmethod().getIdPickupMethod()) : null);
            }
        }
        return document;
    }
}
