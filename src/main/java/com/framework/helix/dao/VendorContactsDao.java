package com.framework.helix.dao;

import com.framework.helix.entity.VendorContact;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/13/2014.
 */
public interface VendorContactsDao {

    public void saveVendorContact(VendorContact vendorContact) throws HelixDaoException;

    public List<VendorContact> getVendorContacts(Integer idVendor) throws HelixDaoException;

    public void deleteVendorContact(int idVendorContact) throws HelixDaoException;

    public VendorContact getVendorContact(int idVendorContact) throws HelixDaoException;

    public void updateVendorContact(VendorContact vendorContact) throws HelixDaoException;
}
