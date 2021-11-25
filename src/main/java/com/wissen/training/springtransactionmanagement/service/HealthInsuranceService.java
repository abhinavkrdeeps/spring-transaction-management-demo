package com.wissen.training.springtransactionmanagement.service;

import com.wissen.training.springtransactionmanagement.models.EmployeeHealthInsurance;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HealthInsuranceService {

    void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance);

    void deleteHealthInsuranceById(String id);
}
