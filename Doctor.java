package Hw3;

//The doctor class extends MedicalStaff to represent doctor personnel
// This class adds doctor specific details like medical role/specialty and department

public class Doctor extends MedicalStaff {
   
    public Doctor(String firstName, String lastName, String blazerId, String role, String department) {
        super(firstName, lastName, blazerId, role, department);
    }
   
}