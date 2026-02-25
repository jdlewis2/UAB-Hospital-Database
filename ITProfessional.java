package Hw3;

//This class adds ITprofessional specific attributes like team assignment and technical specializations

public class ITProfessional extends UABPerson {

    private String team;

    public ITProfessional(String firstName, String lastName, String blazerId, String team) {
        super(firstName, lastName, blazerId);
        this.team = team;
    }
    
    public String getTeam() {
        return team;
    }
    
    public void setTeam(String team) {
        this.team = team;
    }
    
    @Override
    public String toString() {
        return super.toString() + " Team: " + team;
    }
}