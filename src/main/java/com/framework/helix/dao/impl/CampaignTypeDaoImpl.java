package com.framework.helix.dao.impl;

import com.framework.helix.dao.CampaignTypeDao;
import com.framework.helix.entity.Campaigntype;
import com.framework.helix.exception.HelixDaoException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 9/2/2014.
 */
@Repository("campaignTypeDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class CampaignTypeDaoImpl implements CampaignTypeDao {

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

    public Campaigntype getCampaignType(Integer idCampaignType) throws HelixDaoException {
        try {
            return (Campaigntype) hibernateTemplate.get(Campaigntype.class, idCampaignType);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get campaign failed!", e);
        }
    }

    public List<Campaigntype> getCampaignTypes() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Campaigntype.class)
                    .addOrder(Order.asc("idCampaignType"))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get Campaign Type failed!", e);
        }
    }


}
