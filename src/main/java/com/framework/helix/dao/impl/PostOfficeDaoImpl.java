package com.framework.helix.dao.impl;

import com.framework.helix.dao.PostOfficeDao;
import com.framework.helix.entity.Postoffice;
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

import java.util.Date;
import java.util.List;

@Repository("postOfficeDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class PostOfficeDaoImpl implements PostOfficeDao{

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
    public void deletePostOfficeDetails(int idPostoffice) throws HelixDaoException {
        try {
            hibernateTemplate.delete(hibernateTemplate.get(Postoffice.class,idPostoffice));
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete post office details failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void savePostOfficeDetails(Postoffice postoffice)  throws HelixDaoException {
        try {
            hibernateTemplate.save(postoffice);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save post office details failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updatePostOfficeDetails(Postoffice postoffice)  throws HelixDaoException {
        try {
            hibernateTemplate.update(postoffice);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update post office details failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void saveOrUpdatePostOfficeDetails(Postoffice postoffice) throws HelixDaoException {
        try {
            hibernateTemplate.saveOrUpdate(postoffice);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save or update post office details failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deletePostOfficeDetails(Postoffice postoffice) throws HelixDaoException {
        try {
            hibernateTemplate.delete(postoffice);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete post office details failed!", e);
        }
    }

    public Postoffice getPostOfficeDetails(Integer idPostoffice) throws HelixDaoException {
        try {
            return (Postoffice) hibernateTemplate.get(Postoffice.class, idPostoffice);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get Post office details failed!", e);
        }
    }

    public List<Postoffice> getPostOfficeDetails() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Postoffice.class)
                    .addOrder(Order.asc("idPostoffice"))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get post office details failed!", e);
        }
    }

    public String getLastPostOfficeDetailsId() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Postoffice.class)
                    .setProjection(Projections.max("idPostoffice"))
                    .uniqueResult()
                    .toString();

        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get idPostoffice failed!", e);
        }
    }

    public  List<Postoffice> getPostOfficeDetailsByClientId(Integer idClient) throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Postoffice.class)
                    .add(Restrictions.eq("client.idClient", idClient))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get post office Details failed!", e);
        }
    }

    public  List<Postoffice> getPostOfficeDetailsForDrivable(Integer idClient,String currentYear) throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Postoffice.class)
                    .add(Restrictions.eq("client.idClient", idClient))
                    .add(Restrictions.isNotNull("DateLastPickup"))
                    .add(Restrictions.eq("pickupmethod.idPickupMethod",1))
                    .add(Restrictions.eq("client.idClient", idClient))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get post office Details failed!", e);
        }
    }


    public  List<Postoffice> getPostOfficeDetailsForReShip(Integer idClient,String currentYear) throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Postoffice.class)
                    .add(Restrictions.eq("client.idClient", idClient))
                    .add(Restrictions.eq("pickupmethod.idPickupMethod",2))
                    .add(Restrictions.isNotNull("DateLastPickup"))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get post office Details failed!", e);
        }
    }
}
