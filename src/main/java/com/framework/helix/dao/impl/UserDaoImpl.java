package com.framework.helix.dao.impl;

import com.framework.helix.dao.UserDao;
import com.framework.helix.entity.User;
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

/**
 * @since 5/25/2014
 */
@Repository("userDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class UserDaoImpl implements UserDao{

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

     public User getUser(String userName) throws HelixDaoException{
         try {
             return (User)sessionFactory.getCurrentSession()
                     .createCriteria(User.class)
                     .add(Restrictions.eq("UserName", userName))
                     .uniqueResult();
         }
         catch (DataAccessException e) {
             throw new HelixDaoException("Get user details failed!", e);
         }

     }

    @Transactional(readOnly = false)
    public void updateUser(User user) throws HelixDaoException {
        try {
            hibernateTemplate.update(user);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update user failed!", e);
        }
    }
}

