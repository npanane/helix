package com.framework.helix.service;

import com.framework.helix.entity.Address;
import com.framework.helix.entity.State;
import com.framework.helix.exception.HelixServiceException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 5/26/2014.
 */
public interface AddressService {

    public void deleteAddress(int idAddress) throws HelixServiceException;

    public void saveAddress(Address address) throws HelixServiceException;

    public void updateAddress(Address address) throws HelixServiceException;

    public void saveOrUpdateAddress(Address address) throws HelixServiceException;

    public void deleteAddress(Address address) throws HelixServiceException;

    public Address getAddress(Integer idAddress) throws HelixServiceException;

    public Address getAddress(Integer idAddress, Boolean createInstanceIfNotExists) throws HelixServiceException;

    public List<Address> getAddresses() throws HelixServiceException;

    public String getLastAddressId() throws HelixServiceException;

    public List<State> getStates() throws HelixServiceException;

    public State getState(Integer idState) throws HelixServiceException;
}
