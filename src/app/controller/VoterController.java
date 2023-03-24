package app.controller;

import app.data.models.Party;
import app.data.models.Position;
import app.data.models.Voter;
import app.dtos.requests.RegisterRequest;
import app.services.VoterService;
import app.utils.ElectionResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class VoterController {
    @Autowired
    private VoterService voterService;

    @Autowired

    private ElectionResults results;

    @PostMapping("voter/register")
    public Object registerVoter(@RequestBody RegisterRequest registerRequest){
        return voterService.register(registerRequest);
    }
    @GetMapping("voter/{id}")
    public Object findVoter(@PathVariable String id){
        return voterService.findVoter(id);
    }

    @GetMapping("voter/viewCandidate")
    public ResponseEntity<?> viewCandidate(@RequestParam Position position, @RequestParam Party party){
        try {
            return new ResponseEntity<>(voterService.viewCandidate(position, party), HttpStatus.CREATED);
        } catch (NullPointerException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("voter/castVote")
    public ResponseEntity<?> castVote(@RequestParam String voterId, @RequestParam Position position, @RequestParam Party party){
        try {
            return new ResponseEntity<>(voterService.castVote(voterId, position, party), HttpStatus.CREATED);
        }catch (IllegalAccessException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    public List<Voter> findAllVoters() {
        return voterService.findAllVoters();
    }
    @GetMapping("voter/viewResult")
    public String viewResults() throws IllegalAccessException {
        return results.viewResults();
    }
}
