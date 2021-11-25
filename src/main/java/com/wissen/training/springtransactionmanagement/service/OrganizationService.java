package com.wissen.training.springtransactionmanagement.service;

import com.wissen.training.springtransactionmanagement.exceptions.InvalidInsuranceAmountException;
import com.wissen.training.springtransactionmanagement.models.Employee;
import com.wissen.training.springtransactionmanagement.models.EmployeeHealthInsurance;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrganizationService {

    void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) throws InvalidInsuranceAmountException;

    void leaveOrganization(Employee employee,EmployeeHealthInsurance employeeHealthInsurance);
}
