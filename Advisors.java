package Hw3;

//this class adds advisor specific attributes like department and student group (undergraduate/graduate)

public class Advisors extends UABPerson {
    private String department;
    private String group;

    public Advisors(String firstName, String lastName, String blazerId, String department, String group) {
        super(firstName, lastName, blazerId);
        this.department = department;
        this.group = group;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getGroup() {
        return group;
    }
    
    public void setGroup(String group) {
        this.group = group;
    }
    
    @Override
    public String toString() {
        return super.toString() + " Department: " + department + " Group: " + group;
    }
}