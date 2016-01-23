package com.framework.helix.service.impl;

import com.framework.helix.dao.PickUpMethodsDao;
import com.framework.helix.entity.Pickupmethod;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.PickUpMethodsService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by nuwan.n.bandara on 7/9/2014.
 */
public class PickUpMethodsServiceImpl implements PickUpMethodsService{

    private PickUpMethodsDao pickUpMethodsDao;

    @Autowired
    public void setPickUpMethodsDao(PickUpMethodsDao pickUpMethodsDao) {
        this.pickUpMethodsDao = pickUpMethodsDao;
    }

    public Pickupmethod getPickUpMethod(Integer idPickupMethod) throws HelixServiceException {
        try {
            return pickUpMethodsDao.getPickUpMethod(idPickupMethod);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get pickup method.", e);
        }
    }

}
