package com.wissen.training.springtransactionmanagement.dao;

import com.wissen.training.springtransactionmanagement.models.EmployeeHealthInsurance;
import org.springframework.stereotype.Service;

public interface EmployeeHealthInsuranceDao {

    void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance);

    void deleteEmployeeHealthInsuranceById(String id);
}
