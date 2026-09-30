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
public class BedManagement {
    //GeeksforGeeks, 2021. 
    //Different ways to declare and initialize 2D array in java.[online] GeeksforGeeks.
    //Available at: <https://www.geeksforgeeks.org/java/different-ways-to-declare-and-initialize-2-d-array-in-java/> 
    //[Accessed 5 September 2026].
    private String[][] bedIDs;
    private Patient[][] bedOccupants;
    private static final int rows=4;
    private static final int cols=5;
    private String ErrorMsg="";
    //W3Schools, 2019. 
    //Java ArrayList. [online] W3schools.com. 
    //Available at: <https://www.w3schools.com/java/java_arraylist.asp> 
    //[Accessed 5 September 2026].
    private ArrayList<Patient> patients;
    
    public BedManagement() {
        bedIDs = new String[rows][cols];
        bedOccupants = new Patient[rows][cols];
        patients = new ArrayList<Patient>();
        initializeBeds();
    }
    
    // Ensure each bed is given unique ID
    private void initializeBeds(){
        int count = 1;
        for (int row = 0; row<rows; row++){
            for (int col=0; col<cols; col++){
                if (count<10) {
                    bedIDs[row][col] = "B0"+count;
                } else {
                    bedIDs[row][col] = "B"+count;
                }
                bedOccupants[row][col]=null;
                count++;
            }
        }
    }
    
    //Add patients to arraylist
    public boolean addPatient(Patient patient) {
        patients.add(patient);
        return true;
    }
    
    //Finding patient by patientID
    public Patient findPatient(String patientID) {
        for (int i=0; i<patients.size(); i++){
            Patient p = patients.get(i);
            if (p.getPatientId().equals(patientID)){
                return p;
            }
        }
       return null; 
    }
    
    //Delete patient by patientID
    public boolean removePatient(String patientID) {
        for (int i=0; i<patients.size(); i++) {
            Patient p = patients.get(i);
            if (p.getPatientId().equals(patientID)) {
                patients.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Patient> getAllPatients() {
        return patients;
    }
    
    public int getPatientCount() {
        return patients.size();
    }
    
    public boolean allocateBed(Patient patient) {
        for (int row=0; row<rows; row++) {
            for (int col=0; col<cols; col++) {
                if (bedOccupants[row][col] == null) {
                    bedOccupants[row][col] = patient;
                    String bedID = bedIDs[row][col];
                    
                    if (patient instanceof Inpatient) {
                        Inpatient inpatient = (Inpatient) patient;
                        inpatient.setBedNumber(bedID);
                        inpatient.setWardNumber(1);
                        
                    }
                    ErrorMsg ="";
                    return true;
                }
            }
        }
       ErrorMsg = "No beds available";
       return false;
    }   
    
    public boolean releaseBed(String bedID) {
        for (int row=0; row<rows; row++) {
            for (int col=0; col<cols; col++) {
                if (bedIDs[row][col].equals(bedID)) {
                    if (bedOccupants[row][col]!=null) {
                        Patient patient = bedOccupants[row][col];
                        if (patient instanceof Inpatient) {
                            Inpatient inpatient = (Inpatient) patient;
                            inpatient.setBedNumber(null);
                            inpatient.setWardNumber(0);
                        }
                        bedOccupants[row][col]=null;
                        ErrorMsg="";
                        return true;
                    } else {
                        ErrorMsg="Bed is already empty";
                        return false;
                    }
                }
            }
        }
        ErrorMsg="Bed not found";
        return false;
    }
    
    public void displayLayout() {
        System.out.println();
        System.out.println("=== BED LAYOUT ===");
        System.out.println("-----------------------------");
        
        for (int row=0; row<rows; row++) {
            for (int col=0; col<cols; col++) {
                String status;
                if (bedOccupants[row][col]==null){
                    status="Free";
                } else {
                    status="Occupied";
                }
                System.out.print("[" + bedIDs[row][col]+ ": "+ status +"]");
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
    }
    
    public void displayAvailableBeds() {
        System.out.println();
         System.out.println("=== AVAILABLE BEDS ===");
        boolean found = false;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (bedOccupants[row][col] == null) {
                    System.out.print(bedIDs[row][col] + " ");
                    found = true;
                }     
           }
       }  
        if (!found) {
            System.out.println("No beds available");
        } else {
            System.out.println();
        }
    }
   
    public void displayOccupiedBeds() {
         System.out.println();
         System.out.println("=== OCCUPIED BEDS ===");
         boolean found = false;
         
         for (int row=0; row<rows; row++) {
             for (int col=0; col<cols; col++) {
                 if (bedOccupants[row][col]!=null) {
                     System.out.println(bedIDs[row][col]);
                     found=true;
                 }
             }
         }
         if (!found) {
             System.out.println("No beds occupied");
         }
    }
    
    public int getOccupiedCount() {
        int count=0;
        for (int row=0; row<rows; row++) {
            for (int col=0; col<cols; col++) {
                if (bedOccupants[row][col]!=null) count++;
            }
        }
        return count;
    }
    
    public boolean haveAvailableBeds() {
        return getOccupiedCount()<(rows*cols);
    }
    
    public int getTotalBeds() {
        return rows*cols;
    }
    
    public double getOccupancyPercentage() {
        double occupied = getOccupiedCount();
        double total = getTotalBeds();
        return (occupied/total)*100;
    }
    
    public String getErrorMsg() {
        return ErrorMsg;
    }
}

