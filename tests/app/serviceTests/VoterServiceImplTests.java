package app.serviceTests;

import app.data.models.Candidate;
import app.data.models.Party;
import app.data.models.Position;
import app.data.repositories.CandidateRepository;
import app.dtos.SavedVoterResponse;
import app.dtos.requests.CandidateRegisterRequest;
import app.dtos.requests.RegisterRequest;
import app.services.CandidateService;
import app.services.CandidateServiceImpl;
import app.services.VoterService;
import app.services.VoterServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VoterServiceImplTests {
    private RegisterRequest registerRequest;
    private VoterService voterService;

    private CandidateService candidateService;

    private CandidateRepository candidateRepository;

    private CandidateRepository repository;




     @Test

    public void registerTest_VoterIsNotZero() {
        registerRequest = new RegisterRequest();

         voterService = new VoterServiceImpl();
        SavedVoterResponse savedVoter = voterService.register(registerRequest);
         System.out.println(savedVoter.getFirstName());
         System.out.println(savedVoter.getId());
         assertTrue(savedVoter.getId()!=0);

     }
     @Test
     public void findAlVotersTest(){
         voterService = new VoterServiceImpl();
         registerRequest = new RegisterRequest();
        SavedVoterResponse savedVoter = voterService.register(registerRequest);
         assertNotNull(voterService.findAllVoters());
     }
     @Test
    public void testCastVote() throws IllegalAccessException {

         candidateService = new CandidateServiceImpl();

         CandidateRegisterRequest candidateRegisterRequest = new CandidateRegisterRequest();

         candidateRegisterRequest.setName("Obi");
         candidateRegisterRequest.setParty(Party.LABOUR);
         candidateRegisterRequest.setPosition(Position.PRESIDENT);

        Candidate candidate1 =  candidateService.register(candidateRegisterRequest);

         RegisterRequest registerRequest1 = new RegisterRequest();
         registerRequest1.setFirstName("Samuel");
         registerRequest1.setLastName("Tolu");
         registerRequest1.setGender("Male");
         VoterService voterService1 = new VoterServiceImpl();
         voterService1.register(registerRequest1);
         var vote = voterService1.findVoter(1);
         assertEquals(candidate1, voterService1.castVote(1, Position.PRESIDENT, Party.LABOUR));
         assertEquals(1, candidate1.getVotes());

     }

     @Test

     public void testThatVotingWithInvalidIdThrowsException() throws IllegalAccessException {

         candidateService = new CandidateServiceImpl();

         CandidateRegisterRequest candidateRegisterRequest = new CandidateRegisterRequest();

         candidateRegisterRequest.setName("Obi");
         candidateRegisterRequest.setParty(Party.LABOUR);
         candidateRegisterRequest.setPosition(Position.PRESIDENT);

         Candidate candidate1 =  candidateService.register(candidateRegisterRequest);

         RegisterRequest registerRequest1 = new RegisterRequest();
         registerRequest1.setFirstName("Samuel");
         registerRequest1.setLastName("Tolu");
         registerRequest1.setGender("Male");
         VoterService voterService1 = new VoterServiceImpl();

         voterService1.register(registerRequest1);

       assertThrows(IllegalAccessException.class, () -> voterService1.castVote(2, Position.PRESIDENT, Party.LABOUR));



     }

     @Test
    public void testThatVoterCannotVoteAfterVotingOnce() throws IllegalAccessException {

         voterService = new VoterServiceImpl();
         registerRequest = new RegisterRequest();
         SavedVoterResponse savedVoter =  voterService.register(registerRequest);
         Candidate candidate = new Candidate();
         candidate.setParty(Party.LABOUR);
         repository.save(candidate);
       //voterService.castVote();
        // assertThrows(IllegalAccessException.class, () -> voterService.castVote(voterService.findAllVoters().get(0), candidate));
     }

@Test
    public void testThatVoterCanViewCandidate() throws IllegalAccessException {

         voterService = new VoterServiceImpl();
         candidateService = new CandidateServiceImpl();
         Candidate atiku = new Candidate();
         CandidateRegisterRequest candidateRegisterRequest1 = new CandidateRegisterRequest();
         candidateRegisterRequest1.setName("Atiku");
         candidateRegisterRequest1.setParty(Party.PDP);
         candidateRegisterRequest1.setPosition(Position.PRESIDENT);
         atiku = candidateService.register(candidateRegisterRequest1);
         System.out.println(atiku.getName());

         System.out.println(voterService.viewCandidate(Position.PRESIDENT, Party.PDP));
         System.out.println(candidateService.viewAllCandidatesByPositionAndParty(Position.PRESIDENT, Party.PDP));


     }

     @Test

    public void testThatVoterCanViewResults() throws IllegalAccessException {
         CandidateRegisterRequest candidateRegisterRequest = new CandidateRegisterRequest();
         candidateRegisterRequest.setPosition(Position.PRESIDENT);
         candidateRegisterRequest.setParty(Party.PDP);
         candidateRegisterRequest.setName("Atiku");
         candidateService = new CandidateServiceImpl();
         Candidate candidate = candidateService.register(candidateRegisterRequest);


         CandidateRegisterRequest candidateRegisterRequest1 = new CandidateRegisterRequest();
         candidateRegisterRequest1.setPosition(Position.PRESIDENT);
         candidateRegisterRequest1.setParty(Party.APC);
         candidateRegisterRequest1.setName("Obi");

         Candidate candidate1 = candidateService.register(candidateRegisterRequest1);

         CandidateRegisterRequest candidateRegisterRequest2 = new CandidateRegisterRequest();
         candidateRegisterRequest2.setPosition(Position.PRESIDENT);
         candidateRegisterRequest2.setParty(Party.LABOUR);
         candidateRegisterRequest2.setName("Tinubu");

         Candidate candidate2 = candidateService.register(candidateRegisterRequest2);


         registerRequest = new RegisterRequest();
         registerRequest.setFirstName("Samuel");
         registerRequest.setLastName("Tolu");
         voterService = new VoterServiceImpl();
         SavedVoterResponse voter =    voterService.register(registerRequest);

         voterService.castVote(voter.getId(), Position.PRESIDENT, Party.PDP);

         System.out.println(voterService.viewResults());


     }

}
