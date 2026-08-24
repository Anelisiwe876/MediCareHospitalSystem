package com.mycompany.medicarehospitalsystem;
import java.util.ArrayList;

public class PatientManagement {
  ArrayList<Patient> patients;
 
    public PatientManagement() {
        patients = new ArrayList<Patient>();
    }
 
    public boolean registerPatient(Patient patient) {
        if (findPatient(patient.getPatientId()) != null) {
            return false;
        }
        patients.add(patient);
        return true;
    }
 
    public Patient findPatient(String patientId) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getPatientId().equals(patientId)) {
                return patients.get(i);
            }
        }
        return null;
    }
 
    public boolean updatePatient(String patientId, String firstName, String lastName, int age, String gender, String medicalCondition) {
        Patient patient = findPatient(patientId);
        if (patient == null) {
            return false;
        }
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setAge(age);
        patient.setGender(gender);
        patient.setMedicalCondition(medicalCondition);
        return true;
    }
 
    public boolean deletePatient(String patientId) {
        Patient patient = findPatient(patientId);
        if (patient == null) {
            return false;
        }
        patients.remove(patient);
        return true;
    }
 
    public void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
            return;
        }
        for (int i = 0; i < patients.size(); i++) {
            patients.get(i).displayDetails();
            System.out.println("------------------------------");
        }
    }
 
    public int getTotalPatients() {
        return patients.size();
    }
 
    public void sortPatientsBySurname() {
        for (int i = 0; i < patients.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < patients.size(); j++) {
                if (patients.get(j).getLastName().compareToIgnoreCase(patients.get(minIndex).getLastName()) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Patient temp = patients.get(i);
                patients.set(i, patients.get(minIndex));
                patients.set(minIndex, temp);
            }
        }
    }
 
    public void sortPatientsById() {
        for (int i = 0; i < patients.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < patients.size(); j++) {
                if (patients.get(j).getPatientId().compareToIgnoreCase(patients.get(minIndex).getPatientId()) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Patient temp = patients.get(i);
                patients.set(i, patients.get(minIndex));
                patients.set(minIndex, temp);
            }
        }
    }
}
