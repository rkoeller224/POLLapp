package com.apress.dto;

import com.apress.domain.Option;

import java.util.Collection;

public class VoteResult {
    private int totalVotes;
    private Collection<OptionCount> Results;

    public int getTotalVotes() {
        return totalVotes;
    }

    public Collection<OptionCount> getResults() {
        return Results;
    }

    public void setResults(Collection<OptionCount> results) {
        Results = results;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }
}
