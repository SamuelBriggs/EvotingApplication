package app.controller;

import app.data.models.Party;
import app.data.models.Position;
import app.data.models.Voter;
import app.dtos.requests.RegisterRequest;
import app.services.VoterService;
import app.services.VoterServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class VoterController {
    private VoterService voterService = new VoterServiceImpl();

    @PostMapping("register")
    public Object registerVoter(@RequestBody RegisterRequest registerRequest){
        return voterService.register(registerRequest);
    }
    @GetMapping("id")
    public Object findVoter(int id){
        return voterService.findVoter(id);
    }


    public Object viewCandidate(Position position, Party party){
        return voterService.viewCandidate(position, party);
    }



    public List<Voter> findAllVoters() {
        return voterService.findAllVoters();
    }


    public String viewResults(){
        return voterService.viewResults();
    }
}
