package com.xmen.mutant.service.dto;

import java.util.List;
import java.util.Objects;

public class DnaDTO {

    private List<String> dna;

    public DnaDTO(List<String> dna) {
        this.dna = dna;
    }

    public List<String> getDna() {
        return dna;
    }

    public DnaDTO() {
    }

    public void setDna(List<String> dna) {
        this.dna = dna;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DnaDTO)) return false;
        DnaDTO dnaDTO = (DnaDTO) o;
        return Objects.equals(dna, dnaDTO.dna);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dna);
    }

    @Override
    public String toString() {
        return "DnaDTO{" +
                "dna=" + dna +
                '}';
    }
}
