package app.services;

import app.data.models.Candidate;
import app.data.models.Party;
import app.data.models.Position;
import app.dtos.requests.CandidateRegisterRequest;

import java.util.List;

public interface CandidateService {
    Candidate register(CandidateRegisterRequest candidateRegisterRequest) throws IllegalAccessException;

    List <Candidate> viewAllCandidates();

    List <Candidate> viewAllCandidatesByPosition(Position position) throws IllegalAccessException;

    List <Candidate> viewAllCandidatesByParty(Party party);

    Candidate viewAllCandidatesByPositionAndParty(Position position, Party party);

    String viewCandidateBio(Position position, Party party);

    String viewResults();




}
