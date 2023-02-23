package data.repositories;

import data.models.Candidate;
import data.models.Party;
import data.models.Position;

import java.util.ArrayList;
import java.util.List;

public class CandidateRepositoryImpl implements CandidateRepository{
   private List<Candidate> candidates = new ArrayList<>();

   private int count = 1;

    @Override
    public Candidate save(Candidate candidate) {
        boolean candidateHasNotBeenSavedBefore = candidate.getId() == 0;
       if(candidateHasNotBeenSavedBefore){ candidate.setId(generateId());
        candidates.add(candidate);
        count++;}
        return candidate;
    }

    private int generateId() {
        return count;
    }

    @Override
    public Candidate findCandidate(int id) {
        for (int i = 0; i < candidates.size() ; i++) {
            Candidate candidate = candidates.get(i);
            if (candidate.getId() == id) return candidate;
        }
        return null ;
    }

    @Override
    public Candidate findCandidateByPositionAndParty(Position position, Party party) {
        for (int i = 0; i < candidates.size() ; i++) {
            Candidate candidate = candidates.get(i);
            boolean validCandidate = (candidate.getPosition().equals(position) && candidate.getParty().equals(party));
            if (validCandidate) return candidate;
        }

        return null;
    }
    @Override
    public int countAllCandidates() {
        return candidates.size();
    }

    @Override
    public List<Candidate> findAllCandidatesByPosition(Position position) {
        List<Candidate> validCandidates = new ArrayList<>();
        for (int i = 0; i < candidates.size() ; i++) {
            Candidate candidate = candidates.get(i);
            boolean validCandidate = candidate.getPosition().equals(position);
            if (validCandidate) validCandidates.add(candidate);
        }
        return validCandidates;
    }

    @Override
    public List<Candidate> findAllCandidatesByParty(Party party) {
        List<Candidate> validPartyCandidates = new ArrayList<>();
        for (int i = 0; i < candidates.size() ; i++) {
            Candidate candidate = candidates.get(i);
            boolean validCandidate = candidate.getParty().equals(party);
            if(validCandidate) validPartyCandidates.add(candidate);
        }
        return validPartyCandidates;
    }
}
