package com.framework.helix.dao.impl;

import com.framework.helix.dao.RenewTypesDao;
import com.framework.helix.entity.Renewtype;
import com.framework.helix.exception.HelixDaoException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("renewTypesDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class RenewTypesDaoImpl implements RenewTypesDao{

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

    public Renewtype getRenewTypes(Integer idRenewType) throws HelixDaoException {
        try {
            return (Renewtype) hibernateTemplate.get(Renewtype.class, idRenewType);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get renew types failed!", e);
        }
    }

    public List<Renewtype> getRenewTypes() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession().createCriteria(Renewtype.class).list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get renew types failed!", e);
        }
    }
}
