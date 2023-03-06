package app.data.repositories;

import app.data.models.Voter;

import java.util.ArrayList;
import java.util.List;

public class VoterRepositoryImpl implements VoterRepository {

    private List<Voter> voters = new ArrayList<>();
    private int count = 1;

    @Override
    public Voter save(Voter voter) {
        boolean voterHasNotBeenSavedBefore = voter.getId()==0;
        if(voterHasNotBeenSavedBefore) {
            voters.add(voter);
            voter.setId(generateID());
            count++;

        }
        return voter;
    }

    private int generateID() {
        return count;
    }

    @Override
    public Voter findVoterById(int id) {
        for (int i = 0; i < voters.size() ; i++) {
            Voter voter = voters.get(i);
            boolean validVoter = voter.getId() == id;
            if (validVoter) return voter;
        }
        return null;
    }

    @Override
    public int countAllVoters() {
        return voters.size();
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < voters.size() ; i++) {
            Voter voter = voters.get(i);
            boolean validVoter = voter.getId() == id;
            if (validVoter) voters.remove(voter);
        }
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Voter> returnAllVoter() {
        return voters;
    }


}
