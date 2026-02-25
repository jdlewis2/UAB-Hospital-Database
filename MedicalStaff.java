package Hw3;

//MedicalStaff is an abstract base class extending UABPerson for all medical personnel
//It provides details and behavior for medical roles like doctors and nurses
//this class can't be used to create objects directly; it must be extended by medical staff classes like my nurse and doctor classes

public abstract class MedicalStaff extends UABPerson {
    
    private String role;
    private String department;
    
    
    public MedicalStaff(String firstName, String lastName, String blazerId, String role, String department) {
        super(firstName, lastName, blazerId);
        this.role = role;
        this.department = department;
    }
    
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    @Override
    public String toString() {
        return super.toString() + " Role: " + role + " Department: " + department;
    }
}