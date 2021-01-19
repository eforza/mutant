package com.xmen.mutant.service;

import com.xmen.mutant.repository.DnaTestRepository;
import com.xmen.mutant.service.dto.StatsDTO;
import com.xmen.mutant.service.impl.DnaStatsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class DnaStatsServiceTest {

    @Mock
    private DnaTestRepository dnaTestRepository;

    private DnaStatsService statsService;

    @Before
    public void setup() {
        statsService = new DnaStatsServiceImpl(dnaTestRepository);
    }

    @Test
    public void getStats() {
        when(dnaTestRepository.count()).thenReturn(100L);
        when(dnaTestRepository.countByIsMutant(true)).thenReturn(40L);
        StatsDTO stats = statsService.getStats();

        assertEquals(100, stats.getCount_human_dna());
        assertEquals(40, stats.getCount_mutant_dna());
        assertEquals(0.4, stats.getRatio(), 0.0);

        verify(dnaTestRepository, times(1)).count();
        verify(dnaTestRepository, times(1)).countByIsMutant(true);
    }

    @Test
    public void getZeroHumanStatistics() {
        when(dnaTestRepository.count()).thenReturn(1L);
        when(dnaTestRepository.countByIsMutant(true)).thenReturn(1L);
        StatsDTO stats = statsService.getStats();

        assertEquals(1, stats.getCount_human_dna());
        assertEquals(1, stats.getCount_mutant_dna());
        assertEquals(1.0, stats.getRatio(), 0.0);

        verify(dnaTestRepository, times(1)).count();
        verify(dnaTestRepository, times(1)).countByIsMutant(true);

    }

    @Test
    public void getZeroMutantStatistics()  {
        when(dnaTestRepository.count()).thenReturn(1L);
        when(dnaTestRepository.countByIsMutant(true)).thenReturn(0L);
        StatsDTO stats = statsService.getStats();

        assertNotNull(stats);
        assertEquals(1, stats.getCount_human_dna());
        assertEquals(0, stats.getCount_mutant_dna());
        assertEquals(0.0, stats.getRatio(), 0.0);

        verify(dnaTestRepository, times(1)).count();
        verify(dnaTestRepository, times(1)).countByIsMutant(true);

    }

    @Test
    public void getZeroBothStatistics() {

        when(dnaTestRepository.count()).thenReturn(0L);
        when(dnaTestRepository.countByIsMutant(true)).thenReturn(0L);
        StatsDTO stats = statsService.getStats();

        assertNotNull(stats);
        assertEquals(0, stats.getCount_human_dna());
        assertEquals(0, stats.getCount_mutant_dna());
        assertEquals(0.0, stats.getRatio(), 0.0);

        verify(dnaTestRepository, times(1)).count();
        verify(dnaTestRepository, times(1)).countByIsMutant(true);
    }

}
