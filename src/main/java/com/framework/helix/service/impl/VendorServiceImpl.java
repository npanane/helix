package com.framework.helix.service.impl;

import com.framework.helix.dao.VendorDao;
import com.framework.helix.entity.Vendor;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 5/29/2014.
 */
public class VendorServiceImpl implements VendorService {

    private VendorDao vendorDao;

    @Autowired
    public void setVendorDao(VendorDao vendorDao) {
        this.vendorDao = vendorDao;
    }

    public void saveVendor(Vendor vendor) throws HelixServiceException {
        try {
            vendorDao.saveVendor(vendor);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save vendor.", e);
        }
    }

    public void updateVendor(Vendor vendor) throws HelixServiceException {
        try {
            vendorDao.updateVendor(vendor);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to update vendor.", e);
        }
    }

    public void saveOrUpdateVendor(Vendor vendor) throws HelixServiceException {
        try {
            vendorDao.saveOrUpdateVendor(vendor);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save or update vendor.", e);
        }
    }

    public void deleteVendor(Vendor vendor) throws HelixServiceException {
        try {
            vendorDao.deleteVendor(vendor);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to delete vendor.", e);
        }
    }

    public Vendor getVendor(Integer idVendor) throws HelixServiceException {
        try {
            return vendorDao.getVendor(idVendor);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get vendor.", e);
        }
    }

    public List<Vendor> getVendors() throws HelixServiceException {
        try {
            return vendorDao.getVendors();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get vendors.", e);
        }
    }

    public String getLastVendorId() throws HelixServiceException {
        try {
            return vendorDao.getLastVendorId();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get vendorId.", e);
        }
    }
}
