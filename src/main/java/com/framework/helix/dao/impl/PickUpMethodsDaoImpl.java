package com.framework.helix.dao.impl;

import com.framework.helix.dao.PickUpMethodsDao;
import com.framework.helix.entity.Pickupmethod;
import com.framework.helix.exception.HelixDaoException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("pickUpMethodsDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class PickUpMethodsDaoImpl implements PickUpMethodsDao{

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

    public Pickupmethod getPickUpMethod(Integer idPickupMethod) throws HelixDaoException {
        try {
            return (Pickupmethod) hibernateTemplate.get(Pickupmethod.class, idPickupMethod);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get pickUp method failed!", e);
        }
    }


}
