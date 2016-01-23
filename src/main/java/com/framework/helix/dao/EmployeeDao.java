package com.framework.helix.dao;

import com.framework.helix.entity.Employee;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/29/2014.
 */
public interface EmployeeDao {

    public void saveEmployee(Employee employee)throws HelixDaoException;

    public void updateEmployee(Employee employee) throws HelixDaoException;

    public void saveOrUpdateEmployee(Employee employee) throws HelixDaoException;

    public void deleteEmployee(Employee employee) throws HelixDaoException;

    public Employee getEmployee(Integer idEmployee) throws HelixDaoException;

    public List<Employee> getEmployees() throws HelixDaoException;

    public String getLastEmployeeId() throws HelixDaoException;

}
