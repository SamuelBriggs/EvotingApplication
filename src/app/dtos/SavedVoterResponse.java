package app.dtos;

public class SavedVoterResponse {
    private int id;
    private String firstName;

    private String lastName;
    private int age;
    private String gender;
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    private boolean has_voted = false;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHas_voted() {
        return has_voted;
    }

    public void setHas_voted(boolean has_voted) {
        this.has_voted = has_voted;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
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

    public String getState() {
        return state;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("""
                Welcome, %s!
                
                Your id is %s.
                
                
                
                """, getFirstName(), getId());
    }
}
