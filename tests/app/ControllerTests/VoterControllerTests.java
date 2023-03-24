package app.ControllerTests;

import app.controller.VoterController;
import app.data.models.Party;
import app.data.models.Position;
import app.data.models.Voter;
import app.dtos.requests.CandidateRegisterRequest;
import app.dtos.requests.RegisterRequest;
import app.dtos.responses.SavedCandidateResponse;
import app.services.CandidateService;
import app.services.CandidateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoterControllerTests {
    private VoterController voterController;
    private RegisterRequest registerRequest;

    @BeforeEach

    public void setUp(){
        voterController = new VoterController();
        registerRequest = new RegisterRequest();
    }

    @Test

    public void testThatVoterCanBeRegistered(){
        registerRequest.setFirstName("Samuel");
        registerRequest.setLastName("Alawode");
        registerRequest.setGender("Male");
        registerRequest.setState("Ogun State");
      var response =  voterController.registerVoter(registerRequest);
    }

    @Test

    public void testThatVoterCanViewAllVoters(){
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setFirstName("Joy");
        registerRequest1.setLastName("Alawode");
        registerRequest1.setGender("Female");
        registerRequest1.setState("Lagos State");
        voterController.registerVoter(registerRequest1);

        registerRequest.setFirstName("Samuel");
        registerRequest.setLastName("Alawode");
        registerRequest.setGender("Male");
        registerRequest.setState("Ogun State");
        voterController.registerVoter(registerRequest);

        System.out.println(voterController.findAllVoters());
    }

    @Test

    public void testThatVoterCanCastVote() throws IllegalAccessException {
        registerRequest.setFirstName("Samuel");
        registerRequest.setLastName("Alawode");
        registerRequest.setGender("Male");
        registerRequest.setState("Ogun State");
        voterController.registerVoter(registerRequest);
        Voter voter = voterController.findAllVoters().get(0);

        var candidate = new SavedCandidateResponse();
        CandidateService candidateService = new CandidateServiceImpl();

        CandidateRegisterRequest candidateRegisterRequest = new CandidateRegisterRequest();
        candidateRegisterRequest.setPosition(Position.PRESIDENT);
        candidateRegisterRequest.setName("Atiku");
        candidateRegisterRequest.setParty(Party.PDP);
      candidate =   candidateService.register(candidateRegisterRequest);
     //   System.out.println(voterController.castVote(voter, candidate));
        System.out.println(candidate.getVotes());
        assertEquals(true, voter.isHas_voted());

    }


}
