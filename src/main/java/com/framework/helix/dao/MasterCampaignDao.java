package com.framework.helix.dao;

import com.framework.helix.entity.MasterCampaign;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/7/2014.
 */
public interface MasterCampaignDao {

    public void deleteCampaignDetails(int idCampaign) throws HelixDaoException;

    public void saveCampaignDetails(MasterCampaign masterCampaign) throws HelixDaoException;

    public void updateCampaignDetails(MasterCampaign masterCampaign) throws HelixDaoException;

    public void saveOrUpdateCampaignDetails(MasterCampaign masterCampaign) throws HelixDaoException;

    public void deleteCampaignDetails(MasterCampaign masterCampaign) throws HelixDaoException;

    public MasterCampaign getCampaignDetails(Integer idCampaign) throws HelixDaoException;

    public List<MasterCampaign> getCampaignDetails() throws HelixDaoException;

    public String getLastCampaignDetailsId() throws HelixDaoException;

    public List<MasterCampaign> getCampaignDetailsCI(Integer idClient)throws HelixDaoException;

    public List<MasterCampaign> getCampaignDetailsForSpring() throws HelixDaoException;

    public List<MasterCampaign> getCampaignDetailsForFall() throws HelixDaoException;
}
