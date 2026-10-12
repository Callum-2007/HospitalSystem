/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hospitalsystem;
import com.mycompany.medicarehospitalsystem.Patient;
import com.mycompany.medicarehospitalsystem.PatientCategory;
import com.mycompany.medicarehospitalsystem.SystemLogic;
import java.util.Scanner;
/**
 *
 * @author emeris
 */
public class HospitalSystem {

    private static SystemLogic system = new SystemLogic();
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int choice;
        
        System.out.println();
        System.out.println("====================================================");
        System.out.println("=== Medicare Hospital Patient Admission System ===");
        System.out.println("====================================================");
        
       do {
           displayMenu();
           choice = getInt("Enter your choice: ");
           
           switch (choice) {
               case 1: registerPatient(); break;
               case 2: searchPatient(); break;
               case 3: updatePatient(); break;
               case 4: deletePatient(); break;
               case 5: system.displayAllPatients(); break;
               case 6: allocateBed(); break;
               case 7: releaseBed(); break;
               case 8: system.displayWardLayout(); break;
               case 9: system.displayAvailableBeds(); break;
               case 10: system.displayOccupiedBeds(); break;
               case 11: system.generateReports(); break;
               case 12: System.out.println();
                        System.out.println("Thank you for using the system. "
                                + " Goodbye."); break;
               default:
                   System.out.println("Invalid choice! Please try again.");             
           }
           
           
       } while (choice != 12);
       input.close();
       
    }
    
   private static void displayMenu() {
       System.out.println();
       System.out.println("-------------------------------------------");
       System.out.println("1. Register New patient");
       System.out.println("2. Search Patient");
       System.out.println("3. Update Patient Details");
       System.out.println("4. Delete Patient");
       System.out.println("5. Display All Patients");
       System.out.println("6. Allocate Bed");
       System.out.println("7. Release Bed");
       System.out.println("8. Display Ward Layout");
       System.out.println("9. Display Available Beds");
       System.out.println("10. Display Occupied Beds");
       System.out.println("11. Generate Reports");
       System.out.println("12. Exit");
       System.out.println("-------------------------------------------");
   }
   private static void registerPatient() {
       System.out.println();
       System.out.println("--- REGISTER NEW PATIENT ---");
       
       System.out.print("First name: ");
       String firstName = input.nextLine().trim();
       
       System.out.print("Last name: ");
       String lastName = input.nextLine().trim();
       
       int age = getInt("Age: ");
       
       System.out.print("Gender: ");
       String gender = input.nextLine().trim();
       
       System.out.print("Medical Condition: ");
       String condition = input.nextLine().trim();
       
       System.out.println();
       System.out.println("Categories:");
       System.out.println("1. Inpatient");
       System.out.println("2. Outpatient");
       System.out.println("3. Emergency");
       
       int catChoice = getInt("Select category (1-3): ");
        PatientCategory category;
        if (catChoice == 1) {
            category = PatientCategory.INPATIENT;
        } else if (catChoice == 2) {
            category = PatientCategory.OUTPATIENT;
        } else if (catChoice == 3) {
            category = PatientCategory.EMERGENCY;
        } else {
            System.out.println("Invalid choice. Defaulting to Outpatient.");
            category = PatientCategory.OUTPATIENT;
        }

       boolean registered = system.registerPatient(firstName, lastName, age, gender, condition, category);
       if (registered) {
           System.out.println();
           System.out.println("Patient registered successfully!");
           Patient p = system.searchPatient("P" + (1001 + system.getTotalPatients() - 1));
           if (p != null) {
               System.out.println("Patient ID: "+p.getPatientId());
           }
           
           if (category == PatientCategory.INPATIENT) {
               System.out.print("Allocate bed now? (y/n): ");
               String response = input.nextLine().trim();
               if (response.equals("y") || response.equals("Y")) {
                   Patient patient = system.searchPatient("P"+ (1001 + system.getTotalPatients() - 1));
                 if (patient!=null)  {
                     String bedId = system.allocateBed(patient.getPatientId());
                 } else {
                     System.out.println("Error: "+ system.getLastErrorMessage());
                 }
               }
           }
       } else {
           System.out.println("Error: " + system.getLastErrorMessage());
       }
   }

   private static void searchPatient() {
       System.out.println();
       System.out.println("--- SEARCH PATIENT ---");
       System.out.print("Enter Patient ID: ");
       String id = input.nextLine().trim();
       
       Patient patient = system.searchPatient(id);
       if (patient != null) {
           System.out.println();
           System.out.println("Patient found:");
           System.out.println("----------------------------------------");
           patient.displayDetails();
           System.out.println("----------------------------------------");
       } else {
           System.out.println("Error: " + system.getLastErrorMessage());
       }
   }
   
   private static void updatePatient() {
       System.out.println();
       System.out.println("--- UPDATE PATIENT ---");
       System.out.print("Enter Patient ID: ");
       String id = input.nextLine().trim();
       
       Patient existing = system.searchPatient(id);
       if (existing == null) {
           System.out.println("Error: "+system.getLastErrorMessage());
           return;
       }
       
       System.out.println();
       System.out.println("Current details:");
       existing.displayDetails();
        
       System.out.println();
       System.out.println("Enter new details (press Enter to keep current):");
        
       System.out.print("First Name [" + existing.getFirstName() + "]: ");
       String firstName = input.nextLine().trim();
       if (firstName.length() == 0) firstName = existing.getFirstName();
       
       System.out.print("Last Name [" + existing.getLastName() + "]: ");
       String lastName = input.nextLine().trim();
       if (lastName.length() == 0) lastName = existing.getLastName();
       
       System.out.print("Age [" + existing.getAge() + "]: ");
       String ageStr = input.nextLine().trim();
        int age = existing.getAge();
        if (ageStr.length() != 0) {
            try {
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Keeping current value.");
            }
        }
        
           
        System.out.print("Gender [" + existing.getGender() + "]: ");
        String gender = input.nextLine().trim();
        if (gender.length() == 0) gender = existing.getGender();
       
        System.out.print("Medical Condition [" + existing.getMedicalCondition() + "]: ");
        String condition = input.nextLine().trim();
        if (condition.length() == 0) condition = existing.getMedicalCondition();
       
        boolean updated = system.updatePatient(id, firstName, lastName, age, gender, condition);
        if (updated) {
            System.out.println("Patient updated successfully!");
        } else {
            System.out.println("Error: " + system.getLastErrorMessage());
        }     
   }
   
   private static void deletePatient() {
       System.out.println();
       System.out.println("--- DELETE PATIENT ---");
       System.out.print("Enter Patient ID: ");
       String id = input.nextLine().trim();
       
        Patient patient = system.searchPatient(id);
        if (patient == null) {
            System.out.println("Error: " + system.getLastErrorMessage());
            return;
        }
        
       System.out.println();
       System.out.println("Patient to delete:");
       patient.displayDetails(); 
       
       System.out.print("Are you sure? (y/n): ");
       String confirm = input.nextLine().trim();
       
       if (confirm.equals("y") || confirm.equals("Y")) {
            boolean deleted = system.deletePatient(id);
            if (deleted) {
                System.out.println("Patient deleted successfully!");
            } else {
                System.out.println("Error: " + system.getLastErrorMessage());
            }
        } else {
            System.out.println("Deletion cancelled.");
        }    
   }
   
    private static void allocateBed() {
        System.out.println();
        System.out.println("--- ALLOCATE BED ---");
        System.out.print("Enter Patient ID: ");
        String id = input.nextLine().trim();
        
        String bedId = system.allocateBed(id);
        if (bedId != null) {
            System.out.println("Bed " + bedId + " allocated successfully!");
        } else {
            System.out.println("Error: " + system.getLastErrorMessage());
        }
    }
    
    private static void releaseBed() {
        System.out.println();
        System.out.println("--- RELEASE BED ---");
        System.out.print("Enter Bed ID (e.g., B01): ");
        String bedId = input.nextLine().trim().toUpperCase();
        
        boolean released = system.releaseBed(bedId);
        if (released) {
            System.out.println("Bed " + bedId + " released successfully!");
        } else {
            System.out.println("Error: " + system.getLastErrorMessage());
        }
    }
    
    private static int getInt(String prompt) {
        while (true) {
            //W3Schools, 2020. 
            //Java exceptions (try...catch). [online] W3Schools. 
            //Available at: <https://www.w3schools.com/java/java_try_catch.asp> 
            //[Accessed 6 September 2026].
            try {
                System.out.print(prompt);
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
      }
   }
}

