package app.dtos.requests;

import lombok.Data;

@Data
public class RegisterRequest {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String state;

}


