package com.framework.helix.dao.impl;

import com.framework.helix.dao.VendorTypesDao;
import com.framework.helix.entity.Vendortype;
import com.framework.helix.exception.HelixDaoException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("vendorTypesDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class VendorTypesDaoImpl implements VendorTypesDao {

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
    public void saveVendorTypes(Vendortype vendortype) throws HelixDaoException {
        try {
            hibernateTemplate.save(vendortype);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save vendor type failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deleteVendorTypes(int idVendorType) throws HelixDaoException{
        try {
            hibernateTemplate.delete(hibernateTemplate.get(Vendortype.class,idVendorType));
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete vendor type failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updateVendorTypes(Vendortype vendortype) throws HelixDaoException {
        try {
            hibernateTemplate.update(vendortype);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update vendor type failed!", e);
        }
    }

    public Vendortype getVendorTypes(int idVendorType) throws HelixDaoException {
        try {
            return (Vendortype) hibernateTemplate.get(Vendortype.class, idVendorType);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get Vendor type failed!", e);
        }
    }

    public String getLastVendorTypeId() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Vendortype.class)
                    .setProjection(Projections.max("idVendorType"))
                    .uniqueResult()
                    .toString();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get vendor Type Id failed!", e);
        }
    }
}
