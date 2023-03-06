package app.RepositoryTests;

import app.data.models.Candidate;
import app.data.models.Party;
import app.data.models.Position;
import app.data.repositories.CandidateRepository;
import app.data.repositories.CandidateRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CandidateRepositoryTests {

    private Candidate candidate;

    private Candidate candidate2;
    private CandidateRepository candidateRepository;

    @BeforeEach

    public void setUp(){
        candidate = new Candidate();
        candidateRepository = new CandidateRepositoryImpl();
        candidate2 = new Candidate();
        candidate.setName("Peter Obi");
        candidate.setParty(Party.LABOUR);
        candidate.setPosition(Position.PRESIDENT);
        candidate2.setPosition(Position.PRESIDENT);

    }
    @Test
    public void saveOneCandidate_countOfAllCandidates_IsOne(){
        candidateRepository.save(candidate);
        assertEquals(1, candidateRepository.countAllCandidates());

    }
    @Test
    public void saveTheSameCandidate_countRemainsOne(){
        candidateRepository.save(candidate);
        candidateRepository.save(candidate);
        assertEquals(1, candidateRepository.countAllCandidates());
    }

    @Test

    public void saveTwoCandidates_countOfCandidatesBecomeTwo(){
        candidateRepository.save(candidate);
        Candidate candidate1 = new Candidate();
        candidateRepository.save(candidate1);
        assertEquals(2, candidateRepository.countAllCandidates());
    }
    @Test
    public void findCandidateById(){
        Candidate can = candidateRepository.save(candidate);
        assertEquals(can, candidateRepository.findCandidate(1) );
    }

    @Test
    public void findCandidateByPartyAndPosition(){
        candidateRepository.save(candidate);
        Candidate candidate1 = candidateRepository.findCandidateByPositionAndParty(Position.PRESIDENT, Party.LABOUR);
        assertEquals(candidate1, candidate);
    }
    @Test

    public void findCandidateByParty(){
        candidateRepository.save(candidate);
        System.out.println(candidateRepository.findAllCandidatesByPosition(Position.PRESIDENT));

    }

    @Test

    public void returnAllCandidatesContestingInAPosition(){
        candidateRepository.save(candidate);
        candidateRepository.save(candidate2);
        System.out.println(candidateRepository.findAllCandidatesByPosition(Position.PRESIDENT));

    }
    @Test

    public void returnAllCandidates(){
        candidateRepository.save(candidate);
        candidateRepository.save(candidate2);

        System.out.println(candidateRepository.returnAllCandidates());

    }


}
