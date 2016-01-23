package com.framework.helix.dao.impl;

import com.framework.helix.dao.ClientDao;
import com.framework.helix.entity.Client;
import com.framework.helix.entity.ClientType;
import com.framework.helix.exception.HelixDaoException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
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

@Repository("clientDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class ClientDaoImpl implements ClientDao {

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
    public void saveClient(Client client) throws HelixDaoException {
        try {
            hibernateTemplate.save(client);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save client failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updateClient(Client client) throws HelixDaoException {
        try {
            hibernateTemplate.update(client);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update client failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void saveOrUpdateClient(Client client) throws HelixDaoException {
        try {
            hibernateTemplate.saveOrUpdate(client);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save or update client failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deleteClient(Client client) throws HelixDaoException {
        try {
            hibernateTemplate.delete(client);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete client failed!", e);
        }
    }

    public Client getClient(Integer idClient) throws HelixDaoException {
        try {
            return (Client) hibernateTemplate.get(Client.class, idClient);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get client failed!", e);
        }
    }

    public List<Client> getClients() throws HelixDaoException {
        try {
            List<Client> clients = sessionFactory.getCurrentSession()
                    .createCriteria(Client.class)
                    .addOrder(Order.asc("ClientName"))
                    .list();
            return clients;
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get clients failed!", e);
        }
    }

    public List<Client> getClients(String status) throws HelixDaoException {
        try {
            Criteria clientCriteria = sessionFactory.getCurrentSession().createCriteria(Client.class);
            Criteria clientStatusCriteria = clientCriteria.createCriteria("clientstatus");
            clientStatusCriteria.add(Restrictions.eq("Status", status));
            List<Client> clients = clientCriteria.list();
            return clients;
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get clients failed!", e);
        }
    }

    public String  getLastClientsId() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Client.class)
                    .setProjection(Projections.max("idClient"))
                    .uniqueResult()
                    .toString();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get ClientId failed!", e);
        }
    }

    public List<Client> getClientsForPoBoxPickup(String season, Integer year) throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Client.class)
                    //.createAlias("postoffices", "postoffice")
                    //.add(Restrictions.eq("postoffice.DateLastPickup", "2014-12-01 00:00:00"))
                    .createAlias("masterCampaign", "masterCampaign")
                    .add(Restrictions.eq("masterCampaign.Season", season))
                    .list();

        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Unable to getClientsForPoBoxPickup(). ERROR: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ClientType> getClientType() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(ClientType.class)
                    .addOrder(Order.asc("idClientType"))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get client type failed!", e);
        }
    }
}
