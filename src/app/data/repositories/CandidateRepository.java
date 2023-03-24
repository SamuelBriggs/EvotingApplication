package app.data.repositories;

import app.data.models.Candidate;
import app.data.models.Party;
import app.data.models.Position;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CandidateRepository extends MongoRepository<Candidate, String> {


    List<Candidate> findAllCandidatesByPosition(Position position);

    List<Candidate> findAllCandidatesByParty(Party party);

    Candidate findCandidateByPositionAndParty(Position position, Party party);


}
