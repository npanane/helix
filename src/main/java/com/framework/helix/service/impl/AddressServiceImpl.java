package com.framework.helix.service.impl;

import com.framework.helix.dao.AddressDao;
import com.framework.helix.entity.Address;
import com.framework.helix.entity.State;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 5/26/2014.
 */
public class AddressServiceImpl implements AddressService{

    private AddressDao addressDao;

    @Autowired
    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public void deleteAddress(int idAddress) throws HelixServiceException {
         try {
             addressDao.deleteAddress(idAddress);
         }
         catch (Exception e) {
             throw new HelixServiceException("Unable to delete address details.", e);
         }
    }

    public void saveAddress(Address address) throws HelixServiceException {
        try {
            addressDao.saveAddress(address);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save address.", e);
        }
    }

    public void updateAddress(Address address) throws HelixServiceException {
        try {
            addressDao.updateAddress(address);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to update address.", e);
        }
    }

    public void saveOrUpdateAddress(Address address) throws HelixServiceException {
        try {
            addressDao.saveOrUpdateAddress(address);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save or update address.", e);
        }
    }

    public void deleteAddress(Address address) throws HelixServiceException {
        try {
            addressDao.deleteAddress(address);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to delete address.", e);
        }
    }

    public Address getAddress(Integer idAddress) throws HelixServiceException {
        try {
            return addressDao.getAddress(idAddress);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get address.", e);
        }
    }

    public Address getAddress(Integer idAddress, Boolean createInstanceIfNotExists) throws HelixServiceException {
        try {
            Address address = addressDao.getAddress(idAddress);
            if (createInstanceIfNotExists && address == null) {
                address = new Address();
            }
            return address;
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get address.", e);
        }
    }

    public List<Address> getAddresses() throws HelixServiceException {
        try {
            return addressDao.getAddresses();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get addresses.", e);
        }
    }

    public String getLastAddressId() throws HelixServiceException {
        try {
            return addressDao.getLastAddressId();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get addressId.", e);
        }
    }

    @Override
    public List<State> getStates() throws HelixServiceException {
        try {
            return addressDao.getStates();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get states.", e);
        }
    }

    @Override
    public State getState(Integer idState) throws HelixServiceException {
        try {
            return addressDao.getState(idState);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get state.", e);
        }
    }
}
