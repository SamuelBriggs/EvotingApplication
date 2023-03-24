package app.data.repositories;

import app.data.models.Voter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoterRepository extends MongoRepository<Voter, String> {

    Voter findVoterById(String id);
}
