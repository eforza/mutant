package com.xmen.mutant.web.rest;

import com.xmen.mutant.service.MutantService;
import com.xmen.mutant.service.dto.DnaDTO;
import com.xmen.mutant.service.error.ValidationDNAException;
import com.xmen.mutant.web.rest.error.ApiErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MutantResource {

    private static final Logger log = LoggerFactory.getLogger(MutantResource.class);

    private final MutantService mutantService;

    public MutantResource(MutantService mutantService) {
        this.mutantService = mutantService;
    }

    @PostMapping(value = "/mutant", consumes = "application/json")
    public ResponseEntity<Object> isMutant(@RequestBody @Valid DnaDTO dnaDTO) {

        try {
        if (mutantService.isMutant(dnaDTO.getDna())) {
            log.info("DNA is mutant: {}", dnaDTO.getDna());
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            log.info("DNA is human: {} ", dnaDTO.getDna());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        } catch (ValidationDNAException e) {
            log.error("Input error", e);
            return ResponseEntity.badRequest().body(new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), e));

        }
    }
}
