package app.services;

import app.data.models.Candidate;
import app.data.models.Party;
import app.data.models.Position;
import app.data.repositories.CandidateRepository;
import app.data.repositories.CandidateRepositoryImpl;
import app.dtos.requests.CandidateRegisterRequest;
import app.utils.ElectionResults;
import app.utils.Mapper;

import java.util.List;

public class CandidateServiceImpl implements CandidateService {

    private static CandidateRepository candidateRepository = new CandidateRepositoryImpl();

    @Override
    public Candidate register(CandidateRegisterRequest candidateRegisterRequest) throws IllegalAccessException {
        List<Candidate> candidates = viewAllCandidates();
        for (int i = 0; i < candidates.size(); i++) {
            Candidate candidate = candidates.get(i);
            if(candidateRegisterRequest.getPosition().equals(candidate.getPosition()) && candidateRegisterRequest.getParty().equals(candidate.getParty())) throw new IllegalAccessException("Candidate with Party and Position already exists.");
        }
        return candidateRepository.save(Mapper.mapCandidate(candidateRegisterRequest));
    }

    @Override
    public List<Candidate> viewAllCandidates() {
       return candidateRepository.returnAllCandidates();
    }

    @Override
    public List<Candidate> viewAllCandidatesByPosition(Position position) throws IllegalAccessException {
        boolean validPosition = position.equals(Position.PRESIDENT) || position.equals(Position.GOVERNOR) || position.equals(Position.SENATOR);
         if (!validPosition) throw new IllegalAccessException("Input a valid Position");
        return candidateRepository.findAllCandidatesByPosition(position);

    }

    @Override
    public List<Candidate> viewAllCandidatesByParty(Party party) {
        return candidateRepository.findAllCandidatesByParty(party);
    }

    @Override
    public Candidate viewAllCandidatesByPositionAndParty(Position position, Party party) {
        return candidateRepository.findCandidateByPositionAndParty(position, party);
    }

    @Override
    public String viewCandidateBio(Position position, Party party) {
        Candidate candidate = candidateRepository.findCandidateByPositionAndParty(position, party);
        return candidate.toString();
    }

    @Override
    public String viewResults() {
        return ElectionResults.viewResults();
    }
}
