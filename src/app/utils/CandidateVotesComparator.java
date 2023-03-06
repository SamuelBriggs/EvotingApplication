package app.utils;

import app.data.models.Candidate;

import java.util.Comparator;

public class CandidateVotesComparator implements Comparator<Candidate> {
    @Override
    public int compare(Candidate can1, Candidate can2) {
        return can1.getVotes() - can2.getVotes();
    }
}
