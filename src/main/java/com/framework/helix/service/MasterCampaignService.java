package com.framework.helix.service;

import com.framework.helix.entity.MasterCampaign;
import com.framework.helix.exception.HelixServiceException;
import org.dom4j.Document;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/7/2014.
 */
public interface MasterCampaignService {

    public void deleteCampaignDetails(int idCampaign) throws HelixServiceException;

    public void saveCampaignDetails(MasterCampaign masterCampaign) throws HelixServiceException;

    public void updateCampaignDetails(MasterCampaign masterCampaign) throws HelixServiceException;

    public void saveOrUpdateCampaignDetails(MasterCampaign masterCampaign) throws HelixServiceException;

    public void deleteCampaignDetails(MasterCampaign masterCampaign) throws HelixServiceException;

    public MasterCampaign getCampaignDetails(Integer idCampaign) throws HelixServiceException;

    public List<MasterCampaign> getCampaignDetails() throws HelixServiceException;

    public String getLastCampaignDetailsId() throws HelixServiceException;

    public List<MasterCampaign> getCampaignDetailsCI(Integer idClient)throws HelixServiceException;

    public List<MasterCampaign> getCampaignDetailsForSpring() throws HelixServiceException;

    public List<MasterCampaign> getCampaignDetailsForFall() throws HelixServiceException;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Document buildCampaignDetailsResponseXML(Integer ClientId) throws HelixServiceException;
}
