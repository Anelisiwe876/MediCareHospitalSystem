package com.mycompany.medicarehospitalsystem;
public class Patient { // start of class
    // System must store patient information
    String patientId;
    String firstName;
    String lastName;
    int age;
    String gender;
    String medicalCondition;
    PatientCategory category;
    
    // Constructor
    public Patient(String patientId, String firstName, String lastName, int age, 
        String gender, String medicalCondition, PatientCategory category) { // opening curly brace
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.medicalCondition = medicalCondition;
        this.category = category;
    } // closing of curly brace
    
    // getter method
    public String getPatientId() { // opening curly brace
        return patientId;
    } // closing of curly brace
 
    public String getFirstName() { // opening curly brace
        return firstName;
    } // closing of curly brace
 
    public String getLastName() { // opening curly brace
        return lastName;
    } // closing of curly brace
 
    public int getAge() { // opening curly brace
        return age;
    } // closing of curly brace
 
    public String getGender() { // opening curly brace
        return gender;
    } // closing of curly brace
 
    public String getMedicalCondition() { // opening curly brace
        return medicalCondition;
    } // closing of curly brace
 
    public PatientCategory getCategory() { // opening curly brace
        return category;
    } // closing of curly brace
    
    // setter method
    public void setFirstName(String firstName) { // opening curly brace
        this.firstName = firstName;
    } // closing of curly brace
 
    public void setLastName(String lastName) { // opening curly brace
        this.lastName = lastName;
    } // closing of curly brace
 
    public void setAge(int age) { // opening curly brace
        this.age = age;
    } // closing of curly brace
 
    public void setGender(String gender) { // opening curly brace
        this.gender = gender;
    } // closing of curly brace
 
    public void setMedicalCondition(String medicalCondition) { // opening curly brace
        this.medicalCondition = medicalCondition;
    } // closing of curly brace
 
    public void setCategory(PatientCategory category) { // opening curly brace
        this.category = category;
    } // closing of curly brace
      
    // Display all patient detials
    public void displayDetails() { // opening curly brace
        System.out.println("Patient ID: " + patientId);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Medical Condition: " + medicalCondition);
        System.out.println("Category: " + category);
    } // closing of curly brace
} // End of class
