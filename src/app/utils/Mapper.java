package app.utils;

import app.data.models.Voter;
import app.dtos.SavedVoterResponse;
import app.dtos.requests.RegisterRequest;
import app.dtos.responses.FindVoterResponse;

public class Mapper {
    public static Voter savedVoterMap(RegisterRequest registerRequest){
        Voter voter = new Voter();
        voter.setFirstName(registerRequest.getFirstName());
        voter.setLastName(registerRequest.getLastName());
        voter.setGender(registerRequest.getGender());
        voter.setState(registerRequest.getState());
        voter.setId(registerRequest.getId());
        voter.setAge(registerRequest.getAge());
        return voter;
    }


    public static void map (Voter voter, FindVoterResponse response){
        response.setGender(voter.getGender());
        response.setState(voter.getState());
        response.setFullName(voter.getFirstName() + " " + voter.getLastName());
        response.setAge(voter.getAge());
        response.setVoterId(voter.getId());
    }

    public static void returnViewOfSavedVoter(Voter voter, SavedVoterResponse response){
        response.setFirstName(voter.getFirstName());
        response.setLastName(voter.getLastName());
        response.setState(voter.getState());
        response.setGender(voter.getGender());
        response.setId(voter.getId());
        response.setAge(voter.getAge());

    }
}
