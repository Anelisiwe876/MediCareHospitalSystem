package com.mycompany.medicarehospitalsystem;
import java.util.Scanner;

public class MediCareHospitalSystem { // start of class

    public static void main(String[] args) { // opening curly brace
        Scanner scanner = new Scanner(System.in);
        PatientManagement patientManagement = new PatientManagement();
        BedManagement bedManagement = new BedManagement();
        boolean running = true;
 
        while (running) { // opening curly brace
            printMenu();
            String choice = scanner.nextLine();
 
            switch (choice) { // opening curly brace
                case "1":
                    registerPatient(scanner, patientManagement);
                    break;
                case "2":
                    searchPatient(scanner, patientManagement);
                    break;
                case "3":
                    updatePatient(scanner, patientManagement);
                    break;
                case "4":
                    deletePatient(scanner, patientManagement, bedManagement);
                    break;
                case "5":
                    patientManagement.displayAllPatients();
                    break;
                case "6":
                    allocateBed(scanner, patientManagement, bedManagement);
                    break;
                case "7":
                    releaseBed(scanner, patientManagement, bedManagement);
                    break;
                case "8":
                    bedManagement.displayWardLayout();
                    break;
                case "9":
                    bedManagement.displayAvailableBeds();
                    break;
                case "10":
                    bedManagement.displayOccupiedBeds();
                    break;
                case "11":
                    displayReports(patientManagement, bedManagement);
                    break;
                case "12":
                    sortPatients(scanner, patientManagement);
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting system. Goodbye.");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            } // closing of curly brace
        } // closing of curly brace
        
        scanner.close();
    } // closing of curly brace
 
    public static void printMenu() { // opening curly brace
        System.out.println();
        System.out.println("===== MediCare Hospital Patient Admission System =====");
        System.out.println("1. Register a new patient");
        System.out.println("2. Search for a patient");
        System.out.println("3. Update patient details");
        System.out.println("4. Delete a patient");
        System.out.println("5. Display all patients");
        System.out.println("6. Allocate a bed");
        System.out.println("7. Release a bed");
        System.out.println("8. Display ward layout");
        System.out.println("9. Display available beds");
        System.out.println("10. Display occupied beds");
        System.out.println("11. Display reports");
        System.out.println("12. Sort patients");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    } // closing of curly brace
 
    public static void registerPatient(Scanner scanner, PatientManagement patientManagement) {
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Medical Condition: ");
        String medicalCondition = scanner.nextLine();
        System.out.print("Enter Category (INPATIENT, OUTPATIENT, EMERGENCY): ");
        String categoryInput = scanner.nextLine().trim().toUpperCase();
 
        Patient patient;
        if (categoryInput.equals("INPATIENT")) { // opening curly brace
            System.out.print("Enter Ward Number: ");
            String wardNumber = scanner.nextLine();
            patient = new Inpatient(patientId, firstName, lastName, age, gender, medicalCondition, wardNumber);
        } else if (categoryInput.equals("OUTPATIENT")) { // opening curly brace
            patient = new Patient(patientId, firstName, lastName, age, gender, medicalCondition, PatientCategory.OUTPATIENT);
        } else if (categoryInput.equals("EMERGENCY")) { // opening curly brace
            patient = new Patient(patientId, firstName, lastName, age, gender, medicalCondition, PatientCategory.EMERGENCY);
        } else { // opening curly brace
            System.out.println("Invalid category. Registration cancelled.");
            return;
        } // closing of curly brace
 
        boolean success = patientManagement.registerPatient(patient);
        if (success) { // opening curly brace
            System.out.println("Patient registered successfully.");
        } else {
            System.out.println("A patient with that ID already exists.");
        } // closing of curly brace
    } // closing of curly brace
 
    public static void searchPatient(Scanner scanner, PatientManagement patientManagement) { // opening curly brace
        System.out.print("Enter Patient ID to search: ");
        String patientId = scanner.nextLine();
        Patient patient = patientManagement.findPatient(patientId);
        if (patient == null) { // opening curly brace
            System.out.println("Patient not found.");
        } else { // opening curly brace
            patient.displayDetails();
        } // closing of curly brace
    } // closing of curly brace
 
    public static void updatePatient(Scanner scanner, PatientManagement patientManagement) { // opening curly brace
        System.out.print("Enter Patient ID to update: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter new First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter new Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter new Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter new Medical Condition: ");
        String medicalCondition = scanner.nextLine();
 
        boolean success = patientManagement.updatePatient(patientId, firstName, lastName, age, gender, medicalCondition);
        if (success) { // opening curly brace
            System.out.println("Patient updated successfully.");
        } else { // opening curly brace
            System.out.println("Patient not found.");
        } // closing of curly brace
    } // closing of curly brace
 
    public static void deletePatient(Scanner scanner, PatientManagement patientManagement, BedManagement bedManagement) { // opening curly brace
        System.out.print("Enter Patient ID to delete: ");
        String patientId = scanner.nextLine();
        Patient patient = patientManagement.findPatient(patientId);
        if (patient == null) { // opening curly brace
            System.out.println("Patient not found.");
            return;
        } // closing of curly brace
        
        if (patient instanceof Inpatient) { // opening curly brace
            Inpatient inpatient = (Inpatient) patient;
            if (inpatient.getBedNumber() != null) { // opening curly brace
                bedManagement.releaseBed(inpatient.getBedNumber());
            } // closing of curly brace
        } // closing of curly brace
        
        patientManagement.deletePatient(patientId);
        System.out.println("Patient deleted successfully.");
    } // closing of curly brace
 
    public static void allocateBed(Scanner scanner, PatientManagement patientManagement, BedManagement bedManagement) { // opening curly brace
        if (!bedManagement.hasAvailableBed()) { // opening curly brace
            System.out.println("No beds are available.");
            return;
        } // closing of curly brace
        
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        Patient patient = patientManagement.findPatient(patientId);
        if (patient == null || !(patient instanceof Inpatient)) { // opening curly brace
            System.out.println("Only registered inpatients can be allocated a bed.");
            return;
        } // closing of curly brace
        
        System.out.print("Enter Bed Label (e.g. B01): ");
        String bedLabel = scanner.nextLine().trim().toUpperCase();
        boolean success = bedManagement.allocateBed(bedLabel, patientId);
        if (success) { // opening curly brace
            ((Inpatient) patient).setBedNumber(bedLabel);
            System.out.println("Bed allocated successfully.");
        } else { // opening curly brace
            System.out.println("Could not allocate bed. Check the bed label and that it is available.");
        } // closing of curly brace
    } // closing of curly brace
 
    public static void releaseBed(Scanner scanner, PatientManagement patientManagement, BedManagement bedManagement) { // opening curly brace
        System.out.print("Enter Bed Label to release (e.g. B01): ");
        String bedLabel = scanner.nextLine().trim().toUpperCase();
        String patientId = bedManagement.releaseBed(bedLabel);
        if (patientId == null) { // opening curly brace
            System.out.println("Could not release bed. Check the bed label and that it is occupied.");
            return;
        } // closing of curly brace
        
        Patient patient = patientManagement.findPatient(patientId);
        if (patient != null && patient instanceof Inpatient) { // opening curly brace
            ((Inpatient) patient).setBedNumber(null);
        }// closing of curly brace
        
        System.out.println("Bed released successfully.");
    } // closing of curly brace
 
    public static void displayReports(PatientManagement patientManagement, BedManagement bedManagement) { // opening curly brace
        System.out.println("Total Registered Patients: " + patientManagement.getTotalPatients());
        System.out.println("Total Occupied Beds: " + bedManagement.getTotalOccupiedBeds());
        System.out.println("Ward Occupancy: " + bedManagement.getOccupancyPercentage() + "%");
    } // closing of curly brace
 
    public static void sortPatients(Scanner scanner, PatientManagement patientManagement) { // opening curly brace
        System.out.print("Sort by (1) Surname or (2) Patient ID: ");
        String choice = scanner.nextLine();
        if (choice.equals("1")) { // opening curly brace
            patientManagement.sortPatientsBySurname();
            System.out.println("Patients sorted by surname.");
        } else if (choice.equals("2")) { // opening curly brace
            patientManagement.sortPatientsById();
            System.out.println("Patients sorted by Patient ID.");
        } else { // opening curly brace
            System.out.println("Invalid choice.");
            return;
        } // closing of curly brace
        
        patientManagement.displayAllPatients();
    } // closing of curly brace
} // End of class
 