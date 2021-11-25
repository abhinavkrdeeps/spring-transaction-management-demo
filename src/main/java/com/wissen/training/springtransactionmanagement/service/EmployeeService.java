package com.wissen.training.springtransactionmanagement.service;

import com.wissen.training.springtransactionmanagement.models.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.SERIALIZABLE)
public interface EmployeeService {

    void insertEmployee(Employee employee);

    void deleteEmployeeById(String id);
}
