package com.framework.helix.service.impl;

import com.framework.helix.dao.CampaignDao;
import com.framework.helix.entity.Campaign;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Mahaiya on 8/29/2014.
 */
public class CampaignServiceImpl implements CampaignService {

    private CampaignDao campaignDao;

    @Autowired
    public void setCampaignDao(CampaignDao campaignDao) {
        this.campaignDao = campaignDao;
    }

    public void saveCampaign(Campaign campaign) throws HelixServiceException {
        try {
            campaignDao.saveCampaign(campaign);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save campaign.", e);
        }
    }

    public void updateCampaign(Campaign campaign) throws HelixServiceException {
        try {
            campaignDao.updateCampaign(campaign);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to update campaign.", e);
        }
    }

    public void deleteCampaign(Campaign campaign) throws HelixServiceException {
        try {
            campaignDao.deleteCampaign(campaign);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to delete campaign.", e);
        }
    }

    public Campaign getCampaign(Integer idCampaign) throws HelixServiceException {
        try {
            return campaignDao.getCampaign(idCampaign);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get campaign.", e);
        }
    }

    public List<Campaign> getCampaignsByClientId(Integer clientId) throws HelixServiceException {
        try {
            return campaignDao.getCampaignsByClientId(clientId);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get campaigns for client id ["+ clientId + "]", e);
        }
    }

    public List<Campaign> getCampaigns() throws HelixServiceException {
        try {
            return campaignDao.getCampaigns();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get campaigns.", e);
        }
    }

    public List<Campaign> getDriveCampaignList(Integer idDrive) throws HelixServiceException {
        try {
            return campaignDao.getDriveCampaignList(idDrive);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get campaigns.", e);
        }
    }

    public String getLastCampaignId() throws HelixServiceException {
        try {
            return campaignDao.getLastCampaignId();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get campaignId.", e);
        }
    }


}
