package Hw3;

//The Nurse class extends MedicalStaff to represent nursing personnel
//It adds nurse specific details like on-call status and specialized nursing methods

public class Nurse extends MedicalStaff {
    
    private boolean onCall;
    
    
    public Nurse(String firstName, String lastName, String blazerId, String role, String department, boolean onCall) {
        super(firstName, lastName, blazerId, role, department);
        this.onCall = onCall;
    }
    
    public boolean isOnCall() {
        return onCall;
    }
    
    public void setOnCall(boolean onCall) {
        this.onCall = onCall;
    }
    
    @Override
    public String toString() {
        return super.toString() + " On Call: " + (onCall ? "Yes" : "No");
    }
}