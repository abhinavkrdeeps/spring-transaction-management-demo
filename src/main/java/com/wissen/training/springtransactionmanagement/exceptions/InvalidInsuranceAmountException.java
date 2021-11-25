package com.wissen.training.springtransactionmanagement.exceptions;


public class InvalidInsuranceAmountException extends Exception{
    public InvalidInsuranceAmountException(String cause){
        super(cause);
    }
}
