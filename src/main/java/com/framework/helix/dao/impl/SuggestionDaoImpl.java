package com.framework.helix.dao.impl;

import com.framework.helix.dao.SuggestionDao;
import com.framework.helix.entity.Staffsuggestion;
import com.framework.helix.exception.HelixDaoException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("suggestionDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class SuggestionDaoImpl implements SuggestionDao{

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
    public void saveStaffSuggestion(Staffsuggestion staffsuggestion) throws HelixDaoException {
        try {
            hibernateTemplate.save(staffsuggestion);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save staff suggestion failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updateStaffSuggestion(Staffsuggestion staffsuggestion) throws HelixDaoException {
        try {
            hibernateTemplate.update(staffsuggestion);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update staff suggestion failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deleteStaffSuggestion(Staffsuggestion staffsuggestion) throws HelixDaoException {
        try {
            hibernateTemplate.delete(staffsuggestion);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete staff suggestion failed!", e);
        }
    }

    public Staffsuggestion getStaffSuggestion(Integer idSuggestion) throws HelixDaoException {
        try {
            return (Staffsuggestion) hibernateTemplate.get(Staffsuggestion.class, idSuggestion);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get Staff suggestion failed!", e);
        }
    }

    public List<Staffsuggestion> getStaffSuggestions() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Staffsuggestion.class)
                    .addOrder(Order.asc("idSuggestion"))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get Staff suggestion failed!", e);
        }
    }

    public String getLastStaffSuggestionId() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Staffsuggestion.class)
                    .setProjection(Projections.max("idSuggestion"))
                    .uniqueResult()
                    .toString();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get Suggestion Id failed!", e);
        }
    }
}
