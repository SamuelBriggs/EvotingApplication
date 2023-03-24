package app.services;

import app.data.models.Candidate;
import app.data.models.Party;
import app.data.models.Position;
import app.data.repositories.CandidateRepository;
import app.dtos.requests.CandidateRegisterRequest;
import app.dtos.responses.SavedCandidateResponse;
import app.utils.CandidateMapperClass;
import app.utils.CandidateVotesComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public SavedCandidateResponse register(CandidateRegisterRequest candidateRegisterRequest) throws IllegalAccessException {
        List<Candidate> candidates = viewAllCandidates();
        for (int i = 0; i < candidates.size(); i++) {
            Candidate candidate = candidates.get(i);
            if(candidateRegisterRequest.getPosition().equals(candidate.getPosition()) && candidateRegisterRequest.getParty().equals(candidate.getParty())) throw new IllegalAccessException("Candidate with Party and Position already exists.");
        }
        Candidate candidate = candidateRepository.save(CandidateMapperClass.mapCandidateRegister(candidateRegisterRequest));
        SavedCandidateResponse candidateResponse = new SavedCandidateResponse();
         CandidateMapperClass.viewSavedCandidate(candidate, candidateResponse);
         return candidateResponse;
    }

    @Override
    public List<Candidate> viewAllCandidates() {
        return CandidateMapperClass.listOfCandidates(candidateRepository.findAll());
    }

    @Override
    public List<Candidate> viewAllCandidatesByPosition(Position position) throws IllegalAccessException {
        boolean validPosition = position.equals(Position.PRESIDENT) || position.equals(Position.GOVERNOR) || position.equals(Position.SENATOR);
         if (!validPosition) throw new IllegalAccessException("Input a valid Position");
        return CandidateMapperClass.listOfCandidates(candidateRepository.findAllCandidatesByPosition(position));

    }

    @Override
    public List<Candidate> viewAllCandidatesByParty(Party party) throws IllegalAccessException {
        boolean validParty = party.equals(Party.PDP) || party.equals(Party.LABOUR) || party.equals(Party.APC);
        if (!validParty) throw new IllegalAccessException("Input a valid Party");
        return CandidateMapperClass.listOfCandidates(candidateRepository.findAllCandidatesByParty(party));
    }

    @Override
    public SavedCandidateResponse viewAllCandidatesByPositionAndParty(Position position, Party party) throws NullPointerException {
        if (candidateRepository.findCandidateByPositionAndParty(position, party) == null) throw new NullPointerException("No Candidate Match");
        Candidate candidate =  candidateRepository.findCandidateByPositionAndParty(position, party);
        SavedCandidateResponse savedCandidateResponse = new SavedCandidateResponse();
        CandidateMapperClass.viewSavedCandidate(candidate, savedCandidateResponse);
        return savedCandidateResponse;
    }

    @Override
    public String viewCandidateBio(Position position, Party party) throws NullPointerException {
        if (candidateRepository.findCandidateByPositionAndParty(position, party) == null) throw new NullPointerException("No Candidate Match");
        Candidate candidate = candidateRepository.findCandidateByPositionAndParty(position, party);
        return CandidateMapperClass.returnString(candidate.toString());
    }

    @Override
    public String viewResults() throws IllegalAccessException {
        List<Candidate> candidates = calculateResults();

        String result = String.format("""
                ===============================================
                Winner = %s from Party %s with %s Votes. 
                1st Runner-up = %s from Party %s with %s Votes. 
                2nd Runner-up = %s from Party %s with %s Votes.
                ===============================================
                """, candidates.get(0).getName(), candidates.get(0).getParty(), candidates.get(0).getVotes(), candidates.get(1).getName(), candidates.get(1).getParty(), candidates.get(1).getVotes(), candidates.get(2).getName(), candidates.get(2).getParty(), candidates.get(2).getVotes());
        return result;
    }

    @Override
    public List<Candidate> calculateResults()  {
        Comparator<Candidate> desc = Collections.reverseOrder(new CandidateVotesComparator());
        List<Candidate> candidates = candidateRepository.findAll();
        List<Candidate> newCandidates;
        Collections.sort(candidates, desc);
        newCandidates = candidates;
        return newCandidates;
    }
}
