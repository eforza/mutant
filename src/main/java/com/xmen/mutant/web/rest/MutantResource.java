package com.xmen.mutant.web.rest;

import com.xmen.mutant.service.MutantService;
import com.xmen.mutant.service.dto.DnaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutantResource {

    private static final Logger log = LoggerFactory.getLogger(MutantResource.class);

    private MutantService mutantService;

    public MutantResource(MutantService mutantService) {
        this.mutantService = mutantService;
    }

    @PostMapping(value = "/mutant", consumes = "application/json")
    public ResponseEntity<String> isMutant(@RequestBody DnaDTO dnaDTO) {

        if (mutantService.isMutant(dnaDTO.getDna())) {
            log.info("DNA: {} is mutant", dnaDTO.getDna());
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            log.info("DNA: {} is NOT mutant", dnaDTO.getDna());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }
}
