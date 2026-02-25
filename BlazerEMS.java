package Hw3;

// BlazerEMS - Employee Management System for UAB
// Manages different types of  personnel (Faculty, Students, Staff, Medical)
// It contains functions that  displays, adds,  and deletes members. It also saves/loads data from the file UABEmployeetxt.
// As you may be able to tell the code is pretty long and the primary reason for that would be because...
// I also added navigation options to go back at any input stage if the user makes a mistake or just simply wants to go back.
// The go back feature is in the AddMembers function of code and the DeleteMembers function of code

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BlazerEMS {
	private ArrayList<UABPerson> uabMembers;
    private static final String FILENAME = "UABEmployee.txt";
    
    public BlazerEMS() {
        uabMembers = new ArrayList<>();
    }
   
    //getFromFile Reads employee data from the file and loads it into the system
    //creating appropriate objects for each employee type based on the first character of each line
    public void getFromFile() {
        try {
            File file = new File(FILENAME);
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                
                if (parts.length >= 1) {
                    char type = parts[0].charAt(0);
                    
                    switch (type) {
                        case 'F': 
                            if (parts.length >= 6) {
                                Faculty faculty = new Faculty(
                                    parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5])
                                );
                                uabMembers.add(faculty);
                            }
                            break;
                            
                        case 'S': // Student
                            if (parts.length >= 7) {
                                Student student = new Student(
                                    parts[1], parts[2], parts[3], parts[4], parts[5], Double.parseDouble(parts[6])
                                );
                                uabMembers.add(student);
                            }
                            break;
                            
                        case 'O':
                            if (parts.length >= 6) {
                                OfficeAssociates associate = new OfficeAssociates(
                                    parts[1], parts[2], parts[3], parts[4], parts[5]
                                );
                                uabMembers.add(associate);
                            }
                            break;
                            
                        case 'A':
                            if (parts.length >= 6) {
                                Advisors advisor = new Advisors(
                                    parts[1], parts[2], parts[3], parts[4], parts[5]
                                );
                                uabMembers.add(advisor);
                            }
                            break;
                            
                        case 'I': 
                            if (parts.length >= 5) {
                                ITProfessional itPro = new ITProfessional(
                                    parts[1], parts[2], parts[3], parts[4]
                                );
                                uabMembers.add(itPro);
                            }
                            break;
                            
                        case 'M': 
                            if (parts.length >= 5) {
                                if ("Nurse".equalsIgnoreCase(parts[4])) {
                                    boolean onCall = false;
                                    if (parts.length >= 7) {
                                        onCall = "Yes".equalsIgnoreCase(parts[6]);
                                    }
                                    Nurse nurse = new Nurse(
                                        parts[1], parts[2], parts[3], parts[4], parts[5], onCall
                                    );
                                    uabMembers.add(nurse);
                                } else {
                                    Doctor doctor = new Doctor(
                                        parts[1], parts[2], parts[3], parts[4], parts[5]
                                    );
                                    uabMembers.add(doctor);
                                }
                            }
                            break;
                    }
                }
            }
            
            scanner.close();
            System.out.println("Data loaded successfully from " + FILENAME);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + FILENAME);
            System.out.println("Creating a new empty database.");
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
    
    // The main(String[] args) function is the entry point of the program that initializes the system, loads existing data,
    // and presents a menu for users to play with different functions of the system
    public static void main(String[] args) {
        BlazerEMS EMS = new BlazerEMS();
        EMS.getFromFile();
        
        Scanner scanner = new Scanner(System.in);
        
        boolean running = true;
        while (running) {
            System.out.println("\nWelcome to the Blazer Employee Management System ");
            System.out.println("\nWhat would you like to do:");
            System.out.println("\n1. Display all UAB members");
            System.out.println("2. Add a new UAB member");
            System.out.println("3. Delete a UAB member");
            System.out.println("4. Save data to file");
            System.out.println("5. Exit File");
            System.out.print("\nPlease Enter an Option from above using one of the numbers: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        EMS.ShowAllMembers();
                        break;
                    case 2:
                        EMS.AddMembers(scanner);
                        break;
                    case 3:
                        EMS.DeleteMembers(scanner);
                        break;
                    case 4:
                        EMS.saveToFile();
                        break;
                    case 5:
                        running = false;
                        System.out.println("\nYou have Successfully exited the program...");
                        break;
                    default:
                        System.out.println("\nNot an existing choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        
        scanner.close();
}

    // this saveToFile function Writes all employee data from the system back to the file,
    // formatting each line according to the employee type with the right identifiers
    public void saveToFile() {
        try {
            FileWriter writer = new FileWriter(FILENAME);
            
            for (UABPerson person : uabMembers) {
                String line = "";
                
                if (person instanceof Faculty) {
                    Faculty faculty = (Faculty) person;
                    line = "F " + faculty.getFirstName() + " " + faculty.getLastName() + " " + 
                           faculty.getBlazerId() + " " + faculty.getDepartment() + " " + faculty.getCourses();
                } else if (person instanceof Student) {
                    Student student = (Student) person;
                    line = "S " + student.getFirstName() + " " + student.getLastName() + " " + 
                           student.getBlazerId() + " " + student.getLevel() + " " + 
                           student.getMajor() + " " + student.getGpa();
                } else if (person instanceof OfficeAssociates) {
                    OfficeAssociates associate = (OfficeAssociates) person;
                    line = "O " + associate.getFirstName() + " " + associate.getLastName() + " " + 
                           associate.getBlazerId() + " " + associate.getRole() + " " + associate.getDepartment();
                } else if (person instanceof Advisors) {
                    Advisors advisor = (Advisors) person;
                    line = "A " + advisor.getFirstName() + " " + advisor.getLastName() + " " + 
                           advisor.getBlazerId() + " " + advisor.getDepartment() + " " + advisor.getGroup();
                } else if (person instanceof ITProfessional) {
                    ITProfessional itPro = (ITProfessional) person;
                    line = "I " + itPro.getFirstName() + " " + itPro.getLastName() + " " + 
                           itPro.getBlazerId() + " " + itPro.getTeam();
                } else if (person instanceof Nurse) {
                    Nurse nurse = (Nurse) person;
                    line = "M " + nurse.getFirstName() + " " + nurse.getLastName() + " " + 
                           nurse.getBlazerId() + " " + "Nurse" + " " + nurse.getDepartment() + " " + 
                           (nurse.isOnCall() ? "Yes" : "No");
                } else if (person instanceof Doctor) {
                    Doctor doctor = (Doctor) person;
                    line = "M " + doctor.getFirstName() + " " + doctor.getLastName() + " " + 
                           doctor.getBlazerId() + " " + doctor.getRole() + " " + doctor.getDepartment();
                }
                
                writer.write(line + "\n");
            }
            
            writer.close();
            System.out.println("\nData saved successfully to " + FILENAME);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    
    //ShowAllMembers shows all UAB employees in the system, organized by 
    //their types (Faculty, Student, Office Associates, etc.) with counts for each category
    public void ShowAllMembers() {
        System.out.println("\nThe UAB Employee System has the following employees:");
        System.out.println("\nTotal Number of employees = " + uabMembers.size());
        
        
        ArrayList<Faculty> facultyList = new ArrayList<>();
        for (UABPerson person : uabMembers) {
            if (person instanceof Faculty) {
                facultyList.add((Faculty) person);
            }
        }
        System.out.println("\nFaculty: " + facultyList.size());
        for (Faculty faculty : facultyList) {
            System.out.println(faculty);
        }
        

        ArrayList<Student> studentList = new ArrayList<>();
        for (UABPerson person : uabMembers) {
            if (person instanceof Student) {
                studentList.add((Student) person);
            }
        }
        System.out.println("\nStudents: " + studentList.size());
        for (Student student : studentList) {
            System.out.println(student);
        }
        
     
        ArrayList<OfficeAssociates> officeassociatesList = new ArrayList<>();
        for (UABPerson person : uabMembers) {
            if (person instanceof OfficeAssociates) {
                officeassociatesList.add((OfficeAssociates) person);
            }
        }
        System.out.println("\nOffice Associates: " + officeassociatesList.size());
        for (OfficeAssociates office : officeassociatesList) {
            System.out.println(office);
        }
        
    
        ArrayList<Advisors> advisorList = new ArrayList<>();
        for (UABPerson person : uabMembers) {
            if (person instanceof Advisors) {
                advisorList.add((Advisors) person);
            }
        }
        System.out.println("\nAdvisors: " + advisorList.size());
        for (Advisors advisor : advisorList) {
            System.out.println(advisor);
        }
        

        ArrayList<ITProfessional> itList = new ArrayList<>();
        for (UABPerson person : uabMembers) {
            if (person instanceof ITProfessional) {
                itList.add((ITProfessional) person);
            }
        }
        System.out.println("\nIT Professionals: " + itList.size());
        for (ITProfessional it : itList) {
            System.out.println(it);
        }
        
       
        ArrayList<Doctor> doctorList = new ArrayList<>();
        ArrayList<Nurse> nurseList = new ArrayList<>();
        for (UABPerson person : uabMembers) {
            if (person instanceof Doctor) {
                doctorList.add((Doctor) person);
            } else if (person instanceof Nurse) {
                nurseList.add((Nurse) person);
            }
        }
        int medicalStaffTotal = doctorList.size() + nurseList.size();
        System.out.println("\nTotal Medical Staff: " + medicalStaffTotal);
        
        System.out.println("\nDoctors: " + doctorList.size());
        for (Doctor doctor : doctorList) {
            System.out.println(doctor);
        }
        
        System.out.println("\nNurses: " + nurseList.size());
        for (Nurse nurse : nurseList) {
            System.out.println(nurse);
        }
    }
    
    // AddMembers does exactly what the name says it adds a new UAB member to the system,
    // with different input paths based on employee type. It also has navigation options to go back at each step
    // Since there is alot of steps to this part the fact that I added go back options for each of them made the code much longer.
    public void AddMembers(Scanner scanner) {
        boolean inAddMemberMenu = true;
        
        while (inAddMemberMenu) {
            int type = 0;
            boolean validInput = false;
            
          
            while (!validInput) {
                System.out.println("\nSelect the type of person you'd like to add:");
                System.out.println("\n1. Faculty");
                System.out.println("2. Student");
                System.out.println("3. Office Associate");
                System.out.println("4. Advisor");
                System.out.println("5. IT Professional");
                System.out.println("6. Doctor");
                System.out.println("7. Nurse");
                System.out.println("0. Go back to the main menu");
                System.out.print("\nPlease Enter an Option from above using one of the numbers: ");
                
                try {
                    String input = scanner.nextLine();
                    type = Integer.parseInt(input);
                    if (type >= 0 && type <= 7) {
                        validInput = true;
                    } else {
                        System.out.println("Please enter a number between 0 and 7.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You can only choose a number between 0 and 7.");
                }
            }
            
        
            if (type == 0) {
                System.out.println("\nReturning to main menu...");
                return;
            }
            

            boolean getFirstName = true;
            String firstName = "";
            while (getFirstName) {
                System.out.println("\nEnter first name (or type '0' to go back to person type selection):");
                System.out.print("\nPlease enter an option: ");
                firstName = scanner.nextLine();
                
                if (firstName.equals("0")) {
                    System.out.println("Going back to person type selection...");
                    break;
                }
                
                if (firstName.trim().isEmpty()) {
                    System.out.println("First name cannot be empty. Please try again.");
                } else {
                    getFirstName = false;
                }
            }
            
            if (firstName.equals("0")) {
                continue;
            }
            
          
            boolean getLastName = true;
            String lastName = "";
            while (getLastName) {
                System.out.println("\nEnter last name (or type '0' to go back to first name):");
                System.out.print("Please enter an option: ");
                lastName = scanner.nextLine();
                
                if (lastName.equals("0")) {
                    System.out.println("Going back to first name...");
                    break;
                }
                
                if (lastName.trim().isEmpty()) {
                    System.out.println("Last name cannot be empty. Please try again.");
                } else {
                    getLastName = false;
                }
            }
            
            if (lastName.equals("0")) {
                continue;
            }
            
          
            boolean getBlazerId = true;
            String blazerId = "";
            while (getBlazerId) {
                System.out.println("\nEnter blazerId (or type '0' to go back to last name):");
                System.out.print("Please enter an option: ");
                blazerId = scanner.nextLine();
                
                if (blazerId.equals("0")) {
                    System.out.println("Going back to last name...");
                    break;
                }
                
                if (blazerId.trim().isEmpty()) {
                    System.out.println("BlazerID cannot be empty. Please try again.");
                } else {
                    getBlazerId = false;
                }
            }
            
            if (blazerId.equals("0")) {
                continue; 
            }
            
          
            switch (type) {
                case 1: 
                    
                    boolean getDepartment = true;
                    String fDepartment = "";
                    while (getDepartment) {
                        System.out.println("\nEnter department(ex. Math, CS) (or type '0' to go back to blazerId):");
                        System.out.print("Please enter an option: ");
                        fDepartment = scanner.nextLine();
                        
                        if (fDepartment.equals("0")) {
                            System.out.println("Going back to blazerId...");
                            break;
                        }
                        
                        if (fDepartment.trim().isEmpty()) {
                            System.out.println("Department cannot be empty. Please try again.");
                        } else {
                            getDepartment = false;
                        }
                    }
                    
                    if (fDepartment.equals("0")) {
                        continue; 
                    }
                    
                  
                    int courses = 0;
                    boolean getCourses = true;
                    while (getCourses) {
                        System.out.println("\nEnter number of courses (or type '0' to go back to department):");
                        System.out.print("Please enter an option: ");
                        String coursesInput = scanner.nextLine();
                        
                        if (coursesInput.equals("0")) {
                            System.out.println("Going back to department...");
                            break;
                        }
                        
                        try {
                            courses = Integer.parseInt(coursesInput);
                            getCourses = false;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number for courses.");
                        }
                    }
                    
                    if (courses == 0 && getCourses) {
                        continue; 
                    }
                    
                    uabMembers.add(new Faculty(firstName, lastName, blazerId, fDepartment, courses));
                    System.out.println("Faculty added successfully!");
                    inAddMemberMenu = false; 
                    break;
                    
                case 2:
                    
                    boolean getLevel = true;
                    String level = "";
                    while (getLevel) {
                        System.out.println("\nEnter level (Freshman, Sophomore, Junior, Senior) (or type '0' to go back to blazerId):");
                        System.out.print("Please enter an option: ");
                        level = scanner.nextLine();
                        
                        if (level.equals("0")) {
                            System.out.println("Going back to blazerId...");
                            break;
                        }
                        
                        if (level.trim().isEmpty()) {
                            System.out.println("Level cannot be empty. Please try again.");
                        } else {
                            getLevel = false;
                        }
                    }
                    
                    if (level.equals("0")) {
                        continue; 
                    }
                    
                    
                    boolean getMajor = true;
                    String major = "";
                    while (getMajor) {
                        System.out.println("\nEnter major (or type '0' to go back to level):");
                        System.out.print("Please enter an option: ");
                        major = scanner.nextLine();
                        
                        if (major.equals("0")) {
                            System.out.println("Going back to level...");
                            break;
                        }
                        
                        if (major.trim().isEmpty()) {
                            System.out.println("Major cannot be empty. Please try again.");
                        } else {
                            getMajor = false;
                        }
                    }
                    
                    if (major.equals("0")) {
                        continue;
                    }
                    
                  
                    double gpa = 0.0;
                    boolean getGpa = true;
                    while (getGpa) {
                        System.out.println("\nEnter GPA (or type '0' to go back to major):");
                        System.out.print("Please enter an option: ");
                        String gpaInput = scanner.nextLine();
                        
                        if (gpaInput.equals("0")) {
                            System.out.println("Going back to major...");
                            break;
                        }
                        
                        try {
                            gpa = Double.parseDouble(gpaInput);
                            getGpa = false;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number for GPA.");
                        }
                    }
                    
                    if (getGpa) {
                        continue; 
                    }
                    
                    uabMembers.add(new Student(firstName, lastName, blazerId, level, major, gpa));
                    System.out.println("\nThe given Student has been added successfully!");
                    inAddMemberMenu = false;
                    break;
                    
                case 3: 
                    
                    boolean getRole = true;
                    String oRole = "";
                    while (getRole) {
                        System.out.println("\nEnter role (or type '0' to go back to blazerId):");
                        System.out.print("Please enter an option: ");
                        oRole = scanner.nextLine();
                        
                        if (oRole.equals("0")) {
                            System.out.println("Going back to blazerId...");
                            break;
                        }
                        
                        if (oRole.trim().isEmpty()) {
                            System.out.println("Role cannot be empty. Please try again.");
                        } else {
                            getRole = false;
                        }
                    }
                    
                    if (oRole.equals("0")) {
                        continue; 
                    }
                    
                    boolean getODepartment = true;
                    String oDepartment = "";
                    while (getODepartment) {
                        System.out.println("\nEnter department(ex. Math, CS) (or type '0' to go back to role):");
                        System.out.print("Please enter an option: ");
                        oDepartment = scanner.nextLine();
                        
                        if (oDepartment.equals("0")) {
                            System.out.println("Going back to role...");
                            break;
                        }
                        
                        if (oDepartment.trim().isEmpty()) {
                            System.out.println("Department cannot be empty. Please try again.");
                        } else {
                            getODepartment = false;
                        }
                    }
                    
                    if (oDepartment.equals("0")) {
                        continue; 
                    }
                    
                    uabMembers.add(new OfficeAssociates(firstName, lastName, blazerId, oRole, oDepartment));
                    System.out.println("\nThe given office associate has been added successfully!");
                    inAddMemberMenu = false;
                    break;
                    
                case 4:
         
                    boolean getADepartment = true;
                    String aDepartment = "";
                    while (getADepartment) {
                        System.out.println("\nEnter department(ex. Math, CS) (or type '0' to go back to blazerId):");
                        System.out.print("Please enter an option: ");
                        aDepartment = scanner.nextLine();
                        
                        if (aDepartment.equals("0")) {
                            System.out.println("Going back to blazerId...");
                            break;
                        }
                        
                        if (aDepartment.trim().isEmpty()) {
                            System.out.println("Department cannot be empty. Please try again.");
                        } else {
                            getADepartment = false;
                        }
                    }
                    
                    if (aDepartment.equals("0")) {
                        continue;
                    }
                    
                    
                    boolean getGroup = true;
                    String group = "";
                    while (getGroup) {
                        System.out.println("\nEnter group (Undergraduate, Graduate) (or type '0' to go back to department):");
                        System.out.print("Please enter an option: ");
                        group = scanner.nextLine();
                        
                        if (group.equals("0")) {
                            System.out.println("Going back to department...");
                            break;
                        }
                        
                        if (group.trim().isEmpty()) {
                            System.out.println("Group cannot be empty. Please try again.");
                        } else {
                            getGroup = false;
                        }
                    }
                    
                    if (group.equals("0")) {
                        continue; 
                    }
                    
                    uabMembers.add(new Advisors(firstName, lastName, blazerId, aDepartment, group));
                    System.out.println("\nThe given advisor has been added successfully!");
                    inAddMemberMenu = false; 
                    break;
                    
                case 5: 
                
                    boolean getTeam = true;
                    String team = "";
                    while (getTeam) {
                        System.out.println("\nEnter team (or type '0' to go back to blazerId):");
                        System.out.print("Please enter an option: ");
                        team = scanner.nextLine();
                        
                        if (team.equals("0")) {
                            System.out.println("Going back to blazerId...");
                            break;
                        }
                        
                        if (team.trim().isEmpty()) {
                            System.out.println("Team cannot be empty. Please try again.");
                        } else {
                            getTeam = false;
                        }
                    }
                    
                    if (team.equals("0")) {
                        continue; 
                    }
                    
                    uabMembers.add(new ITProfessional(firstName, lastName, blazerId, team));
                    System.out.println("\nThe given IT professional has been added successfully!");
                    inAddMemberMenu = false; 
                    break;
                    
                case 6: 
                
                    boolean getDRole = true;
                    String dRole = "";
                    while (getDRole) {
                        System.out.println("\nEnter role (ex. Neurologist, Surgeon) (or type '0' to go back to blazerId):");
                        System.out.print("Please enter an option: ");
                        dRole = scanner.nextLine();
                        
                        if (dRole.equals("0")) {
                            System.out.println("Going back to blazerId...");
                            break;
                        }
                        
                        if (dRole.trim().isEmpty()) {
                            System.out.println("Role cannot be empty. Please try again.");
                        } else {
                            getDRole = false;
                        }
                    }
                    
                    if (dRole.equals("0")) {
                        continue; 
                    }
                    
                    
                    boolean getDoctorsDepartment = true;
                    String DoctorsDepartment = "";
                    while (getDoctorsDepartment) {
                        System.out.println("\nEnter department(ex. Math, CS) (or type '0' to go back to role):");
                        System.out.print("Please enter an option: ");
                        DoctorsDepartment = scanner.nextLine();
                        
                        if (DoctorsDepartment.equals("0")) {
                            System.out.println("Going back to role...");
                            break;
                        }
                        
                        if (DoctorsDepartment.trim().isEmpty()) {
                            System.out.println("Department cannot be empty. Please try again.");
                        } else {
                            getDoctorsDepartment = false;
                        }
                    }
                    
                    if (DoctorsDepartment.equals("0")) {
                        continue;
                    }
                    
                    uabMembers.add(new Doctor(firstName, lastName, blazerId, dRole, DoctorsDepartment));
                    System.out.println("\nThe given doctor has been added successfully!");
                    inAddMemberMenu = false; 
                    break;
                    
                case 7: 
            
                    boolean getNurseDepartment = true;
                    String NurseDepartment = "";
                    while (getNurseDepartment) {
                        System.out.println("\nEnter department(ex. Math, CS) (or type '0' to go back to blazerId):");
                        System.out.print("Please enter an option: ");
                        NurseDepartment = scanner.nextLine();
                        
                        if (NurseDepartment.equals("0")) {
                            System.out.println("Going back to blazerId...");
                            break;
                        }
                        
                        if (NurseDepartment.trim().isEmpty()) {
                            System.out.println("Department cannot be empty. Please try again.");
                        } else {
                            getNurseDepartment = false;
                        }
                    }
                    
                    if (NurseDepartment.equals("0")) {
                        continue; 
                    }
                    
                   
                    boolean getOnCall = true;
                    boolean onCall = false;
                    while (getOnCall) {
                        System.out.println("\nIs on call? (Yes/No) (or type '0' to go back to department):");
                        System.out.print("Please enter an option: ");
                        String onCallInput = scanner.nextLine();
                        
                        if (onCallInput.equals("0")) {
                            System.out.println("Going back to department...");
                            break;
                        }
                        
                        if (onCallInput.equalsIgnoreCase("Yes") || onCallInput.equalsIgnoreCase("no")) {
                            onCall = onCallInput.equalsIgnoreCase("yes");
                            getOnCall = false;
                        } else {
                            System.out.println("Please enter 'Yes' or 'No'.");
                        }
                    }
                    
                    if (getOnCall) {
                        continue; 
                    }
                    
                    uabMembers.add(new Nurse(firstName, lastName, blazerId, "Nurse", NurseDepartment, onCall));
                    System.out.println("\nThe given nurse has been added successfully!");
                    inAddMemberMenu = false; 
                    break;
            }
        }
    }
    
    //DeleteMembers(Scanner scanner)- Removes the UAB members from the system, 
    //this allows users to select employee type and then identify the specific employee by their BlazerID
    //This function of code also includes the go back features
    public void DeleteMembers(Scanner scanner) {
        boolean stayInDeleteMenu = true;
        
        while (stayInDeleteMenu) {
            int type = 0;
            boolean validInput = false;
            
            while (!validInput) {
                System.out.println("\nSelect the type of person you'd like to delete:");
                System.out.println("\n1. Faculty");
                System.out.println("2. Student");
                System.out.println("3. Office Associate");
                System.out.println("4. Advisor");
                System.out.println("5. IT Professional");
                System.out.println("6. Doctor");
                System.out.println("7. Nurse");
                System.out.println("0. Go back to the main menu");
                System.out.print("\nPlease Enter an Option from above using one of the numbers: ");
                
                try {
                    String input = scanner.nextLine();
                    type = Integer.parseInt(input);
                    if (type >= 0 && type <= 7) {
                        validInput = true;
                    } else {
                        System.out.println("Please enter a number between 0 and 7.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }
                        if (type == 0) {
                System.out.println("\nReturning to main menu...");
                return;
            }
            
            boolean stayWithSameType = true;
            while (stayWithSameType) {
                System.out.println("\nPlease Enter the blazerId of the member you'd like to delete:");
                System.out.println("(or you can Enter '0' to go back to the person type selection)");
                System.out.print("\nPlease Enter an Option: ");
                String blazerId = scanner.nextLine();
                
                if (blazerId.equals("0")) {
                    System.out.println("Going back to person type selection...");
                    stayWithSameType = false;
                    continue; 
                }
                
                Class<?> targetClass = null;
                
                switch (type) {
                    case 1: targetClass = Faculty.class; break;
                    case 2: targetClass = Student.class; break;
                    case 3: targetClass = OfficeAssociates.class; break;
                    case 4: targetClass = Advisors.class; break;
                    case 5: targetClass = ITProfessional.class; break;
                    case 6: targetClass = Doctor.class; break;
                    case 7: targetClass = Nurse.class; break;
                    default:
                        System.out.println("Invalid option selected!");
                        stayWithSameType = false;
                        continue;
                }
                
                UABPerson personToRemove = null;
                
                for (UABPerson person : uabMembers) {
                    if (targetClass.isInstance(person) && person.getBlazerId().equals(blazerId)) {
                        personToRemove = person;
                        break;
                    }
                }
                
                if (personToRemove != null) {
                    uabMembers.remove(personToRemove);
                    System.out.println("\nMember with the blazerId '" + blazerId + "' has been deleted successfully!");
                    stayWithSameType = false; 
                    stayInDeleteMenu = false; 
                } else {
                    System.out.println("\nNo Member found with that role and blazerId!");
                   
                    System.out.println("\nWhat would you like to do?");
                    System.out.println("\n1. Try another BlazerID for this type");
                    System.out.println("2. Go back to person type selection");
                    System.out.println("3. Return to main menu");
                    System.out.print("\nPlease Enter an Option: ");
                    
                    int choice = 0;
                    try {
                        choice = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        choice = 1;
                    }
                    
                    switch (choice) {
                        case 1:
                            
                            break;
                        case 2:
                           
                            stayWithSameType = false;
                            break;
                        case 3:
                            
                            return;
                        default:
                            
                            break;
                    }
                }
            }
        }
    }
}