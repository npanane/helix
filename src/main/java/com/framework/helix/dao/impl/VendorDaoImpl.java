package com.framework.helix.dao.impl;

import com.framework.helix.dao.VendorDao;
import com.framework.helix.entity.Vendor;
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

@Repository("vendorDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class VendorDaoImpl implements VendorDao {

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
    public void saveVendor(Vendor vendor) throws HelixDaoException {
        try {
            hibernateTemplate.save(vendor);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save vendor failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updateVendor(Vendor vendor) throws HelixDaoException {
        try {
            hibernateTemplate.update(vendor);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update vendor failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void saveOrUpdateVendor(Vendor vendor) throws HelixDaoException {
        try {
            hibernateTemplate.saveOrUpdate(vendor);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save or update vendor failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deleteVendor(Vendor vendor) throws HelixDaoException {
        try {
            hibernateTemplate.delete(vendor);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete vendor failed!", e);
        }
    }

    public Vendor getVendor(Integer idVendor) throws HelixDaoException {
        try {
            return (Vendor) hibernateTemplate.get(Vendor.class, idVendor);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get vendor failed!", e);
        }
    }

    public List<Vendor> getVendors() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Vendor.class)
                    .addOrder(Order.asc("VendorName"))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get vendors failed!", e);
        }
    }

    public String getLastVendorId() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Vendor.class)
                    .setProjection(Projections.max("idVendor"))
                    .uniqueResult()
                    .toString();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get VendorId failed!", e);
        }
    }
}