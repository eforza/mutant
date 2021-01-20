package com.xmen.mutant.service.impl;

import com.xmen.mutant.matrix.MatrixOperations;
import com.xmen.mutant.model.DnaTest;
import com.xmen.mutant.repository.DnaTestRepository;
import com.xmen.mutant.service.MutantService;
import com.xmen.mutant.service.error.ValidationDNAException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class MutantServiceImpl implements MutantService {

    private static final Logger log = LoggerFactory.getLogger(MutantServiceImpl.class);

    private static final String DNA_WORD_REGEX = "^([ATGC]*)$";
    private static final int MIN_SIZE = 4;
    public static final String DNA_MUTANT = ".*(AAAA|CCCC|GGGG|TTTT).*";


    @Autowired
    private DnaTestRepository dnaResultRepository;

    public MutantServiceImpl(DnaTestRepository dnaResultRepository) {
        this.dnaResultRepository = dnaResultRepository;
    }

    @Override
    public boolean isMutant(List<String> dna) {


        validateDna(dna);
        final boolean isMutant = checkMutant(dna);

        this.save(dna, isMutant);
        return isMutant;
    }

    private boolean checkMutant(List<String> dna) {

        Pattern pattern = Pattern.compile(DNA_MUTANT);

        //horizontal
        int mutantCount = dna.stream()
                .filter(pattern.asPredicate())
                .collect(Collectors.toList()).size();
        if (mutantCount > 1) {
            return true;
        }

        //vertical
        mutantCount += MatrixOperations.transpose(dna).stream()
                .filter(pattern.asPredicate())
                .collect(Collectors.toList()).size();
        if (mutantCount > 1) {
            return true;
        }

        //diagonal left to bottom
        mutantCount += MatrixOperations.diagonalsLeftToBottom(dna).stream()
                .filter(pattern.asPredicate())
                .collect(Collectors.toList()).size();
        if (mutantCount > 1) {
            return true;
        }
        //diagional from left top
        mutantCount += MatrixOperations.diagonalsLeftToTop(dna).stream()
                .filter(pattern.asPredicate())
                .collect(Collectors.toList()).size();
        if (mutantCount > 1) {
            return true;
        }
        return false;

    }



    private static void validateDna(List<String> dna) {
        if (!isSquareMatrix(dna)) {
            throw new ValidationDNAException("Not square matrix");
        }
        if (dna.size() < MIN_SIZE ) {
            throw new ValidationDNAException("DNA sequence to small, must be at least: "
                    + (MIN_SIZE ) + "x" + (MIN_SIZE ));
        }
        if (dna.parallelStream().anyMatch(dnaRow -> !dnaRow.matches(DNA_WORD_REGEX))) {
            throw new ValidationDNAException("Matrix contain an invalid character must be contains only A, T, C or G.");
        }
    }

    private static boolean isSquareMatrix(List<String> dna) {
        final int rowAmount = dna.size();
        return dna.stream().noneMatch(row -> row.length() != rowAmount);
    }


    @Async
    @Override
    public void save(List<String> dna, boolean isMutant) {

        //TODO: analize use hash fuction.
        final String dnaAsString = String.join("", dna);
        DnaTest dnaResult = dnaResultRepository.findByDna(dnaAsString);

        if (dnaResult == null) {
            try {
                dnaResultRepository.save(new DnaTest(dnaAsString, isMutant));
                log.info("Saved Dna: {} using async thread: {}", dna, Thread.currentThread());

            } catch (DataAccessException e) {
                log.error("Error occurred while executing async saving in thread {}", Thread.currentThread());
            }
        } else {
            log.info("Dna {} exist in the db", dna);
        }

    }


}

