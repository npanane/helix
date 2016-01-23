package com.framework.helix.service;

import com.framework.helix.entity.Employee;
import com.framework.helix.exception.HelixServiceException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/29/2014.
 */
public interface EmployeeService {

    public void saveEmployee(Employee employee)throws HelixServiceException;

    public void updateEmployee(Employee employee) throws HelixServiceException;

    public void saveOrUpdateEmployee(Employee employee) throws HelixServiceException;

    public void deleteEmployee(Employee employee) throws HelixServiceException;

    public Employee getEmployee(Integer idEmployee) throws HelixServiceException;

    public List<Employee> getEmployees() throws HelixServiceException;

    public String getLastEmployeeId() throws HelixServiceException;

}
