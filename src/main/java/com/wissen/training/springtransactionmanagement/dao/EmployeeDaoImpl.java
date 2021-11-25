package com.wissen.training.springtransactionmanagement.dao;

import com.wissen.training.springtransactionmanagement.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao{

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    // Annotation that needs to be done on a method which needs to be executed after dependency injection for any kind of initialization.
    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void insertEmployee(Employee employee) {
        String empSql = "INSERT INTO employee " + "(empId, empName) VALUES (?, ?)";
        jdbcTemplate.update(empSql, employee.getEmpId(),employee.getEmpName());

    }

    @Override
    public void deleteEmployeeById(String empID) {
        String deleteSql = "DELETE FROM EMPLOYEE where empID=?";
        jdbcTemplate.update(deleteSql,empID);
    }
}
