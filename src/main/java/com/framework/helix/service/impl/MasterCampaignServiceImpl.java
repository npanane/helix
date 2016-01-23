package com.framework.helix.service.impl;

import com.framework.helix.dao.MasterCampaignDao;
import com.framework.helix.entity.Address;
import com.framework.helix.entity.MasterCampaign;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.MasterCampaignService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MasterCampaignServiceImpl implements MasterCampaignService{

    private MasterCampaignDao masterCampaignDao;

    @Autowired
    public void setMasterCampaignDao(MasterCampaignDao masterCampaignDao) {
        this.masterCampaignDao = masterCampaignDao;
    }

    public void deleteCampaignDetails(int idCampaign) throws HelixServiceException {
        try {
            masterCampaignDao.deleteCampaignDetails(idCampaign);
        }
        catch (Exception e) {
            throw new HelixServiceException("Unable to delete campaign details.", e);
        }
    }

    public void saveCampaignDetails(MasterCampaign masterCampaign) throws HelixServiceException {
        try {
            masterCampaignDao.saveCampaignDetails(masterCampaign);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save campaign details.", e);
        }
    }

    public void updateCampaignDetails(MasterCampaign masterCampaign) throws HelixServiceException {
        try {
            masterCampaignDao.updateCampaignDetails(masterCampaign);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to update campaign details.", e);
        }
    }

    public void saveOrUpdateCampaignDetails(MasterCampaign masterCampaign) throws HelixServiceException {
        try {
            masterCampaignDao.saveOrUpdateCampaignDetails(masterCampaign);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save or update campaign details.", e);
        }
    }

    public void deleteCampaignDetails(MasterCampaign masterCampaign) throws HelixServiceException {
        try {
            masterCampaignDao.deleteCampaignDetails(masterCampaign);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to delete campaign details.", e);
        }
    }

    public MasterCampaign getCampaignDetails(Integer idCampaign) throws HelixServiceException {
        try {
            return masterCampaignDao.getCampaignDetails(idCampaign);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get campaign details.", e);
        }
    }

    public List<MasterCampaign> getCampaignDetails() throws HelixServiceException {
        try {
            return masterCampaignDao.getCampaignDetails();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get campaign details.", e);
        }
    }

    public String getLastCampaignDetailsId() throws HelixServiceException {
        try {
            return masterCampaignDao.getLastCampaignDetailsId();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get campaignId.", e);
        }
    }

    public List<MasterCampaign> getCampaignDetailsCI(Integer idClient) throws HelixServiceException {
        try {
            return masterCampaignDao.getCampaignDetailsCI(idClient);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get campaign details.", e);
        }
    }

    public List<MasterCampaign> getCampaignDetailsForSpring() throws HelixServiceException {
        try {
            return masterCampaignDao.getCampaignDetailsForSpring();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get campaign details.", e);
        }
    }

    public List<MasterCampaign> getCampaignDetailsForFall() throws HelixServiceException {
        try {
            return masterCampaignDao.getCampaignDetailsForFall();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get campaign details.", e);
        }
    }

    public Document buildCampaignDetailsResponseXML(Integer ClientId) throws HelixServiceException {
        List<MasterCampaign> masterCampaignList = getCampaignDetailsCI(ClientId);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("campaign");
        if (masterCampaignList !=null) {
            for (MasterCampaign masterCampaign : masterCampaignList) {
                Element element = root.addElement("details");
                element.addAttribute("IdCampaign", String.valueOf(masterCampaign.getIdCampaign()));
                element.addAttribute("rank", masterCampaign.getRank());
                element.addAttribute("firstName", masterCampaign.getFirstName());
                element.addAttribute("lastName", masterCampaign.getLastName());
                element.addAttribute("title", masterCampaign.getTitle());
                element.addAttribute("phone", (masterCampaign.getContact() != null)
                        ? masterCampaign.getContact().getMain() : "");
                if (masterCampaign.getNEPVoiceMail() != null) {
                    element.addAttribute("NEPVoiceMail", masterCampaign.getNEPVoiceMail().toString());
                }
                Address address = masterCampaign.getAddress();
                element.addAttribute("address1", ((address != null) ? address.getStreetName() : ""));
                element.addAttribute("city", (address != null) ? masterCampaign.getAddress().getCity() : "");
                element.addAttribute("state", ((address != null && address.getState() != null)
                        ? String.valueOf(address.getState().getIdState()) : "0"));
                element.addAttribute("zip", (address != null) ? address.getZipCode() : "");
                element.addAttribute("taxID", masterCampaign.getTaxID());
                element.addAttribute("friendOf", masterCampaign.getFriendsOf());
                element.addAttribute("season", masterCampaign.getSeason());
                element.addAttribute("clientWebsite", (masterCampaign.getContact() != null)
                        ? masterCampaign.getContact().getWebURL() : "");
                element.addAttribute("baseFormNo", masterCampaign.getBaseFormNo());
                element.addAttribute("donationWebsite", masterCampaign.getDonationWebsite());
                element.addAttribute("letterType", masterCampaign.getLetterType());
                element.addAttribute("pictureTitle", masterCampaign.getPictureTitle());
                element.addAttribute("clientPercent", masterCampaign.getClientPercent());
                element.addAttribute("bankAcc", masterCampaign.getBankAccountNo());
                element.addAttribute("locationNo", masterCampaign.getLocationNo());
                if (masterCampaign.getFlTaxDeductible() != null) {
                    element.addAttribute("taxDeductible", masterCampaign.getFlTaxDeductible().toString());
                }
                if (masterCampaign.getContact() != null && masterCampaign.getContact().getIdContact() != null) {
                    element.addAttribute("IdContact", masterCampaign.getContact().getIdContact().toString());
                }
                if (masterCampaign.getAddress()  != null && masterCampaign.getAddress().getIdAddress() != null) {
                    element.addAttribute("IdAddress", masterCampaign.getAddress().getIdAddress().toString());
                }
            }
        }
        return document;
    }

}
