package com.framework.helix.dao;

import com.framework.helix.entity.Vendortype;
import com.framework.helix.exception.HelixDaoException;

/**
 * Created by nuwan.n.bandara on 7/13/2014.
 */
public interface VendorTypesDao {

    public void saveVendorTypes(Vendortype vendortype) throws HelixDaoException;

    public void deleteVendorTypes(int idVendorType) throws HelixDaoException;

    public Vendortype getVendorTypes(int idVendorType) throws HelixDaoException;

    public void updateVendorTypes(Vendortype vendortype) throws HelixDaoException;

    public String getLastVendorTypeId() throws HelixDaoException;

}
