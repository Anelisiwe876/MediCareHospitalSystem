package com.mycompany.medicarehospitalsystem;
public class BedManagement { // start of class
    Bed[][] beds;
 
    public BedManagement() { // opening curly brace
        beds = new Bed[4][5];
        int bedNumber = 1;
        for (int row = 0; row < 4; row++) { // opening curly brace
            for (int col = 0; col < 5; col++) { // opening curly brace
                String label;
                if (bedNumber < 10) { // opening curly brace
                    label = "B0" + bedNumber;
                } else {
                    label = "B" + bedNumber;
                } // closing of curly brace
                
                beds[row][col] = new Bed(label);
                bedNumber++;
            } // closing of curly brace
        } // closing of curly brace
    } // closing of curly brace
 
    public Bed findBed(String bedLabel) { // opening curly brace
        for (int row = 0; row < beds.length; row++) { // opening curly brace
            for (int col = 0; col < beds[row].length; col++) { // opening curly brace
                if (beds[row][col].getBedLabel().equals(bedLabel)) { // opening curly brace
                    return beds[row][col];
                } // closing of curly brace
            } // closing of curly brace
        } // closing of curly brace
        return null;
    }
 
    public boolean allocateBed(String bedLabel, String patientId) { // opening curly brace
        Bed bed = findBed(bedLabel);
        if (bed == null || bed.isOccupied()) { // opening curly brace
            return false;
        } // closing of curly brace
        bed.allocate(patientId);
        return true;
    } // closing of curly brace
 
    public String releaseBed(String bedLabel) { // opening curly brace
        Bed bed = findBed(bedLabel);
        if (bed == null || !bed.isOccupied()) { // opening curly brace
            return null;
        } // closing of curly brace
        String patientId = bed.getPatientId();
        bed.release();
        return patientId;
    } // closing of curly brace
 
    public boolean hasAvailableBed() { // opening curly brace
        for (int row = 0; row < beds.length; row++) { // opening curly brace
            for (int col = 0; col < beds[row].length; col++) { // opening curly brace
                if (!beds[row][col].isOccupied()) { // opening curly brace
                    return true;
                } // closing of curly brace
            } // closing of curly brace
        } // closing of curly brace
        return false;
    } // closing of curly brace
 
    public void displayWardLayout() { // opening curly brace
        for (int row = 0; row < beds.length; row++) { // opening curly brace
            String line = "";
            for (int col = 0; col < beds[row].length; col++) { // opening curly brace
                Bed bed = beds[row][col];
                if (bed.isOccupied()) { // opening curly brace
                    line = line + bed.getBedLabel() + "(Occupied) ";
                } else {
                    line = line + bed.getBedLabel() + "(Available) ";
                } // closing of curly brace
            } // closing of curly brace
            System.out.println(line.trim());
        } // closing of curly brace
    } // closing of curly brace
 
    public void displayAvailableBeds() { // opening curly brace
        System.out.println("Available Beds:");
        boolean found = false;
        for (int row = 0; row < beds.length; row++) { // opening curly brace
            for (int col = 0; col < beds[row].length; col++) { // opening curly brace
                if (!beds[row][col].isOccupied()) {
                    System.out.println(beds[row][col].getBedLabel());
                    found = true;
                } // closing of curly brace
            } // closing of curly brace
        } // closing of curly brace
        if (!found) { // opening curly brace
            System.out.println("No beds available.");
        } // closing of curly brace
    } // closing of curly brace
 
    public void displayOccupiedBeds() { // opening curly brace
        System.out.println("Occupied Beds:");
        boolean found = false;
        for (int row = 0; row < beds.length; row++) { // opening curly brace
            for (int col = 0; col < beds[row].length; col++) { // opening curly brace
                if (beds[row][col].isOccupied()) {
                    System.out.println(beds[row][col].getBedLabel() + " - Patient ID: " + beds[row][col].getPatientId());
                    found = true;
                } // closing of curly brace
            } // closing of curly brace
        } // closing of curly brace
        if (!found) { // opening curly brace
            System.out.println("No beds occupied.");
        }
    }
 
    public int getTotalOccupiedBeds() { // opening curly brace
        int count = 0;
        for (int row = 0; row < beds.length; row++) { // opening curly brace
            for (int col = 0; col < beds[row].length; col++) { // opening curly brace
                if (beds[row][col].isOccupied()) { // opening curly brace
                    count++;
                } // closing of curly brace
            } // closing of curly brace
        } // closing of curly brace
        return count;
    } // closing of curly brace
 
    public int getTotalBeds() { // opening curly brace
        return beds.length * beds[0].length;
    } // closing of curly brace
 
    public double getOccupancyPercentage() { // opening curly brace
        return (getTotalOccupiedBeds() / (double) getTotalBeds()) * 100;
    } // closing of curly brace
} // closing of curly brace
 
class Bed { // opening curly brace
    String bedLabel;
    boolean occupied;
    String patientId;
 
    public Bed(String bedLabel) { // opening curly brace
        this.bedLabel = bedLabel;
        this.occupied = false;
        this.patientId = null;
    } // closing of curly brace
 
    public String getBedLabel() { // opening curly brace
        return bedLabel;
    } // closing of curly brace
 
    public boolean isOccupied() { // opening curly brace
        return occupied;
    } // closing of curly brace
 
    public String getPatientId() { // opening curly brace
        return patientId;
    } // closing of curly brace
 
    public void allocate(String patientId) { // opening curly brace
        this.occupied = true;
        this.patientId = patientId;
    } // closing of curly brace
 
    public void release() { // opening curly brace
        this.occupied = false;
        this.patientId = null;
    } // closing of curly brace
} // end of class