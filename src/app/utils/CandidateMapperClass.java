package app.utils;

import app.data.models.Candidate;
import app.dtos.requests.CandidateRegisterRequest;
import app.dtos.responses.SavedCandidateResponse;
import app.dtos.responses.VotedCandidateResponse;

import java.util.List;

public class CandidateMapperClass {

    public static Candidate mapCandidateRegister (CandidateRegisterRequest candidateRegisterRequest){
        Candidate candidate = new Candidate();
        candidate.setName(candidateRegisterRequest.getName());
        candidate.setParty(candidateRegisterRequest.getParty());
        candidate.setPosition(candidateRegisterRequest.getPosition());
        candidate.setGender(candidateRegisterRequest.getGender());
        candidate.setAge(candidateRegisterRequest.getAge());
        candidate.setBio(candidateRegisterRequest.getBio());
        candidate.setVotes(candidateRegisterRequest.getVotes());

        return candidate;
    }

    public static void viewSavedCandidate(Candidate candidate, SavedCandidateResponse candidateResponse){
        candidateResponse.setName(candidate.getName());
        candidateResponse.setPosition(candidate.getPosition());
        candidateResponse.setParty(candidate.getParty());
        candidateResponse.setBio(candidate.getBio());
        candidateResponse.setId(candidate.getId());
        candidateResponse.setAge(candidate.getAge());
        candidateResponse.setGender(candidate.getGender());
        candidateResponse.setVotes(candidate.getVotes());

    }

    public static void viewVotedCandidate(Candidate candidate, VotedCandidateResponse candidateResponse){
        candidateResponse.setFullName(candidate.getName());
        candidateResponse.setCurrentVotes(candidate.getVotes());
        candidateResponse.setPosition(candidate.getPosition());
        candidateResponse.setParty(candidate.getParty());
    }

    public static List<Candidate> listOfCandidates(List<Candidate> candidates){
        return candidates;
    }

    public static String returnString (String bio){
        return bio;
    }
}
