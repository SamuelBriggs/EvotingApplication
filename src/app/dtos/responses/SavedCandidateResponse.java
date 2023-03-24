package app.dtos.responses;

import app.data.models.Party;
import app.data.models.Position;
import lombok.Data;

@Data
public class SavedCandidateResponse {
        private String id;
        private String name;
        private int age;
        private String gender;
        private Party party;
        private Position position;
        private int votes;
        private String bio;
    }


