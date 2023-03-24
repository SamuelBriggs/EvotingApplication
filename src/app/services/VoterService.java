package app.services;

import app.data.models.Party;
import app.data.models.Position;
import app.data.models.Voter;
import app.dtos.SavedVoterResponse;
import app.dtos.requests.RegisterRequest;
import app.dtos.responses.FindVoterResponse;
import app.dtos.responses.SavedCandidateResponse;
import app.dtos.responses.VotedCandidateResponse;

import java.util.List;

public interface VoterService {
    SavedVoterResponse register(RegisterRequest registerRequest);
    FindVoterResponse findVoter(String id);
    List<Voter> findAllVoters();
    VotedCandidateResponse castVote(String voterId, Position position, Party party) throws IllegalAccessException;

    SavedCandidateResponse viewCandidate(Position position, Party party);

    String viewResults() throws IllegalAccessException;

}
