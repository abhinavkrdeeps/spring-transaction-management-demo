package com.wissen.training.springtransactionmanagement.service;

import com.wissen.training.springtransactionmanagement.dao.EmployeeHealthInsuranceDao;
import com.wissen.training.springtransactionmanagement.models.EmployeeHealthInsurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HealthInsuranceServiceImpl implements HealthInsuranceService{

    @Autowired
    private EmployeeHealthInsuranceDao employeeHealthInsuranceDao;

    @Override
    public void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance) {
        employeeHealthInsuranceDao.registerEmployeeHealthInsurance(employeeHealthInsurance);
    }

    @Override
    public void deleteHealthInsuranceById(String id) {
        employeeHealthInsuranceDao.deleteEmployeeHealthInsuranceById(id);

    }
}
