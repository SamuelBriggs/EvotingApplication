package app.data.repositories;

import app.data.models.Voter;

import java.util.List;

public interface VoterRepository {
    Voter save(Voter voter);
    Voter findVoterById(int id);
    int countAllVoters();
    void delete(int id);
    void deleteAll();
    List<Voter> returnAllVoter();


}
