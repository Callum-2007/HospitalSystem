/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicarehospitalsystem;
import java.util.ArrayList;
/**
 *
 * @author emeris
 */
public class SystemLogic {
    private BedManagement bed;
    private int patientCounter;
    private String ErrorMsg="";
    
    public SystemLogic() {
        bed = new BedManagement();
        patientCounter=1000;
    }
    
    private String generatePatientID() {
        patientCounter++;
        return "P" + patientCounter;
    }
    
    public boolean registerPatient(String firstName, String lastName, int age,
            String gender, String medicalCondition, PatientCategory category) {
        
        if (firstName == null || firstName.trim().length() ==0) {
            ErrorMsg = "First name cannot be blank.";
            return false;
        }
        if (lastName == null || lastName.trim().length() ==  0) {
            ErrorMsg = "Last name cannot be blank.";
            return false;
        }
        if (age<0 || age>150) {
            ErrorMsg = "Please enter a valid age.";
            return false;
        }
        
        String patientID = generatePatientID();
        Patient patient;
        
        if (category == PatientCategory.INPATIENT) {
            patient = new Inpatient(patientID, firstName, lastName, age, gender,
            medicalCondition,0,null);
        } else {
            patient = new Patient(patientID, firstName, lastName, age, gender,
            medicalCondition, category);
        }
      bed.addPatient(patient);
      ErrorMsg="";
      return true;
    }
    
    public Patient searchPatient(String PatientID){
        Patient patient = bed.findPatient(PatientID);
        if (patient==null) {
            ErrorMsg="Patient not found.";
            return null;
        }
        ErrorMsg="";
        return patient;
    }
    
    public boolean updatePatient(String patientID, String firstName, 
            String lastName, int age, String gender, String medicalCondition){
        
        Patient patient = bed.findPatient(patientID);
        if (patient==null) {
            ErrorMsg="Patient not found.";
            return false;
        }
        
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setAge(age);
        patient.setGender(gender);
        patient.setMedicalCondition(medicalCondition);
        ErrorMsg="";
        return true;
    }
    
    public boolean deletePatient(String patientID) {
        Patient patient = bed.findPatient(patientID);
        if (patient == null) {
            ErrorMsg ="";
            return false;
        }
        
        if (patient instanceof Inpatient) {
            Inpatient inpatient = (Inpatient) patient;
            if (inpatient.getBedNumber()!=null) {
                bed.releaseBed(inpatient.getBedNumber());
            }
        }
        
       boolean removed = bed.removePatient(patientID);
       if (removed) {
           ErrorMsg="";
           return true;
       } else {
           ErrorMsg = "Unable to delete patient.";
           return false;
       }
    }
    
    public String allocateBed(String patientID) {
        Patient patient = bed.findPatient(patientID);
        if (patient==null) {
            ErrorMsg="Patient not found.";
            return null;
        }
        
        if (!(patient instanceof Inpatient)) {
            ErrorMsg="Only Inpatients can be allocated a bed.";
            return null;
        }
        
        Inpatient inpatient = (Inpatient) patient;
        
        if (inpatient.getBedNumber()!=null) {
            ErrorMsg="Patient already has a bed.";
            return null;
        }
        
        if (!bed.haveAvailableBeds()) {
            ErrorMsg = "No beds available.";
            return null;
        }
        
        boolean allocated = bed.allocateBed(inpatient);
        if (allocated) {
            ErrorMsg="";
            return inpatient.getBedNumber();
        } else {
            ErrorMsg = bed.getErrorMsg();
            return null;
        }
        
    }
    
    public boolean releaseBed(String bedID) {
        boolean released = bed.releaseBed(bedID);
        if (!released) {
            ErrorMsg=bed.getErrorMsg();
            return false;
        }
        ErrorMsg="";
        return true;
     
    }
    
    public void displayAllPatients() {
        if (bed.getPatientCount() == 0) {
            System.out.println();
            System.out.println("No patients registered.");
        }
        
        System.out.println();
        System.out.println("=== ALL REGISTERED PATIENTS ===");
        System.out.println("ID | First Name | Last Name | Age | Gender "
                + "| Condition | Category | Ward | Bed");
        System.out.println("---------------------------------------------------"
                + "-------------------------");
        
        ArrayList<Patient> patients = bed.getAllPatients();
        for (int i=0; i<patients.size(); i++) {
            Patient p = patients.get(i);
            System.out.println(p);
        }
        System.out.println("Total: "+bed.getPatientCount()+" patients.");
    }
    
    public void displayAvailableBeds() {
        bed.displayAvailableBeds();
    }
    
     public void displayOccupiedBeds() {
        bed.displayOccupiedBeds();
    }
    
    public void displayWardLayout() {
        bed.displayLayout();
    }
    
    public int getTotalPatients() {
        return bed.getPatientCount();
    }
    
    public int getOccupiedBedsCount() {
        return bed.getOccupiedCount();
    }
    
    public double getOccupancyPercentage() {
        return bed.getOccupancyPercentage();
    }
    
    public String getLastErrorMessage() {
        return ErrorMsg;
    }
    
    public void generateReports() {
        System.out.println();
        System.out.println("============================================================");
        System.out.println("=== WARD REPORTS ===");
        System.out.println("============================================================");
        
        displayAllPatients();
        System.out.println();
        System.out.println("------------------------------------------------------------");
        displayWardLayout();
        System.out.println("------------------------------------------------------------");
        displayAvailableBeds();
        displayOccupiedBeds();
        System.out.println("------------------------------------------------------------");
        System.out.println("Total Registered Patients: "+ getTotalPatients());
        System.out.println("Total Occupied Beds: "+getOccupiedBedsCount()+"/20");
        System.out.println("Ward Occupancy: "+getOccupancyPercentage()+"%");
        System.out.println("============================================================");
    }
    
    
    
    
}
