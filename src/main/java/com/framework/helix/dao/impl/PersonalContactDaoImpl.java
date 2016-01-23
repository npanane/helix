package com.framework.helix.dao.impl;

import com.framework.helix.dao.PersonalContactDao;
import com.framework.helix.entity.Personalcontact;
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

@Repository("personalContactDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class PersonalContactDaoImpl implements PersonalContactDao {

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
    public void savePersonalContact(Personalcontact personalcontact) throws HelixDaoException {
        try {
            hibernateTemplate.save(personalcontact);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save personal contact failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updatePersonalContact(Personalcontact personalcontact) throws HelixDaoException {
        try {
            hibernateTemplate.update(personalcontact);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update personal contact failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void saveOrUpdatePersonalContact(Personalcontact personalcontact) throws HelixDaoException {
        try {
            hibernateTemplate.saveOrUpdate(personalcontact);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save or update personal contact failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deletePersonalContact(Personalcontact personalcontact) throws HelixDaoException {
        try {
            hibernateTemplate.delete(personalcontact);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete personal contact failed!", e);
        }
    }

    public Personalcontact getPersonalContact(Integer idPersonalcontacts) throws HelixDaoException {
        try {
            return (Personalcontact) hibernateTemplate.get(Personalcontact.class, idPersonalcontacts);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get personal contact failed!", e);
        }
    }

    public List<Personalcontact> getPersonalContact() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Personalcontact.class)
                    .addOrder(Order.asc("ContactPerson"))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get personal contacts failed!", e);
        }
    }

    public String  getLastPersonalContactId() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Personalcontact.class)
                    .setProjection(Projections.max("idPersonalcontact"))
                    .uniqueResult()
                    .toString();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get Personal Contact Id failed!", e);
        }
    }

}
