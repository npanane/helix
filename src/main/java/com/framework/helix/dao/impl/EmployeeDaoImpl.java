package com.framework.helix.dao.impl;

import com.framework.helix.dao.EmployeeDao;
import com.framework.helix.entity.Employee;
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

@Repository("employeeDao")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = true)
public class EmployeeDaoImpl implements EmployeeDao {

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
    public void saveEmployee(Employee employee)throws HelixDaoException{
        try {
            hibernateTemplate.save(employee);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save employee failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void updateEmployee(Employee employee) throws HelixDaoException {
        try {
            hibernateTemplate.update(employee);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Update employee failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void saveOrUpdateEmployee(Employee employee) throws HelixDaoException {
        try {
            hibernateTemplate.saveOrUpdate(employee);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Save or update employee failed!", e);
        }
    }

    @Transactional(readOnly = false)
    public void deleteEmployee(Employee employee) throws HelixDaoException {
        try {
            hibernateTemplate.delete(employee);
            hibernateTemplate.flush();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Delete employee failed!", e);
        }
    }

    public Employee getEmployee(Integer idEmployee) throws HelixDaoException {
        try {
            return (Employee) hibernateTemplate.get(Employee.class, idEmployee);
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get employee failed!", e);
        }
    }

    public List<Employee> getEmployees() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Employee.class)
                    .addOrder(Order.asc("idEmployee"))
                    .list();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get employee failed!", e);
        }
    }

    public String getLastEmployeeId() throws HelixDaoException {
        try {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Employee.class)
                    .setProjection(Projections.max("idEmployee"))
                    .uniqueResult()
                    .toString();
        }
        catch (DataAccessException e) {
            throw new HelixDaoException("Get EmployeeId failed!", e);
        }
    }
}
