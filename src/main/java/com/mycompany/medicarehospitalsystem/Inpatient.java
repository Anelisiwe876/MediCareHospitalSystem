package com.mycompany.medicarehospitalsystem;
public class Inpatient extends Patient {
    String wardNumber;
    String bedNumber;
 
    public Inpatient(String patientId, String firstName, String lastName, int age, String gender, String medicalCondition, String wardNumber) {
        super(patientId, firstName, lastName, age, gender, medicalCondition, PatientCategory.INPATIENT);
        this.wardNumber = wardNumber;
        this.bedNumber = null;
    } // closing of curly brace
 
    public String getWardNumber() {
        return wardNumber;
    } // closing of curly brace
 
    public String getBedNumber() {
        return bedNumber;
    } // closing of curly brace
 
    public void setWardNumber(String wardNumber) {
        this.wardNumber = wardNumber;
    } // closing of curly brace
 
    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    } // closing of curly brace
 
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Ward Number: " + wardNumber);
        if (bedNumber == null) {
            System.out.println("Bed Number: Not Allocated");
        } else {
            System.out.println("Bed Number: " + bedNumber);
        } // closing of curly brace
    } // closing of curly brace
} // closing of curly brace

