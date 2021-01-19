package com.xmen.mutant.service.dto;

public class StatsDTO {

    private long count_mutant_dna;
    private long count_human_dna;

    public StatsDTO(long count_mutant_dna, long count_human_dna) {
        this.count_mutant_dna = count_mutant_dna;
        this.count_human_dna = count_human_dna;
    }

    public long getCount_mutant_dna() {
        return count_mutant_dna;
    }

    public long getCount_human_dna() {
        return count_human_dna;
    }

    public double getRatio() {
        if (count_human_dna == 0 && count_mutant_dna == 0) return 0d;
        if (count_human_dna == 0) return 1d;
        return ((double) count_mutant_dna / count_human_dna);
    }

    @Override
    public String toString() {
        return "StatsDTO{" +
                "count_mutant_dna=" + count_mutant_dna +
                ", count_human_dna=" + count_human_dna +
                '}';
    }
}
