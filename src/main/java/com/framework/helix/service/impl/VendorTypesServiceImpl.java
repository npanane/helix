package com.framework.helix.service.impl;

import com.framework.helix.dao.VendorTypesDao;
import com.framework.helix.entity.Vendortype;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.VendorTypesService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by nuwan.n.bandara on 7/13/2014.
 */
public class VendorTypesServiceImpl implements VendorTypesService {

    private VendorTypesDao vendorTypesDao;

    @Autowired
    public void setVendorTypesDao(VendorTypesDao vendorTypesDao) {
        this.vendorTypesDao = vendorTypesDao;
    }

    public void saveVendorTypes(Vendortype vendortype) throws HelixServiceException {
        try {
            vendorTypesDao.saveVendorTypes(vendortype);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save vendor type.", e);
        }
    }

    public void  deleteVendorTypes(int idVendorType) throws HelixServiceException {
        try {
            vendorTypesDao.deleteVendorTypes(idVendorType);
        }
        catch (Exception e) {
            throw new HelixServiceException("Unable to delete Vendor Type.", e);
        }
    }

    public Vendortype getVendorTypes(int idVendorType) throws HelixServiceException {
        try {
            return vendorTypesDao.getVendorTypes(idVendorType);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get Vendor Type.", e);
        }
    }

    public void updateVendorTypes(Vendortype vendortype) throws HelixServiceException {
        try {
            vendorTypesDao.updateVendorTypes(vendortype);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get vendor type.", e);
        }
    }

    public String getLastVendorTypeId() throws HelixServiceException {
        try {
            return vendorTypesDao.getLastVendorTypeId();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get vendor type id.", e);
        }
    }

}
