package com.framework.helix.dao;

import com.framework.helix.entity.Vendor;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 5/29/2014.
 */
public interface VendorDao {

    public void saveVendor(Vendor vendor) throws HelixDaoException;

    public void updateVendor(Vendor vendor) throws HelixDaoException;

    public void saveOrUpdateVendor(Vendor vendor) throws HelixDaoException;

    public void deleteVendor(Vendor vendor) throws HelixDaoException;

    public Vendor getVendor(Integer idVendor) throws HelixDaoException;

    public List<Vendor> getVendors() throws HelixDaoException;

    public String getLastVendorId() throws HelixDaoException;

}
