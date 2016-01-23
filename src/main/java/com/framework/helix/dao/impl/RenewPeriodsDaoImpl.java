package com.framework.helix.dao.impl;

import com.framework.helix.dao.RenewPeriodsDao;
import com.framework.helix.entity.Renewperiod;
import com.framework.helix.exception.HelixDaoException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("renewPeriodsDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class RenewPeriodsDaoImpl implements RenewPeriodsDao {

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

    public Renewperiod getRenewPeriods(Integer idRenewPeriod) throws HelixDaoException {
        try {
            return (Renewperiod) hibernateTemplate.get(Renewperiod.class, idRenewPeriod);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get renew period failed!", e);
        }
    }

}
