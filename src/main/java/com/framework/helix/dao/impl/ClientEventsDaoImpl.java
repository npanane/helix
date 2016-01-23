package com.framework.helix.dao.impl;

import com.framework.helix.dao.ClientEventsDao;
import com.framework.helix.entity.Clientevent;
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

@Repository("clientEventsDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class ClientEventsDaoImpl implements ClientEventsDao {

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
    public void saveClientEvent(Clientevent clientevent) throws HelixDaoException {
        try {
            hibernateTemplate.save(clientevent);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save client event failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deleteClientEvent(int idClientEvent) throws HelixDaoException{
        try {
            hibernateTemplate.delete(hibernateTemplate.get(Clientevent.class,idClientEvent));
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete client event failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updateClientEvent(Clientevent clientevent) throws HelixDaoException {
        try {
            hibernateTemplate.update(clientevent);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update client event failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void saveOrUpdateClientEvent(Clientevent clientevent) throws HelixDaoException {
        try {
            hibernateTemplate.saveOrUpdate(clientevent);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update client event failed!", e);
        }
    }

    public List<Clientevent> getClientEvents(Integer idClient) throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Clientevent.class)
                    .add(Restrictions.eq("client.idClient", idClient))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get client event failed!", e);
        }
    }

    public Clientevent getClientEvent(int idClientEvent) throws HelixDaoException {
        try {
            return (Clientevent) hibernateTemplate.get(Clientevent.class, idClientEvent);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get client event failed!", e);
        }
    }
}
