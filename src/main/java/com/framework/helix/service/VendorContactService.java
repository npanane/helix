package com.framework.helix.service;

import com.framework.helix.entity.VendorContact;
import com.framework.helix.exception.HelixServiceException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/13/2014.
 */
public interface VendorContactService {

    public void saveVendorContact(VendorContact vendorContact) throws HelixServiceException;

    public List<VendorContact> getVendorContacts(Integer idVendor) throws HelixServiceException;

    public void deleteVendorContact(int idVendorContact) throws HelixServiceException;

    public VendorContact getVendorContact(int idVendorContact) throws HelixServiceException;

    public void updateVendorContact(VendorContact vendorContact) throws HelixServiceException;

}
