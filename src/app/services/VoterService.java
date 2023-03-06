package app.services;

import app.data.models.Candidate;
import app.data.models.Party;
import app.data.models.Position;
import app.data.models.Voter;
import app.dtos.SavedVoterResponse;
import app.dtos.requests.RegisterRequest;

import java.util.List;

public interface VoterService {
    SavedVoterResponse register(RegisterRequest registerRequest);
    Voter findVoter(int id);
    List<Voter> findAllVoters();
    Candidate castVote(int voterId, Position position, Party party) throws IllegalAccessException;

    Candidate viewCandidate(Position position, Party party);

    String viewResults();

}
