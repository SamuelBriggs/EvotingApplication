package app.serviceTests;

import app.data.models.Candidate;
import app.data.models.Party;
import app.data.models.Position;
import app.dtos.requests.CandidateRegisterRequest;
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

        CandidateRegisterRequest candidateRegisterRequest = new CandidateRegisterRequest();
        candidateRegisterRequest.setName("Peter Obi");
        candidateRegisterRequest.setPosition(Position.PRESIDENT);
        candidateRegisterRequest.setParty(Party.LABOUR);
        candidateRegisterRequest.setGender("Male");
        candidate = candidateService.register(candidateRegisterRequest);


          assertEquals(1, candidate.getId());

    }
    @Test
    public void testThatCandidateCannotRegisterWithExistingPartyAndPosition() throws IllegalAccessException {
        CandidateRegisterRequest candidateRegisterRequest = new CandidateRegisterRequest();
        candidateRegisterRequest.setName("Peter Obi");
        candidateRegisterRequest.setPosition(Position.PRESIDENT);
        candidateRegisterRequest.setParty(Party.LABOUR);
        candidateRegisterRequest.setGender("Male");
        candidate = candidateService.register(candidateRegisterRequest);

        CandidateRegisterRequest candidateRegisterRequest1 = new CandidateRegisterRequest();
        candidateRegisterRequest1.setName("Peter");
        candidateRegisterRequest1.setPosition(Position.PRESIDENT);
        candidateRegisterRequest1.setParty(Party.LABOUR);
        candidateRegisterRequest1.setGender("Male");

        assertThrows(IllegalAccessException.class, () -> candidateService.register(candidateRegisterRequest1));


    }
    @Test

    public void viewAllCandidates() throws IllegalAccessException {
        CandidateRegisterRequest candidateRegisterRequest = new CandidateRegisterRequest();
        candidateRegisterRequest.setName("Peter Obi");
        candidateRegisterRequest.setPosition(Position.PRESIDENT);
        candidateRegisterRequest.setParty(Party.LABOUR);
        candidateRegisterRequest.setGender("Male");
        candidate = candidateService.register(candidateRegisterRequest);

        CandidateRegisterRequest candidateRegisterRequest1 = new CandidateRegisterRequest();
        candidateRegisterRequest1.setName("Atiku");
        candidateRegisterRequest1.setParty(Party.PDP);
        Candidate atiku = new Candidate();
        atiku = candidateService.register(candidateRegisterRequest1);
        List<Candidate> candidates = candidateService.viewAllCandidates();
        assertEquals(2, candidates.size()
        );
    }

    @Test

    public void testThatCandidatesCanBeViewedByPosition() throws IllegalAccessException {
        Candidate atiku = new Candidate();
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
        Candidate atiku = new Candidate();
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
        Candidate atiku = new Candidate();
        CandidateRegisterRequest candidateRegisterRequest1 = new CandidateRegisterRequest();
        candidateRegisterRequest1.setName("Atiku");
        candidateRegisterRequest1.setParty(Party.PDP);
        candidateRegisterRequest1.setPosition(Position.PRESIDENT);
        atiku = candidateService.register(candidateRegisterRequest1);
        assertEquals(atiku, candidateService.viewAllCandidatesByPositionAndParty(Position.PRESIDENT, Party.PDP));
    }

    @Test

    public void testThatCandidateBioCanBeViewed() throws IllegalAccessException {
        Candidate atiku = new Candidate();
        CandidateRegisterRequest candidateRegisterRequest1 = new CandidateRegisterRequest();
        candidateRegisterRequest1.setName("Atiku");
        candidateRegisterRequest1.setParty(Party.PDP);
        candidateRegisterRequest1.setPosition(Position.PRESIDENT);
        atiku = candidateService.register(candidateRegisterRequest1);

        String bio = atiku.toString();
        assertEquals(bio, candidateService.viewCandidateBio(Position.PRESIDENT, Party.PDP));

    }
}