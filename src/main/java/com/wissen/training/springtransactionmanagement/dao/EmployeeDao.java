package com.wissen.training.springtransactionmanagement.dao;

import com.wissen.training.springtransactionmanagement.models.Employee;


public interface EmployeeDao {

    void insertEmployee(Employee employee);

    void deleteEmployeeById(String empID);

}
