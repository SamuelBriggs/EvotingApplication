package app.dtos.responses;

import lombok.Data;

@Data
public class FindVoterResponse {
    private String voterId;
    private String fullName;
    private int age;
    private String gender;
    private String state;
}



