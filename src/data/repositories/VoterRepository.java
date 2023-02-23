package data.repositories;

import data.models.Voter;

public interface VoterRepository {
    Voter save(Voter voter);
    Voter findVoterById(int id);
    int countAllVoters();
    void delete(int id);
    void deleteAll();


}
