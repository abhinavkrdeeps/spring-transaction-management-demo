package com.wissen.training.springtransactionmanagement.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Transaction in a database -> Sequence Of Operations performed on a database as a single logical unit.
 *
 * @Transactional annotation can be applied to a class or methods.
 * If applied at classes, it is by default applicable to all the methods and subclasses.
 * 1) It creates a proxy class to manage, create ,commit and rollback the transaction in a database.
 * In short term it will just wrap some transaction management code around the methods mark as @Transactional
 *
 * Properties For @Transactional Annotation
 * 1.1) Propagation. REQUIRED -> It is the default one. If there is an active transaction, 
 * appends the business logic with the current transaction else create a new one.
 *
 * 1.2) Propagation. SUPPORT -> If there is an active transaction it will be used else executed in non-transactional
 *
 * 1.3) Propagation. NEVER -> If there is an active transaction, spring throws an exception.
 *
 * 1.4) Propagation. REQUIRE_NEW  -> If there is an active transaction, spring suspends it and creates a new one.
 *
 *  ISOLATION LEVEL ->
 *     Isolation. SERIALIZABLE   ()
 *     Isolation.READ_COMMITTED  ()
 *     Isolation.REPEATABLE_READ ()
 *     Isolation.READ_UNCOMMITTED (DIRTY_READ can happen)
 *
 *
 *
 *
 *
 */
@Service
@Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.SERIALIZABLE)
public class TransactionDemo {

    @Transactional(propagation = Propagation.REQUIRED)
    public void requiredPropagation(){
        // pseudo Code For Required
//        if (isExistingTransaction()) {
//            if (isValidateExistingTransaction()) {
//                validateExisitingAndThrowExceptionIfNotValid();
//            }
//            return existing;
//        }
//        return createNewTransaction();
    }
}
