package app.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document ("Voters")
public class Voter {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String state;

    private boolean has_voted = false;



}
