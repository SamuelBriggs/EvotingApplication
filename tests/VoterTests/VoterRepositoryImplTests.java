package VoterTests;

import data.models.Voter;
import data.repositories.VoterRepository;
import data.repositories.VoterRepositoryImpl;
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
        voter.setName("Samuel");
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
    @Test
    public void deleteAVoter_countShouldDecreaseByOne(){
        voterRepository.save(voter);
        Voter voter1 = new Voter();
        voterRepository.save(voter1);
        voterRepository.delete(voter1.getId());
        assertEquals(1, voterRepository.countAllVoters());

    }
}
