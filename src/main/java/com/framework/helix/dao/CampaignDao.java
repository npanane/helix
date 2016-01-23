package com.framework.helix.dao;

import com.framework.helix.entity.Campaign;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by Mahaiya on 8/29/2014.
 */
public interface CampaignDao {

    public void saveCampaign(Campaign campaign)throws HelixDaoException;

    public void updateCampaign(Campaign campaign) throws HelixDaoException;

    public void deleteCampaign(Campaign campaign) throws HelixDaoException;

    public Campaign getCampaign(Integer idCampaign) throws HelixDaoException;

    public List<Campaign> getCampaignsByClientId(Integer clientId) throws HelixDaoException;

    public List<Campaign> getCampaigns() throws HelixDaoException;

    public List<Campaign> getDriveCampaignList(Integer idDrive) throws HelixDaoException;

    public String getLastCampaignId() throws HelixDaoException;





}
