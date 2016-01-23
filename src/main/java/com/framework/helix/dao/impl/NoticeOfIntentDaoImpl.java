package com.framework.helix.dao.impl;

import com.framework.helix.dao.NoticeOfIntentDao;
import com.framework.helix.entity.Noticeofintent;
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

@Repository("noticeOfIntentDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class NoticeOfIntentDaoImpl implements NoticeOfIntentDao{

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
    public void deleteNoticeDetails(int idIntent) throws HelixDaoException {
        try {
            hibernateTemplate.delete(hibernateTemplate.get(Noticeofintent.class,idIntent));
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete notice of intent details failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void saveNoticeDetails(Noticeofintent noticeofintent)  throws HelixDaoException {
        try {
            hibernateTemplate.save(noticeofintent);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save notice of intent details failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updateNoticeDetails(Noticeofintent noticeofintent) throws HelixDaoException {
        try {
            hibernateTemplate.update(noticeofintent);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update notice of intent details failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void saveOrUpdateNoticeDetails(Noticeofintent noticeofintent)  throws HelixDaoException {
        try {
            hibernateTemplate.saveOrUpdate(noticeofintent);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save or update notice of intent details failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deleteNoticeDetails(Noticeofintent noticeofintent)  throws HelixDaoException {
        try {
            hibernateTemplate.delete(noticeofintent);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete notice of intent details failed!", e);
        }
    }

    public Noticeofintent getNoticeDetails(Integer idIntent) throws HelixDaoException {
        try {
            return (Noticeofintent) hibernateTemplate.get(Noticeofintent.class, idIntent);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get notice of intent details failed!", e);
        }
    }

    public List<Noticeofintent> getNoticeDetails() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Noticeofintent.class)
                    .addOrder(Order.asc("idIntent"))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get notice of intent details failed!", e);
        }
    }


    public  List<Noticeofintent> getNoticeDetailsCI(Integer idClient)  throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Noticeofintent.class)
                    .add(Restrictions.eq("client.idClient", idClient))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get notice of intent Details failed!", e);
        }
    }
}
