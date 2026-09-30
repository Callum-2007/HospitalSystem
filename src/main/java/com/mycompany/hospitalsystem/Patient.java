/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicarehospitalsystem;

/**
 *
 * @author emeris
 */
public class Patient {
     private String patientID;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String medicalCondition;
    private PatientCategory category;
    
    // Constructor
    public Patient(String patientID, String firstName, String lastName, int age,
             String gender, String medicalCondition,PatientCategory category) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.medicalCondition = medicalCondition;
        this.category = category;
    }
    
    // Getters and Setters
    public String getPatientId() { 
        return patientID; 
    }
    
    public void setPatientId(String patientId) { 
        this.patientID = patientId; 
    }
    
    public String getFirstName() { 
        return firstName; 
    }
    
    public void setFirstName(String firstName) { 
        this.firstName = firstName; 
    }
    
    public String getLastName() { 
        return lastName; 
    }
    
    public void setLastName(String lastName) { 
        this.lastName = lastName; 
    }
    
    public int getAge() { 
        return age; 
    }
    
    public void setAge(int age) { 
        this.age = age; 
    }
    
    public String getGender() { 
        return gender; 
    }
    
    public void setGender(String gender) { 
        this.gender = gender; 
    }
    
    public String getMedicalCondition() { 
        return medicalCondition; 
    }
    
    public void setMedicalCondition(String medicalCondition) { 
        this.medicalCondition = medicalCondition; 
    }
    
    public PatientCategory getCategory() { 
        return category; 
    }
    
    public void setCategory(PatientCategory category) { 
        this.category = category; 
    }
    
    // Display details method
    public void displayDetails() {
        System.out.println("Patient ID: " + patientID);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Medical Condition: " + medicalCondition);
        System.out.println("Category: " + category);
    }
    
     @Override
    public String toString() {
    return patientID + " | " + firstName + " | " + lastName + " | " + 
           age + " | " + gender + " | " + medicalCondition + " | " + category;
}
    
}
