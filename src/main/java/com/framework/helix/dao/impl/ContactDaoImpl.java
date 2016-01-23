package com.framework.helix.dao.impl;

import com.framework.helix.dao.ContactDao;
import com.framework.helix.entity.Contact;
import com.framework.helix.exception.HelixDaoException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 5/28/2014.
 */

@Repository("contactDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class ContactDaoImpl implements ContactDao {

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
    public void deleteContact(int idContact) throws HelixDaoException {
        try {
            hibernateTemplate.delete(hibernateTemplate.get(Contact.class, idContact));
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete contact failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void saveContact(Contact contact) throws HelixDaoException {
        try {
            hibernateTemplate.save(contact);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save contact failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updateContact(Contact contact) throws HelixDaoException {
        try {
            hibernateTemplate.update(contact);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update contact failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void saveOrUpdateContact(Contact contact) throws HelixDaoException {
        try {
            hibernateTemplate.saveOrUpdate(contact);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save or update contact failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deleteContact(Contact contact) throws HelixDaoException {
        try {
            hibernateTemplate.delete(contact);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete contact failed!", e);
        }
    }

    public Contact getContact(Integer idContact) throws HelixDaoException {
        try {
            return (Contact) hibernateTemplate.get(Contact.class, idContact);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get contact failed!", e);
        }
    }

    public List<Contact> getContacts(int clientId) throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Contact.class)
                    .add(Restrictions.eq("clients.idClient", clientId))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get contacts failed!", e);
        }
    }

    public List<Contact> getContacts() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Contact.class)
                    .addOrder(Order.asc("idContact"))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get contacts failed!", e);
        }
    }

    public String getLastContactId() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Contact.class)
                    .setProjection(Projections.max("idContact"))
                    .uniqueResult()
                    .toString();

        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get contactId failed!", e);
        }
    }
}
