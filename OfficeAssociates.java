package Hw3;

// This class adds role and department attributes specific to office personnel

public class OfficeAssociates extends UABPerson {
	private String role;
    private String department;
    
    
    public OfficeAssociates(String firstName, String lastName, String blazerId, String role, String department) {
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