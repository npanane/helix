package com.framework.helix.service;

import com.framework.helix.entity.Renewperiod;
import com.framework.helix.exception.HelixServiceException;

/**
 * Created by nuwan.n.bandara on 7/9/2014.
 */
public interface RenewPeriodsService {

    public Renewperiod getRenewPeriods(Integer idRenewPeriod) throws HelixServiceException;

}
