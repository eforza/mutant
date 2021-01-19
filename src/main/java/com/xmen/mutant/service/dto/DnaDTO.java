package com.xmen.mutant.service.dto;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class DnaDTO {

    @NotEmpty
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

}
