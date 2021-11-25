package com.wissen.training.springtransactionmanagement;

import com.wissen.training.springtransactionmanagement.exceptions.InvalidInsuranceAmountException;
import com.wissen.training.springtransactionmanagement.models.Employee;
import com.wissen.training.springtransactionmanagement.models.EmployeeHealthInsurance;
import com.wissen.training.springtransactionmanagement.service.OrganizationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringTransactionManagementApplication {

	public static void main(String[] args) throws InvalidInsuranceAmountException {
		ApplicationContext applicationContext= SpringApplication.run(SpringTransactionManagementApplication.class, args);
		OrganizationService organizationService = applicationContext.getBean(OrganizationService.class);

		Employee employee = new Employee("11","Exception Check Again Rollback");
		EmployeeHealthInsurance employeeHealthInsurance = new EmployeeHealthInsurance("15","Mobile","0");
		organizationService.joinOrganization(employee,employeeHealthInsurance);
	}

}
