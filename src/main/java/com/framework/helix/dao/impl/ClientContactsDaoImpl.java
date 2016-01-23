package com.framework.helix.dao.impl;

import com.framework.helix.dao.ClientContactsDao;
import com.framework.helix.entity.Clientcontact;
import com.framework.helix.exception.HelixDaoException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
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
 * Created by nuwan.n.bandara on 7/10/2014.
 */
@Repository("clientContactsDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class ClientContactsDaoImpl implements ClientContactsDao {

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
    public void saveClientContact(Clientcontact clientcontact) throws HelixDaoException {
        try {
            hibernateTemplate.save(clientcontact);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save client contact failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deleteClientContact(int idClientContact) throws HelixDaoException{
        try {
            hibernateTemplate.delete(hibernateTemplate.get(Clientcontact.class,idClientContact));
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete  client contacts  failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updateClientContact(Clientcontact clientcontact) throws HelixDaoException {
        try {
            hibernateTemplate.update(clientcontact);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update client contacts failed!", e);
        }
    }

    public List<Clientcontact> getClientContacts(Integer idClient) throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Clientcontact.class)
                    .add(Restrictions.eq("client.idClient", idClient))
                    .addOrder(Order.asc("FirstName"))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get client contacts failed!", e);
        }
    }

    public Clientcontact getClientContact(int idClientContact) throws HelixDaoException {
        try {
            return (Clientcontact) hibernateTemplate.get(Clientcontact.class, idClientContact);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get client contacts failed!", e);
        }
    }

    public Clientcontact getClientContactByName(String ContactName) throws HelixDaoException{
        try {
            return(Clientcontact) sessionFactory.getCurrentSession()
                    .createCriteria(Clientcontact.class)
                    .add(Restrictions.eq("FirstName", ContactName))
                    .uniqueResult();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get client contacts failed!", e);
        }
    }
}
