package com.framework.helix.service.impl;

import com.framework.helix.dao.EmployeeDao;
import com.framework.helix.entity.Employee;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/29/2014.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void saveEmployee(Employee employee) throws HelixServiceException {
        try {
            employeeDao.saveEmployee(employee);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save employee.", e);
        }
    }

    public void updateEmployee(Employee employee) throws HelixServiceException {
        try {
            employeeDao.updateEmployee(employee);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to update employee.", e);
        }
    }

    public void saveOrUpdateEmployee(Employee employee) throws HelixServiceException {
        try {
            employeeDao.saveOrUpdateEmployee(employee);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save or update employee.", e);
        }
    }

    public void deleteEmployee(Employee employee) throws HelixServiceException {
        try {
            employeeDao.deleteEmployee(employee);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to delete employee.", e);
        }
    }

    public Employee getEmployee(Integer idEmployee) throws HelixServiceException {
        try {
            return employeeDao.getEmployee(idEmployee);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get employee.", e);
        }
    }

    public List<Employee> getEmployees() throws HelixServiceException {
        try {
            return employeeDao.getEmployees();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get employees.", e);
        }
    }

    public String getLastEmployeeId() throws HelixServiceException {
        try {
            return employeeDao.getLastEmployeeId();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get employeeId.", e);
        }
    }

}
