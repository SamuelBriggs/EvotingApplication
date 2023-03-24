package app.dtos.requests;

import app.data.models.Party;
import app.data.models.Position;
import lombok.Data;

@Data
public class CandidateRegisterRequest {

    private String id;
    private String name;
    private int age;
    private String gender;
    private Party party;
    private Position position;
    private int votes;
    private String bio;



}