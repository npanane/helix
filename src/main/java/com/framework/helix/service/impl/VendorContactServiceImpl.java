package com.framework.helix.service.impl;

import com.framework.helix.dao.VendorContactsDao;
import com.framework.helix.entity.VendorContact;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.VendorContactService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/13/2014.
 */
public class VendorContactServiceImpl implements VendorContactService {

    private VendorContactsDao vendorContactsDao;

    @Autowired
    public void setVendorContactsDao(VendorContactsDao vendorContactsDao) {
        this.vendorContactsDao = vendorContactsDao;
    }

    public void saveVendorContact(VendorContact vendorContact) throws HelixServiceException {
        try {
            vendorContactsDao.saveVendorContact(vendorContact);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save vendor contact.", e);
        }
    }

    public List<VendorContact> getVendorContacts(Integer idVendor) throws HelixServiceException {
        try {
            return vendorContactsDao.getVendorContacts(idVendor);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get vendor contacts.", e);
        }
    }

    public void deleteVendorContact(int idVendorContact) throws HelixServiceException {
        try {
            vendorContactsDao.deleteVendorContact(idVendorContact);
        }
        catch (Exception e) {
            throw new HelixServiceException("Unable to delete vendor contacts.", e);
        }
    }

    public VendorContact getVendorContact(int idVendorContact) throws HelixServiceException {
        try {
            return vendorContactsDao.getVendorContact(idVendorContact);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get vendor contacts.", e);
        }
    }

    public void updateVendorContact(VendorContact vendorContact) throws HelixServiceException {
        try {
            vendorContactsDao.updateVendorContact(vendorContact);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get  vendor contacts.", e);
        }
    }


}
