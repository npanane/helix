package com.framework.helix.dao.impl;

import com.framework.helix.dao.InstructionsDao;
import com.framework.helix.entity.Clientselectioninstruction;
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

@Repository("instructionsDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class InstructionsDaoImpl implements InstructionsDao{

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
    public void saveInstruction(Clientselectioninstruction clientselectioninstruction) throws HelixDaoException {
        try {
            hibernateTemplate.save(clientselectioninstruction);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save instruction failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void saveOrUpdateInstruction(Clientselectioninstruction clientselectioninstruction) throws HelixDaoException {
        try {
            hibernateTemplate.saveOrUpdate(clientselectioninstruction);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save instruction failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updateInstruction(Clientselectioninstruction clientselectioninstruction) throws HelixDaoException {
        try {
            hibernateTemplate.update(clientselectioninstruction);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update instruction failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deleteInstruction(int idInstruction) throws HelixDaoException{
        try {
            hibernateTemplate.delete(hibernateTemplate.get(Clientselectioninstruction.class,idInstruction));
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete instruction failed!", e);
        }
    }

    public Clientselectioninstruction getInstruction(int idInstruction) throws HelixDaoException {
        try {
            return (Clientselectioninstruction) hibernateTemplate.get(Clientselectioninstruction.class, idInstruction);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get instruction failed!", e);
        }
    }

    public List<Clientselectioninstruction> getInstructions(Integer idClient) throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Clientselectioninstruction.class)
                    .add(Restrictions.eq("client.idClient", idClient))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get instruction failed!", e);
        }
    }
}
