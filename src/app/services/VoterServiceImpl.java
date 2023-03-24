package app.services;

import app.data.models.Candidate;
import app.data.models.Party;
import app.data.models.Position;
import app.data.models.Voter;
import app.data.repositories.VoterRepository;
import app.dtos.SavedVoterResponse;
import app.dtos.requests.RegisterRequest;
import app.dtos.responses.FindVoterResponse;
import app.dtos.responses.SavedCandidateResponse;
import app.dtos.responses.VotedCandidateResponse;
import app.utils.CandidateMapperClass;
import app.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class VoterServiceImpl implements VoterService{
    @Autowired
    private VoterRepository voterRepository;
    @Autowired
    private CandidateService candidateService;

    @Override
    public SavedVoterResponse register(RegisterRequest registerRequest) {
        SavedVoterResponse voterResponse = new SavedVoterResponse();
        Voter voter =  voterRepository.save(Mapper.savedVoterMap(registerRequest));
        Mapper.returnViewOfSavedVoter(voter, voterResponse);
        return voterResponse;
    }

    @Override
    public FindVoterResponse findVoter(String id) {
        FindVoterResponse voterResponse = new FindVoterResponse();
        Voter voter = voterRepository.findVoterById(id);
        Mapper.map(voter, voterResponse);
        return  voterResponse;

    }

    @Override
    public List<Voter> findAllVoters() {
        return voterRepository.findAll();

    }

    @Override
    public VotedCandidateResponse castVote(String voterId, Position position, Party party) throws IllegalAccessException {
        boolean invalidId = voterRepository.findVoterById(voterId) == null;
        if (invalidId) throw new IllegalAccessException("Id is invalid. Enter again.");
        Voter voter = voterRepository.findVoterById(voterId);
        if (voter.isHas_voted() == true) throw new IllegalAccessException("You have already cast your vote");
           List<Candidate> candidates = candidateService.viewAllCandidates();
        for (int i = 0; i < candidates.size() ; i++) {
            Candidate candidate = candidates.get(i);
            if (position.equals(candidate.getPosition()) && party.equals(candidate.getParty())){
                candidate.setVotes(candidate.getVotes()+1);
                candidateService.save(candidate);
                voter.setHas_voted(true);
                voterRepository.save(voter);
                VotedCandidateResponse votedCandidateResponse = new VotedCandidateResponse();
                CandidateMapperClass.viewVotedCandidate(candidate, votedCandidateResponse);
                return votedCandidateResponse;
            }

        }
        throw new NoSuchElementException("No such Candidate Exists.");

    }
    @Override
    public SavedCandidateResponse viewCandidate(Position position, Party party) {
        return candidateService.viewAllCandidatesByPositionAndParty(position, party);
    }

    @Override
    public String viewResults() throws IllegalAccessException {
        return candidateService.viewResults();
    }


}
