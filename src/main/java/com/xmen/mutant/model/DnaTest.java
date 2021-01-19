package com.xmen.mutant.model;

import javax.persistence.*;

@Entity
@Table(name = "dna_tests")
public class DnaTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob
    @Column(name = "dna")
    private String dna;

    private Boolean isMutant;

    public DnaTest() {
    }

    public DnaTest(String dna, Boolean isMutant) {
        this.dna = dna;
        this.isMutant = isMutant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public Boolean isMutant() {
        return isMutant;
    }

    public void setIsMutant(Boolean mutant) {
        isMutant = mutant;
    }

    @Override
    public String toString() {
        return "DnaResult{" +
                "id=" + id +
                ", dna='" + dna + '\'' +
                ", isMutant=" + isMutant +
                '}';
    }
}

