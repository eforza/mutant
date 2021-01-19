package com.xmen.mutant.service.impl;

import com.xmen.mutant.repository.DnaTestRepository;
import com.xmen.mutant.service.DnaStatsService;
import com.xmen.mutant.service.dto.StatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DnaStatsServiceImpl implements DnaStatsService {

    private final DnaTestRepository dnaResultRepository;

    @Autowired
    public DnaStatsServiceImpl(DnaTestRepository dnaResultRepository) {
        this.dnaResultRepository = dnaResultRepository;
    }

    @Override
    public StatsDTO getStats() {

        // un humano es mutante
        long countHumans = dnaResultRepository.count();
        long countMutants = dnaResultRepository.countByIsMutant(true);

        return new StatsDTO(countMutants, countHumans);
    }
}
