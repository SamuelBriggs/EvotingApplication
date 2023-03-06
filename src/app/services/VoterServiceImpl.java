package app.services;

import app.data.models.Candidate;
import app.data.models.Party;
import app.data.models.Position;
import app.data.models.Voter;
import app.data.repositories.VoterRepository;
import app.data.repositories.VoterRepositoryImpl;
import app.dtos.SavedVoterResponse;
import app.dtos.requests.RegisterRequest;
import app.utils.ElectionResults;
import app.utils.Mapper;

import java.util.List;
import java.util.NoSuchElementException;

public class VoterServiceImpl implements VoterService{
    private static VoterRepository voterRepository = new VoterRepositoryImpl();
    private CandidateService candidateService = new CandidateServiceImpl();

    @Override
    public SavedVoterResponse register(RegisterRequest registerRequest) {
        SavedVoterResponse voterResponse = new SavedVoterResponse();
        Voter voter =  voterRepository.save(Mapper.savedVoterMap(registerRequest));
        Mapper.returnViewOfSavedVoter(voter, voterResponse);
        return voterResponse;
    }

    @Override
    public Voter findVoter(int id) {
        Voter voter = voterRepository.findVoterById(id);
        return voter;
    }

    @Override
    public List<Voter> findAllVoters() {
        return voterRepository.returnAllVoter();

    }

    @Override
    public Candidate castVote(int voterId, Position position, Party party) throws IllegalAccessException {
        boolean invalidId = voterRepository.findVoterById(voterId) == null;
        if (invalidId) throw new IllegalAccessException("Id is invalid. Enter again.");
        Voter voter = voterRepository.findVoterById(voterId);
        if (voter.isHas_voted() == true) throw new IllegalAccessException("You have already cast your vote");
           List<Candidate> candidates = candidateService.viewAllCandidates();
        for (int i = 0; i < candidates.size() ; i++) {
            Candidate candidate = candidates.get(i);
            if (position.equals(candidate.getPosition()) && party.equals(candidate.getParty())){
                candidate.setVotes(candidate.getVotes()+1);
                voter.setHas_voted(true);
                return candidate;
            }

        }
        throw new NoSuchElementException("No such Candidate Exists.");

    }

    @Override
    public Candidate viewCandidate(Position position, Party party) {
        return candidateService.viewAllCandidatesByPositionAndParty(position, party);
    }

    @Override
    public String viewResults() {
        return ElectionResults.viewResults();
    }


}
