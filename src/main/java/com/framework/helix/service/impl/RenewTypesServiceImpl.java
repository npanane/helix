package com.framework.helix.service.impl;

import com.framework.helix.dao.RenewTypesDao;
import com.framework.helix.entity.Renewtype;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.RenewTypesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/10/2014.
 */
public class RenewTypesServiceImpl implements RenewTypesService {

    private RenewTypesDao renewTypesDao;

    @Autowired
    public void setRenewPeriodsDao(RenewTypesDao renewTypesDao) {
        this.renewTypesDao = renewTypesDao;
    }

    public Renewtype getRenewTypes(Integer idRenewType) throws HelixServiceException {
        try {
            return renewTypesDao.getRenewTypes(idRenewType);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get renew types.", e);
        }
    }

    public List<Renewtype> getRenewTypes() throws HelixServiceException {
        try {
            return renewTypesDao.getRenewTypes();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get renew types.", e);
        }
    }
}
