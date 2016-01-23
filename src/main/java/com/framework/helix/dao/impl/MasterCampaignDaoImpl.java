package com.framework.helix.dao.impl;

import com.framework.helix.dao.MasterCampaignDao;
import com.framework.helix.entity.MasterCampaign;
import com.framework.helix.exception.HelixDaoException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("masterCampaignDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class MasterCampaignDaoImpl implements MasterCampaignDao {

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
    public void deleteCampaignDetails(int idCampaign) throws HelixDaoException {
        try {
            hibernateTemplate.delete(hibernateTemplate.get(MasterCampaign.class,idCampaign));
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete campaign details failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void saveCampaignDetails(MasterCampaign masterCampaign)  throws HelixDaoException {
        try {
            hibernateTemplate.save(masterCampaign);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save campaign details failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updateCampaignDetails(MasterCampaign masterCampaign)  throws HelixDaoException {
        try {
            hibernateTemplate.update(masterCampaign);
            hibernateTemplate.flush();
        } catch (DataIntegrityViolationException e) {
            throw new HelixDaoException("Update campaign details failed!", e);
        } catch (DataAccessException e) {
            throw new HelixDaoException("Update campaign details failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void saveOrUpdateCampaignDetails(MasterCampaign masterCampaign) throws HelixDaoException {
        try {
            hibernateTemplate.saveOrUpdate(masterCampaign);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save or update campaign details failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deleteCampaignDetails(MasterCampaign masterCampaign) throws HelixDaoException {
        try {
            hibernateTemplate.delete(masterCampaign);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete campaign details failed!", e);
        }
    }

    public MasterCampaign getCampaignDetails(Integer idCampaign) throws HelixDaoException {
        try {
            return (MasterCampaign) hibernateTemplate.get(MasterCampaign.class, idCampaign);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get campaign details failed!", e);
        }
    }

    public List<MasterCampaign> getCampaignDetails() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(MasterCampaign.class)
                    .addOrder(Order.asc("idCampaign"))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get campaign details failed!", e);
        }
    }

    public String getLastCampaignDetailsId() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(MasterCampaign.class)
                    .setProjection(Projections.max("idCampaign"))
                    .uniqueResult()
                    .toString();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get campaignId failed!", e);
        }
    }

    public List<MasterCampaign> getCampaignDetailsCI(Integer idClient) throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(MasterCampaign.class)
                    .add(Restrictions.eq("client.idClient", idClient))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get campaign Details failed!", e);
        }
    }

    public List<MasterCampaign> getCampaignDetailsForSpring() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(MasterCampaign.class)
                    .add(Restrictions.eq("Season","Spring"))
                    .addOrder(Order.asc("idCampaign"))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get campaign details failed!", e);
        }
    }

    public List<MasterCampaign> getCampaignDetailsForFall() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(MasterCampaign.class)
                    .add(Restrictions.eq("Season","Fall"))
                    .addOrder(Order.asc("idCampaign"))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get campaign details failed!", e);
        }
    }
}
