package app.RepositoryTests;

import app.data.models.Voter;
import app.data.repositories.VoterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoterRepositoryImplTests {
    private Voter voter;
    private VoterRepository voterRepository;

    @BeforeEach

    public void setUp(){
        voter = new Voter();
        voterRepository = new VoterRepositoryImpl();

    }

    @Test
    public void saveOneVoter_countOfAllVotersIncreasesByOne(){
        voterRepository.save(voter);
        assertEquals(1, voterRepository.countAllVoters());
    }
    @Test
    public void saveOneVoterTwice_votersCountRemainsOne(){
        voterRepository.save(voter);
        voterRepository.save(voter);
        assertEquals(1, voterRepository.countAllVoters());
    }
    @Test
    public void saveTwoVoters_votersCountIsTwo(){
        Voter voter1 = new Voter();
        voterRepository.save(voter);
        voterRepository.save(voter1);
        assertEquals(2, voterRepository.countAllVoters());
        System.out.println(voterRepository.returnAllVoter());
    }

    @Test

    public void findVoterById(){
        voterRepository.save(voter);
        assertEquals(voter, voterRepository.findVoterById(voter.getId()));
    }

    @Test
    public void checkAllVoters(){
        voterRepository.save(voter);
        Voter voter1 = new Voter();
        voterRepository.save(voter1);
        assertEquals(2, voterRepository.countAllVoters());
    }

    public void deleteAVoter_countShouldDecreaseByOne(){
        voterRepository.save(voter);
        Voter voter1 = new Voter();
        voterRepository.save(voter1);


    }
}
