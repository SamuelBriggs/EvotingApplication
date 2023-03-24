package app.controller;

import app.data.models.Party;
import app.data.models.Position;
import app.dtos.requests.CandidateRegisterRequest;
import app.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@Service
public class CandidateController {
    @Autowired

    private CandidateService candidateService;
    @PostMapping("candidate/register")
    public ResponseEntity<?> register(@RequestBody CandidateRegisterRequest candidateRegisterRequest){
        try
        {
            return new ResponseEntity<>(candidateService.register(candidateRegisterRequest), HttpStatus.CREATED);
        }catch (IllegalAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
@GetMapping("candidate/viewAll")
    public ResponseEntity<?> viewAllCandidates(){
        return new ResponseEntity<>(candidateService.viewAllCandidates(), HttpStatus.CREATED);
    }


    @GetMapping("candidate/viewByPos")
    public ResponseEntity<?> viewAllCandidatesByPosition(@RequestParam("position") Position position){

        try {
            return new ResponseEntity<>(candidateService.viewAllCandidatesByPosition(position), HttpStatus.CREATED);
        }catch (IllegalAccessException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("candidate/viewByParty")
    public ResponseEntity<?> viewAllCandidatesByParty(@RequestParam("party")Party party){
        try {
            return new ResponseEntity<>(candidateService.viewAllCandidatesByParty(party), HttpStatus.CREATED);
        }catch (IllegalAccessException ex){

            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("candidate/viewByPositionAndParty")
    public ResponseEntity<?> viewAllCandidatesByPositionAndParty(@RequestParam Position position, @RequestParam Party party){
        try {
            return new ResponseEntity<>(candidateService.viewAllCandidatesByPositionAndParty(position, party), HttpStatus.CREATED);
        } catch (NullPointerException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }




}
