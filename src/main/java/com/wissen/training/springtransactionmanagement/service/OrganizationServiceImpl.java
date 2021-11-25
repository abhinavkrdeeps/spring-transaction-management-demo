package com.wissen.training.springtransactionmanagement.service;


import com.wissen.training.springtransactionmanagement.exceptions.InvalidInsuranceAmountException;
import com.wissen.training.springtransactionmanagement.models.Employee;
import com.wissen.training.springtransactionmanagement.models.EmployeeHealthInsurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  Transaction Propagation:
 *    An application consists of multiple components/services making calls to another services/components.
 *    Transaction Propagation indicates if any component/service does not participate in transaction and
 *    how will it behave if the calling component/service already has a transaction.
 *
 *
 *    1) REQUIRED : Propagation. REQUIRED
 *      Case a: If insertEmployee() is called directly.
 *           Creates its own transaction.
 *      case b: if insertEmployee() is called by joinOrganization().
 *           if joinOrganization has a transaction, insertEmployee() use the existing transaction else creates a new transaction.
 *
 *    2) SUPPORT: Propagation. SUPPORT
 *      case a: if insertEmployee() is called directly:
 *              Does not Create Own new transaction.
 *      case b: if insertEmployee() is called by joinOrganization().
 *              if joinOrganization has a transaction, insertEmployee() use the existing transaction else new transaction is not created.
 *
 *    3) NOT_SUPPORTED: Propagation. NOT_SUPPORTED
 *      case a: if insertEmployee() is called directly
 *              Does not create own new transaction.
 *      case b: if insertEmployee() is called by joinOrganization().
 *              if joinOrganization has a transaction, the transaction is suspended temporarily and insertEmployee is executed without transaction.
 *              if joinOrganization does not have a transaction, insertEmployee() is executed without transaction.
 *
 *     4) REQUIRES_NEW: Propagation. REQUIRES_NEW
 *        case a: if insertEmployee() is called directly
 *              Creates a new Transaction of its own.
 *        case b: if insertEmployee() is called by joinOrganization().
 *                if joinOrganization has an active transaction, the transaction is suspended and new transaction is created.
 *                if joinOrganization does not have an active transaction, a new transaction is been created.
 *
 *     5) NEVER: Propagation. NEVER
 *        case a: if insertEmployee() is called directly
 *                Does not create a transaction.
 *        case b: if insertEmployee() is called by joinOrganization().
 *                if joinOrganization has an active transaction, throws an exception
 *                if joinOrganization does not have an active transaction, no transaction is created.
 *
 *      6) MANDATORY: Propagation. MANDATORY
 *          case a: if insertEmployee() is called directly
 *                  An Exception is thrown.
 *          case b: if insertEmployee() is called by joinOrganization().
 *                  if joinOrganization has an active transaction, it uses the existing transaction.
 *                  if joinOrganization does not have an active transaction, throws an exception.
 *
 *
 *  Transaction Isolation:
 *       Transaction Isolation Defines the database state when two or more transactions act concurrently on the same database entity.
 *       It describes behaviour or state of the database when one transaction is working on a database entity , another transaction tries to
 *       access/edit the same database entity.
 *
 *       Isolation Levels:
 *         1) SERIALIZABLE:
 *                     Transactions should be executed serially that is the first transaction gets committed only then the second will get executed.
 *                     Can Cause deadlocks, the second transaction is waiting forever for the first transaction to release the lock.
 *
 *         2) REPEATABLE_READ:
 *                      If Two transactions say A and B are executing concurrently
 *                      Let A be the first transaction and B be the second.
 *                      Till A is committed , B cannot change the existing records but still can add new records.
 *                      Default Isolation level is REPEATABLE READ for MYSQL.
 *                      In Mysql, till transaction A is not committed the new records are not visible in A .
 *
 *          3) READ_COMMITTED:
 *                      If Two transactions say A and B are executing concurrently
 *  *                   Let A be the first transaction and B be the second.
 *                      A is not committed , B can add new records as well as change existing records.
 *                      After B is committed the records are reflected in A when A is still not committed.
 *
 *           4) READ_UNCOMMITTED:
 *                      If Two transactions say A and B are executing concurrently
 *                      Let A be the first transaction and B be the second.
 *                      A is not committed , B can add new records as well as change existing records.
 *                      Even Before B is committed the records are reflected in A when A is still not committed.
 *
 *         PROBLEMS:
 *             Let have two transaction: A and B
 *             1) DIRTY READ:
 *                  A makes a change --> B read the value before A commits --> A rollback -> A commit -> B has wrong value.
 *                  POSSIBLE IN ISOLATION LEVEL READ_UNCOMMITTED
 *             2) NON-REPEATABLE READS:
 *                  Same select statements producing different results:
 *                  A reads a records -> B modifies the records before A is committed -> A again reads -> Different value
 *                  POSSIBLE IN ISOLATION LEVEL READ_UNCOMMITTED and READ_COMMITTED
 *             3) PHANTOM READS:
 *                  A reads a records -> B deletes the records before A is committed -> A again reads -> Does not find
 *                  POSSIBLE IN ISOLATION LEVEL READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ
 *
 *
 *    Rollback:
 *      For an Application Transaction if any actions fails, all other actions gets rolled back.
 *      In case of unchecked Exception , rollback occurs implicitly, however in case of checked exception, rollback does
 *      not occur implicitly. We have to define for which exceptions we want a rollback .
 *      This is achieved by using rollbackFor attribute of @Transactional annotation.
 *
 *
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    HealthInsuranceService healthInsuranceService;

    @Override
    // Spring Boot implicitly Creates the proxy adding code for starting, committing the transaction.
    @Transactional (rollbackFor = InvalidInsuranceAmountException.class)// the whole method is executed as one logical unit (A Transaction). if at any point something fails, the whole is reverted.
    public void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) throws InvalidInsuranceAmountException {
        employeeService.insertEmployee(employee);
        if(employeeHealthInsurance.getCoverageAmount().equals("0")){
            throw new InvalidInsuranceAmountException("Amount is Invalid");
        }
        healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance);
    }

    @Override
    @Transactional
    public void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
        employeeService.deleteEmployeeById(employee.getEmpId());
        healthInsuranceService.deleteHealthInsuranceById(employeeHealthInsurance.getEmpId());

    }
}
