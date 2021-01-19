package com.xmen.mutant.web.rest;

import com.xmen.mutant.service.DnaStatsService;
import com.xmen.mutant.service.MutantService;
import com.xmen.mutant.service.dto.StatsDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the StatsResource REST service.
 *
 * @see StatsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StatsResourceTest {

    private final List<String> dnaMutant = Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG");

    private final List<String> dnaHuman = Arrays.asList("ATGCGT", "CTGTAC", "TTAAGT", "AGAAGG", "CCTCTA", "TCACTG");


    @Autowired
    private DnaStatsService dnaStatsService;

    @Autowired
    private MutantService mutantService;

    /**
     * Mock the REST controller.
     */
    private MockMvc statsResourceMockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        final StatsResource statsResource = new StatsResource(dnaStatsService);
        this.statsResourceMockMvc = MockMvcBuilders.standaloneSetup(statsResource)
                .build();
    }

    @Test
    public void getOKStatistics() throws Exception {
        mutantService.isMutant(dnaMutant);
        mutantService.isMutant(dnaHuman);

        statsResourceMockMvc.perform(get("/stats")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count_human_dna").value(2))
                .andExpect(jsonPath("$.count_mutant_dna").value(1))
                .andExpect(jsonPath("$.ratio").value(0.5));

    }


}
