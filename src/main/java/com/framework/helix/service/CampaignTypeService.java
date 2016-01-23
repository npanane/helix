package com.framework.helix.service;

import com.framework.helix.entity.Campaigntype;
import com.framework.helix.exception.HelixServiceException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 9/2/2014.
 */
public interface CampaignTypeService {

    public Campaigntype getCampaignType(Integer idCampaignType) throws HelixServiceException;

    public List<Campaigntype> getCampaignTypes() throws HelixServiceException;


}
