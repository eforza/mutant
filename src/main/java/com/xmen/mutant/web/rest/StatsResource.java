package com.xmen.mutant.web.rest;

import com.xmen.mutant.service.DnaStatsService;
import com.xmen.mutant.service.dto.StatsDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsResource {

    private DnaStatsService dnaStatsService;

    public StatsResource(DnaStatsService dnaStatsService) {
        this.dnaStatsService = dnaStatsService;
    }

    @GetMapping(value = "/stats", produces = "application/json")
    public StatsDTO getStats() {
        return dnaStatsService.getStats();
    }

}
