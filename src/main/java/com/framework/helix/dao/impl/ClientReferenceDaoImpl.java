package com.framework.helix.dao.impl;

import com.framework.helix.dao.ClientReferenceDao;
import com.framework.helix.entity.Clientreference;
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

@Repository("clientReferenceDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class ClientReferenceDaoImpl implements ClientReferenceDao {

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
    public void saveClientReference(Clientreference clientreference) throws HelixDaoException {
        try {
            hibernateTemplate.save(clientreference);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save client reference failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updateClientReference(Clientreference clientreference) throws HelixDaoException {
        try {
            hibernateTemplate.update(clientreference);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update client reference failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deleteClientReference(Clientreference clientreference) throws HelixDaoException {
        try {
            hibernateTemplate.delete(clientreference);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete client reference failed!", e);
        }
    }

    public Clientreference getClientReference(Integer idClientreference) throws HelixDaoException {
        try {
            return (Clientreference) hibernateTemplate.get(Clientreference.class, idClientreference);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get client reference failed!", e);
        }
    }

    public List<Clientreference> getClientReferences() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Clientreference.class)
                    .addOrder(Order.asc("idClientreference"))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get client references failed!", e);
        }
    }

    public String getLastClientReferenceId() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Clientreference.class)
                    .setProjection(Projections.max("idClientreference"))
                    .uniqueResult()
                    .toString();

        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get client referenceId failed!", e);
        }
    }
}
