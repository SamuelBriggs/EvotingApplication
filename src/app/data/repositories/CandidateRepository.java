package app.data.repositories;

import app.data.models.Candidate;
import app.data.models.Party;
import app.data.models.Position;

import java.util.List;

public interface CandidateRepository {
    Candidate save(Candidate candidate);
    Candidate findCandidate(int id);

    Candidate findCandidateByPositionAndParty(Position position, Party party);

    int countAllCandidates();

    List<Candidate> findAllCandidatesByPosition(Position position);

    List<Candidate> findAllCandidatesByParty(Party party);

    List<Candidate> returnAllCandidates();



}
