package data.repositories;

import data.models.Candidate;
import data.models.Party;
import data.models.Position;

import java.util.List;

public interface CandidateRepository {
    Candidate save(Candidate candidate);
    Candidate findCandidate(int id);

    Candidate findCandidateByPositionAndParty(Position position, Party party);

    int countAllCandidates();

    List<Candidate> findAllCandidatesByPosition(Position position);

    List<Candidate> findAllCandidatesByParty(Party party);





}
