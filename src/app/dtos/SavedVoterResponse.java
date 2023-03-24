package app.dtos;

import lombok.Data;

@Data
public class SavedVoterResponse {
    private String id;
    private String firstName;

    private String lastName;
    private int age;
    private String gender;
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    private boolean has_voted = false;

}
