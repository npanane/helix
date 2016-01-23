package com.framework.helix.dao;

import com.framework.helix.entity.Renewperiod;
import com.framework.helix.exception.HelixDaoException;

/**
 * Created by nuwan.n.bandara on 7/9/2014.
 */
public interface RenewPeriodsDao {

    public Renewperiod getRenewPeriods(Integer idRenewPeriod) throws HelixDaoException;
}
