package com.framework.helix.dao;

import com.framework.helix.entity.Pickupmethod;
import com.framework.helix.exception.HelixDaoException;

/**
 * Created by nuwan.n.bandara on 7/9/2014.
 */
public interface PickUpMethodsDao {

    public Pickupmethod getPickUpMethod(Integer idPickupMethod) throws HelixDaoException;

}
