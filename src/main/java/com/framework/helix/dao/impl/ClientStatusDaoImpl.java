package com.framework.helix.dao.impl;

import com.framework.helix.dao.ClientStatusDao;
import com.framework.helix.entity.Clientstatus;
import com.framework.helix.exception.HelixDaoException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("clientStatusDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class ClientStatusDaoImpl implements ClientStatusDao {

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

    public Clientstatus getClientStatus(Integer idClientStatus)  throws HelixDaoException {
        try {
            return (Clientstatus) hibernateTemplate.get(Clientstatus.class, idClientStatus);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get Client status failed!", e);
        }
    }


}
