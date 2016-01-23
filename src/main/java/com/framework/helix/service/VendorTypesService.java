package com.framework.helix.service;

import com.framework.helix.entity.Vendortype;
import com.framework.helix.exception.HelixServiceException;

/**
 * Created by nuwan.n.bandara on 7/13/2014.
 */
public interface VendorTypesService {

    public void saveVendorTypes(Vendortype vendortype) throws HelixServiceException;

    public void deleteVendorTypes(int idVendorType) throws HelixServiceException;

    public Vendortype getVendorTypes(int idVendorType) throws HelixServiceException;

    public void updateVendorTypes(Vendortype vendortype) throws HelixServiceException;

    public String getLastVendorTypeId() throws HelixServiceException;


}
