package app.dtos.responses;

import app.data.models.Party;
import app.data.models.Position;
import lombok.Data;

@Data
public class VotedCandidateResponse {
    private String fullName;
    private Position position;
    private Party party;
    private int currentVotes;
}

