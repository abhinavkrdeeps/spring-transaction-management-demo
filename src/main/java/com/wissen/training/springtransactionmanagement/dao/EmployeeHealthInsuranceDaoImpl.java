package com.wissen.training.springtransactionmanagement.dao;

import com.wissen.training.springtransactionmanagement.models.EmployeeHealthInsurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class EmployeeHealthInsuranceDaoImpl extends JdbcDaoSupport implements EmployeeHealthInsuranceDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance) {
        String registrationSql = "INSERT INTO EmployeeHealthInsurance values (?,?,?)";
        assert getJdbcTemplate() != null;
        //
       // throw new Exception("Manually Throwing Exception");
        getJdbcTemplate().update(registrationSql,
                employeeHealthInsurance.getEmpId(), employeeHealthInsurance.getHealthInsuranceSchemeName(), employeeHealthInsurance.getCoverageAmount());
    }

    @Override
    public void deleteEmployeeHealthInsuranceById(String id) {
        String deleteQuery = "DELETE FROM EmployeeHealthInsurance where empId=?";
        assert getJdbcTemplate() != null;
        getJdbcTemplate().update(deleteQuery,id);
    }
}
