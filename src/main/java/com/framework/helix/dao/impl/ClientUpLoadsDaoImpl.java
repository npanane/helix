package com.framework.helix.dao.impl;

import com.framework.helix.dao.ClientUpLoadsDao;
import com.framework.helix.entity.Clientupload;
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

import java.util.List;

@Repository("clientUpLoadsDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class ClientUpLoadsDaoImpl implements ClientUpLoadsDao {

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
    public void saveClientUpload(Clientupload clientupload) throws HelixDaoException {
        try {
            hibernateTemplate.save(clientupload);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save client upload failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deleteClientUpload(int idClientupload) throws HelixDaoException{
        try {
            hibernateTemplate.delete(hibernateTemplate.get(Clientupload.class,idClientupload));
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete upload file failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updateClientUpload(Clientupload clientupload) throws HelixDaoException {
        try {
            hibernateTemplate.update(clientupload);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update uoload file failed!", e);
        }
    }

    public List<Clientupload> getClientUploads(Integer idClient) throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Clientupload.class)
                    .add(Restrictions.eq("client.idClient", idClient))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get client upload failed!", e);
        }
    }
}
