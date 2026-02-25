package Hw3;

// This is the base class for all UAB persons (such as student, officeassociates, MedicalStaff, etc.)
// the first name, last name and blazerId is going to be used in every single class except blazerems

public class UABPerson {

    private String firstName;
    private String lastName;
    private String blazerId;
    

    public UABPerson(String firstName, String lastName, String blazerId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.blazerId = blazerId;
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
    
    public String getBlazerId() {
        return blazerId;
    }
    
    public void setBlazerId(String blazerId) {
        this.blazerId = blazerId;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    @Override
    public String toString() {
        return "Name: " + getFullName() + " BlazerId: " + blazerId;
    }
}