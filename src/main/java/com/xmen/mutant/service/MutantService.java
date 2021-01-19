package com.xmen.mutant.service;

import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface MutantService {

    boolean isMutant(List<String> dna);

    @Async
    void save(List<String> dna, boolean isMutant);
}
