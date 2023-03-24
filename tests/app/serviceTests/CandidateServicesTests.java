package app.serviceTests;

import app.data.models.Candidate;
import app.data.models.Party;
import app.data.models.Position;
import app.dtos.requests.CandidateRegisterRequest;
import app.dtos.responses.SavedCandidateResponse;
import app.services.CandidateService;
import app.services.CandidateServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CandidateServicesTests {
    private CandidateService candidateService = new CandidateServiceImpl();
    private Candidate candidate;



    @Test
    public void candidateCanRegister() throws IllegalAccessException {

        SavedCandidateResponse savedCandidateResponse = new SavedCandidateResponse();

        CandidateRegisterRequest candidateRegisterRequest = new CandidateRegisterRequest();
        candidateRegisterRequest.setName("Peter Obi");
        candidateRegisterRequest.setPosition(Position.PRESIDENT);
        candidateRegisterRequest.setParty(Party.LABOUR);
        candidateRegisterRequest.setGender("Male");
        savedCandidateResponse = candidateService.register(candidateRegisterRequest);


          assertEquals(1, savedCandidateResponse.getId());

    }
    @Test
    public void testThatCandidateCannotRegisterWithExistingPartyAndPosition() throws IllegalAccessException {
        CandidateRegisterRequest candidateRegisterRequest = new CandidateRegisterRequest();
        candidateRegisterRequest.setName("Peter Obi");
        candidateRegisterRequest.setPosition(Position.PRESIDENT);
        candidateRegisterRequest.setParty(Party.LABOUR);
        candidateRegisterRequest.setGender("Male");
        candidateService.register(candidateRegisterRequest);

        CandidateRegisterRequest candidateRegisterRequest1 = new CandidateRegisterRequest();
        candidateRegisterRequest1.setName("Peter");
        candidateRegisterRequest1.setPosition(Position.PRESIDENT);
        candidateRegisterRequest1.setParty(Party.LABOUR);
        candidateRegisterRequest1.setGender("Male");

        assertThrows(IllegalAccessException.class, () -> candidateService.register(candidateRegisterRequest1));


    }
    @Test

    public void viewAllCandidates() throws IllegalAccessException {

        SavedCandidateResponse candidateResponse = new SavedCandidateResponse();
        CandidateRegisterRequest candidateRegisterRequest = new CandidateRegisterRequest();
        candidateRegisterRequest.setName("Peter Obi");
        candidateRegisterRequest.setPosition(Position.PRESIDENT);
        candidateRegisterRequest.setParty(Party.LABOUR);
        candidateRegisterRequest.setGender("Male");
        candidateResponse = candidateService.register(candidateRegisterRequest);

        CandidateRegisterRequest candidateRegisterRequest1 = new CandidateRegisterRequest();
        candidateRegisterRequest1.setName("Atiku");
        candidateRegisterRequest1.setParty(Party.PDP);
        candidateRegisterRequest1.setPosition(Position.PRESIDENT);
        Candidate atiku = new Candidate();
        SavedCandidateResponse candidateResponse1 = new SavedCandidateResponse();
        candidateResponse1 = candidateService.register(candidateRegisterRequest1);
        List<Candidate> candidates = candidateService.viewAllCandidates();
        assertEquals(2, candidates.size()
        );
    }

    @Test

    public void testThatCandidatesCanBeViewedByPosition() throws IllegalAccessException {
        SavedCandidateResponse atiku = new SavedCandidateResponse();
        CandidateRegisterRequest candidateRegisterRequest1 = new CandidateRegisterRequest();
        candidateRegisterRequest1.setName("Atiku");
        candidateRegisterRequest1.setParty(Party.PDP);
        candidateRegisterRequest1.setPosition(Position.PRESIDENT);

        atiku = candidateService.register(candidateRegisterRequest1);

        List<Candidate> candidates = candidateService.viewAllCandidatesByPosition(Position.PRESIDENT);
        assertEquals(candidates.get(0).getPosition(), atiku.getPosition());
    }

    @Test
    public void testThatCandidatesCanBeViewedByParty() throws IllegalAccessException {
        var atiku = new SavedCandidateResponse();
        CandidateRegisterRequest candidateRegisterRequest1 = new CandidateRegisterRequest();
        candidateRegisterRequest1.setName("Atiku");
        candidateRegisterRequest1.setParty(Party.PDP);
        candidateRegisterRequest1.setPosition(Position.PRESIDENT);

        atiku = candidateService.register(candidateRegisterRequest1);

        List<Candidate> candidates = candidateService.viewAllCandidatesByParty(Party.PDP);
        assertEquals(candidates.get(0).getPosition(), atiku.getPosition());
    }

    @Test

    public void testThatCandidateCanBeViewedByPartyAndPosition() throws IllegalAccessException {
       var atiku = new SavedCandidateResponse();
        CandidateRegisterRequest candidateRegisterRequest1 = new CandidateRegisterRequest();
        candidateRegisterRequest1.setName("Atiku");
        candidateRegisterRequest1.setParty(Party.PDP);
        candidateRegisterRequest1.setPosition(Position.PRESIDENT);
        atiku = candidateService.register(candidateRegisterRequest1);
        assertEquals(atiku, candidateService.viewAllCandidatesByPositionAndParty(Position.PRESIDENT, Party.PDP));
    }

    @Test

    public void testThatCandidateBioCanBeViewed() throws IllegalAccessException {
        var atiku = new SavedCandidateResponse();
        CandidateRegisterRequest candidateRegisterRequest1 = new CandidateRegisterRequest();
        candidateRegisterRequest1.setName("Atiku");
        candidateRegisterRequest1.setParty(Party.PDP);
        candidateRegisterRequest1.setPosition(Position.PRESIDENT);
        atiku = candidateService.register(candidateRegisterRequest1);

        String bio = candidateService.viewCandidateBio(Position.PRESIDENT, Party.PDP);
        assertEquals(bio, candidateService.viewCandidateBio(Position.PRESIDENT, Party.PDP));

    }
}