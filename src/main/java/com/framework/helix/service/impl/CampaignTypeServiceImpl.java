package com.framework.helix.service.impl;

import com.framework.helix.dao.CampaignTypeDao;
import com.framework.helix.entity.Campaigntype;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.CampaignService;
import com.framework.helix.service.CampaignTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 9/2/2014.
 */
public class CampaignTypeServiceImpl implements CampaignTypeService{

    private CampaignTypeDao campaignTypeDao;

    @Autowired
    public void setCampaignTypeDao(CampaignTypeDao campaignTypeDao) {
        this.campaignTypeDao = campaignTypeDao;
    }


    public Campaigntype getCampaignType(Integer idCampaignType) throws HelixServiceException {
        try {
            return campaignTypeDao.getCampaignType(idCampaignType);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get campaign type.", e);
        }
    }

    public List<Campaigntype> getCampaignTypes() throws HelixServiceException {
        try {
            return campaignTypeDao.getCampaignTypes();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get campaign types.", e);
        }
    }


}
