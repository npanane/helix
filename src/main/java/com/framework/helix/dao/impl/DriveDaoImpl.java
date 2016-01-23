package com.framework.helix.dao.impl;

import com.framework.helix.dao.DriveDao;
import com.framework.helix.entity.Drive;
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

@Repository("driveDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class DriveDaoImpl implements DriveDao{

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
    public void saveDrive(Drive drive) throws HelixDaoException {
        try {
            hibernateTemplate.save(drive);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save drive failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updateDrive(Drive drive) throws HelixDaoException {
        try {
            hibernateTemplate.update(drive);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update drive failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deleteDrive(Drive drive) throws HelixDaoException {
        try {
            hibernateTemplate.delete(drive);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete drive failed!", e);
        }
    }

    public Drive getDrive(Integer idDrive) throws HelixDaoException {
        try {
            return (Drive) hibernateTemplate.get(Drive.class, idDrive);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get drive failed!", e);
        }
    }

    public List<Drive> getDrives()  throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Drive.class)
                    .addOrder(Order.asc("idDrive"))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get drives failed!", e);
        }
    }

    public String getLastDriveId() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Drive.class)
                    .setProjection(Projections.max("idDrive"))
                    .uniqueResult()
                    .toString();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get DriveId failed!", e);
        }
    }
}
