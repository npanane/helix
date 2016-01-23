package com.framework.helix.dao;

import com.framework.helix.entity.Campaigntype;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 9/2/2014.
 */
public interface CampaignTypeDao {

    public Campaigntype getCampaignType(Integer idCampaignType) throws HelixDaoException;

    public List<Campaigntype> getCampaignTypes() throws HelixDaoException;


}
