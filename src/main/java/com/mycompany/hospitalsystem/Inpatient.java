/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicarehospitalsystem;

/**
 *
 * @author emeris
 */

//GeeksforGeeks, 2016b. 
//Super keyword in java. [online] GeeksforGeeks. 
//Available at: <https://www.geeksforgeeks.org/java/super-keyword/> 
//[Accessed 5 September 2026].

public class Inpatient extends Patient{
    private int wardNumber;
    private String bedNumber;
    
    public Inpatient(String patientID, String firstName, String lastName, 
            int age, String gender, String medicalCondition, int wardNumber,
            String bedNumber){
         super(patientID, firstName, lastName, age, gender, medicalCondition, 
              PatientCategory.INPATIENT);
        this.wardNumber = wardNumber;
        this.bedNumber = bedNumber;
        
    }
    
    public int getWardNumber() {
        return wardNumber;
    }
    public void setWardNumber(int wardNumber) {
        this.wardNumber = wardNumber;
    }
    
    public String getBedNumber() {
        return bedNumber;
    }
    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }
    //GeeksforGeeks, 2016a. 
    //Overriding in java. [online] GeeksforGeeks. 
    //Available at: <https://www.geeksforgeeks.org/java/overriding-in-java/> 
    //[Accessed 5 September 2026]
    
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Ward Number: "+ wardNumber);
        System.out.println("Bed Number: "+ bedNumber);
    }
    
    @Override
    public String toString() {
        return super.toString() + " | " + wardNumber + " | " + bedNumber;
    }
}
