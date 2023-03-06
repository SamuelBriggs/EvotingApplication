package app.utils;

import app.data.models.Candidate;
import app.dtos.requests.CandidateRegisterRequest;
import app.dtos.responses.FindVoterResponse;
import app.data.models.Voter;
import app.dtos.SavedVoterResponse;
import app.dtos.requests.RegisterRequest;

public class Mapper {
    public static Voter savedVoterMap(RegisterRequest registerRequest){
        Voter voter = new Voter();
        voter.setFirstName(registerRequest.getFirstName());
        voter.setLastName(registerRequest.getLastName());
        voter.setGender(registerRequest.getGender());
        voter.setState(registerRequest.getState());
        voter.setId(registerRequest.getId());
        return voter;
    }

    public static Candidate mapCandidate(CandidateRegisterRequest candidateRegisterRequest){
        Candidate candidate = new Candidate();
        candidate.setName(candidateRegisterRequest.getName());
        candidate.setParty(candidateRegisterRequest.getParty());
        candidate.setPosition(candidateRegisterRequest.getPosition());
        candidate.setGender(candidateRegisterRequest.getGender());
        candidate.setAge(candidateRegisterRequest.getAge());
        candidate.setBio(candidateRegisterRequest.getBio());
        candidate.setVotes(candidateRegisterRequest.getVotes());
        return  candidate;
    }

    public static void map (Voter voter, FindVoterResponse response){
        response.setGender(voter.getGender());
        response.setState(voter.getState());
    }

    public static void returnViewOfSavedVoter(Voter voter, SavedVoterResponse response){
        response.setFirstName(voter.getFirstName());
        response.setLastName(voter.getLastName());
        response.setState(voter.getState());
        response.setGender(voter.getGender());
        response.setId(voter.getId());

    }
}
