package app.serviceTests;

import app.data.models.Candidate;
import app.dtos.requests.CandidateRegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.services.CandidateService;
import app.services.CandidateServiceImpl;
import app.utils.ElectionResults;

public class ElectionResultsTest {

    private ElectionResults electionResults;

    @BeforeEach

    public void setUp(){
        electionResults = new ElectionResults();
    }

    @Test

    public void check() throws IllegalAccessException {
        Candidate candidate = new Candidate();
        CandidateService candidateService = new CandidateServiceImpl();
        CandidateRegisterRequest candidateRegisterRequest = new CandidateRegisterRequest();
        CandidateRegisterRequest candidateRegisterRequest1 = new CandidateRegisterRequest();

        candidateRegisterRequest1.setName("Obi");
        candidateRegisterRequest1.setVotes(2);
        candidateService.register(candidateRegisterRequest1);
        candidateRegisterRequest.setName("Atiku");
        candidateRegisterRequest.setVotes(4);
        candidateRegisterRequest.setGender("Male");
        candidateService.register(candidateRegisterRequest);

        CandidateRegisterRequest candidateRegisterRequest2 = new CandidateRegisterRequest();

        candidateRegisterRequest2.setVotes(1);
        candidateRegisterRequest2.setName("Tinubu");
        candidateService.register(candidateRegisterRequest2);

        System.out.println(ElectionResults.viewResults());

    }
}
