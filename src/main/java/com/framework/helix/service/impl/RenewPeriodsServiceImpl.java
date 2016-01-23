package com.framework.helix.service.impl;

import com.framework.helix.dao.RenewPeriodsDao;
import com.framework.helix.entity.Renewperiod;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.RenewPeriodsService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by nuwan.n.bandara on 7/9/2014.
 */
public class RenewPeriodsServiceImpl implements RenewPeriodsService {

    private RenewPeriodsDao renewPeriodsDao;

    @Autowired
    public void setRenewPeriodsDao(RenewPeriodsDao renewPeriodsDao) {
        this.renewPeriodsDao = renewPeriodsDao;
    }

    public Renewperiod getRenewPeriods(Integer idRenewPeriod) throws HelixServiceException {
        try {
            return renewPeriodsDao.getRenewPeriods(idRenewPeriod);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get renew Periods.", e);
        }
    }

}
