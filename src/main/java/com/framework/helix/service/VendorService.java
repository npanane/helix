package com.framework.helix.service;

import com.framework.helix.entity.Vendor;
import com.framework.helix.exception.HelixServiceException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 5/29/2014.
 */
public interface VendorService {

    public void saveVendor(Vendor vendor) throws HelixServiceException;

    public void updateVendor(Vendor vendor) throws HelixServiceException;

    public void saveOrUpdateVendor(Vendor vendor) throws HelixServiceException;

    public void deleteVendor(Vendor vendor) throws HelixServiceException;

    public Vendor getVendor(Integer idVendor) throws HelixServiceException;

    public List<Vendor> getVendors() throws HelixServiceException;

    public String getLastVendorId() throws HelixServiceException;

}
