package com.framework.helix.dao.impl;

import com.framework.helix.dao.VendorContactsDao;
import com.framework.helix.entity.VendorContact;
import com.framework.helix.exception.HelixDaoException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("vendorContactsDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class VendorContactsDaoImpl implements VendorContactsDao{

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
    public void  saveVendorContact(VendorContact vendorContact) throws HelixDaoException {
        try {
            hibernateTemplate.save(vendorContact);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save vendor contact failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deleteVendorContact(int idVendorContact) throws HelixDaoException{
        try {
            hibernateTemplate.delete(hibernateTemplate.get(VendorContact.class,idVendorContact));
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete  vendor contacts  failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updateVendorContact(VendorContact vendorContact) throws HelixDaoException {
        try {
            hibernateTemplate.update(vendorContact);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update vendor contacts failed!", e);
        }
    }

    public VendorContact getVendorContact(int idVendorContact) throws HelixDaoException {
        try {
            return (VendorContact) hibernateTemplate.get(VendorContact.class, idVendorContact);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get vendor contacts failed!", e);
        }
    }

    public List<VendorContact> getVendorContacts(Integer idVendor) throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(VendorContact.class)
                    .add(Restrictions.eq("vendor.idVendor", idVendor))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get vendor contacts failed!", e);
        }
    }
}
