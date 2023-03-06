package app.utils;

import app.data.models.Candidate;
import app.services.CandidateService;
import app.services.CandidateServiceImpl;

import java.util.*;

public class ElectionResults {

    private static CandidateService candidateService = new CandidateServiceImpl();
    private static Comparator<Candidate> desc = Collections.reverseOrder(new CandidateVotesComparator());

    public static List<Candidate> calculateResults(){
        List<Candidate> candidates = candidateService.viewAllCandidates();
       List<Candidate> newCandidates;
       Collections.sort(candidates, desc);
       newCandidates = candidates;
        return newCandidates;
    }

    public static String viewResults(){

        List<Candidate> candidates = calculateResults();

        String result = String.format("""
                ===============================================
                Winner = %s from Party %s with %s Votes. 
                1st Runner-up = %s from Party %s with %s Votes. 
                2nd Runner-up = %s from Party %s with %s Votes.
                ===============================================
                """, candidates.get(0).getName(), candidates.get(0).getParty(), candidates.get(0).getVotes(), candidates.get(1).getName(), candidates.get(1).getParty(), candidates.get(1).getVotes(), candidates.get(2).getName(), candidates.get(2).getParty(), candidates.get(2).getVotes());
        return result;
    }


}
