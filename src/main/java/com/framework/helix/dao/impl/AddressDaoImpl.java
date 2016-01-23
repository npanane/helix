package com.framework.helix.dao.impl;

import com.framework.helix.dao.AddressDao;
import com.framework.helix.entity.Address;
import com.framework.helix.entity.State;
import com.framework.helix.exception.HelixDaoException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 5/26/2014.
 */

@Repository("addressDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class AddressDaoImpl implements AddressDao {

    private SessionFactory sessionFactory;
    private HibernateTemplate hibernateTemplate;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Transactional(readOnly = false)
    public void deleteAddress(int idAddress) throws HelixDaoException{
        try {
            hibernateTemplate.delete(hibernateTemplate.get(Address.class, idAddress));
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete address failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void saveAddress(Address address) throws HelixDaoException {
        try {
            hibernateTemplate.save(address);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save address failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updateAddress(Address address) throws HelixDaoException {
        try {
            hibernateTemplate.update(address);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update address failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void saveOrUpdateAddress(Address address) throws HelixDaoException {
        try {
            hibernateTemplate.saveOrUpdate(address);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save or update address failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deleteAddress(Address address) throws HelixDaoException {
        try {
            hibernateTemplate.delete(address);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete address failed!", e);
        }
    }

    public Address getAddress(Integer idAddress) throws HelixDaoException {
        try {
            return (Address) hibernateTemplate.get(Address.class, idAddress);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get address failed!", e);
        }
    }

    public List<Address> getAddresses() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Address.class)
                    .addOrder(Order.asc("idAddress"))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get Addresses failed!", e);
        }
    }

    public String getLastAddressId() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Address.class)
                    .setProjection(Projections.max("idAddress"))
                    .uniqueResult()
                    .toString();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get AddressId failed!", e);
        }
    }

    @Override
    public List<State> getStates() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(State.class).list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Reading state sailed due to " + e.getMessage(), e);
        }
    }

    @Override
    public State getState(Integer idState) throws HelixDaoException {
        try {
            return (State) hibernateTemplate.get(State.class, idState);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get state failed!", e);
        }
    }
}
