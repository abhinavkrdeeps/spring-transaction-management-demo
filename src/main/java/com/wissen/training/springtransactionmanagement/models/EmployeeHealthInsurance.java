package com.wissen.training.springtransactionmanagement.models;

public class EmployeeHealthInsurance {

    private String empId;
    private String healthInsuranceSchemeName;
    private String coverageAmount;

    public EmployeeHealthInsurance() {
    }

    public EmployeeHealthInsurance(String empId, String healthInsuranceSchemeName, String coverageAmount) {
        this.empId = empId;
        this.healthInsuranceSchemeName = healthInsuranceSchemeName;
        this.coverageAmount = coverageAmount;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getHealthInsuranceSchemeName() {
        return healthInsuranceSchemeName;
    }

    public void setHealthInsuranceSchemeName(String healthInsuranceSchemeName) {
        this.healthInsuranceSchemeName = healthInsuranceSchemeName;
    }

    public String getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(String coverageAmount) {
        this.coverageAmount = coverageAmount;
    }
}
