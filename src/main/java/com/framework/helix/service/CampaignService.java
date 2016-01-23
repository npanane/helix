package com.framework.helix.service;

import com.framework.helix.entity.Campaign;
import com.framework.helix.exception.HelixServiceException;

import java.util.List;

/**
 * Created by Mahaiya on 8/29/2014.
 */
public interface CampaignService {

    public void saveCampaign(Campaign campaign)throws HelixServiceException;

    public void updateCampaign(Campaign campaign) throws HelixServiceException;

    public void deleteCampaign(Campaign campaign) throws HelixServiceException;

    public Campaign getCampaign(Integer idCampaign) throws HelixServiceException;

    public List<Campaign> getCampaignsByClientId(Integer clientId) throws HelixServiceException;

    public List<Campaign> getCampaigns() throws HelixServiceException;

    public List<Campaign> getDriveCampaignList(Integer idDrive) throws HelixServiceException;

    public String getLastCampaignId() throws HelixServiceException;

}
