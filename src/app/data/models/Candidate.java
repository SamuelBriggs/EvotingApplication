package app.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Candidates")
public class Candidate {
    @Id
    private String id;
    private String name;
    private int age;
    private String gender;
    private Party party;
    private Position position;
    private int votes;
    private String bio;


}
