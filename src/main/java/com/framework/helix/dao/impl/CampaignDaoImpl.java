package com.framework.helix.dao.impl;

import com.framework.helix.dao.CampaignDao;
import com.framework.helix.entity.Campaign;
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
 * Created by nuwan on 8/29/2014.
 */
@Repository("campaignDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class CampaignDaoImpl implements CampaignDao {

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
    public void saveCampaign(Campaign campaign) throws HelixDaoException {
        try {
            hibernateTemplate.save(campaign);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save campaign failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updateCampaign(Campaign campaign) throws HelixDaoException {
        try {
            hibernateTemplate.update(campaign);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update campaign failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deleteCampaign(Campaign campaign) throws HelixDaoException {
        try {
            hibernateTemplate.delete(campaign);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete campaign failed!", e);
        }
    }

    public Campaign getCampaign(Integer idCampaign) throws HelixDaoException {
        try {
            return (Campaign) hibernateTemplate.get(Campaign.class, idCampaign);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get campaign failed!", e);
        }
    }

    public List<Campaign> getCampaignsByClientId(Integer clientId) throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Campaign.class)
                    .add(Restrictions.eq("client.idClient", clientId))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get Campaigns failed!", e);
        }
    }

    public List<Campaign> getCampaigns() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Campaign.class)
                    .addOrder(Order.asc("idCampaign"))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get Campaigns failed!", e);
        }
    }

    public List<Campaign> getDriveCampaignList(Integer idDrive) throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Campaign.class)
                    .add(Restrictions.eq("drive.idDrive", idDrive))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get campaigns failed!", e);
        }
    }

    public String  getLastCampaignId() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Campaign.class)
                    .setProjection(Projections.max("idCampaign"))
                    .uniqueResult()
                    .toString();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get CampaignId failed!", e);
        }
    }
}
