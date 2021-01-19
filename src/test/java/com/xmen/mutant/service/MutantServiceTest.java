package com.xmen.mutant.service;

import com.xmen.mutant.repository.DnaTestRepository;
import com.xmen.mutant.service.impl.DnaStatsServiceImpl;
import com.xmen.mutant.service.impl.MutantServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * TODO: write a clear description of what this class is all about.
 *
 * @author esteban
 */
@RunWith(MockitoJUnitRunner.class)
public class MutantServiceTest {

    private final List<String> testDna = Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG");
    private final List<String> dnaHuman = Arrays.asList("ATGTCT", "TAGTGC", "AGACGG", "TTATGT", "GAGTCA", "TCACTG");


    @Mock
    private DnaTestRepository dnaResultRepository;

    private MutantService mutantService;

    @Before
    public void setUp() {
        mutantService = new MutantServiceImpl(dnaResultRepository);

    }

    @Test
    public void isMutant() {
        assertTrue(mutantService.isMutant(testDna));
    }

    @Test
    public void isHuman() {
        assertFalse(mutantService.isMutant(dnaHuman));
    }

}
