package com.framework.helix.dao;

import com.framework.helix.entity.Address;
import com.framework.helix.entity.State;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 5/26/2014.
 */
public interface AddressDao {

    public void deleteAddress(int idAddress) throws HelixDaoException;

    public void saveAddress(Address address) throws HelixDaoException;

    public void updateAddress(Address address) throws HelixDaoException;

    public void saveOrUpdateAddress(Address address) throws HelixDaoException;

    public void deleteAddress(Address Address) throws HelixDaoException;

    public Address getAddress(Integer idAddress) throws HelixDaoException;

    public List<Address> getAddresses() throws HelixDaoException;

    public String getLastAddressId() throws HelixDaoException;

    public List<State> getStates() throws HelixDaoException;

    public State getState(Integer idState) throws HelixDaoException;

}
