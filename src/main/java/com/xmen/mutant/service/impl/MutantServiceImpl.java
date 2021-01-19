package com.xmen.mutant.service.impl;

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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MutantServiceImpl implements MutantService {

    private static final Logger log = LoggerFactory.getLogger(MutantServiceImpl.class);

    private static final Set<Character> validChars = Set.of('A', 'T', 'C', 'G');

    @Autowired
    private DnaTestRepository dnaResultRepository;

    public MutantServiceImpl(DnaTestRepository dnaResultRepository) {
        this.dnaResultRepository = dnaResultRepository;
    }

    @Override
    public boolean isMutant(List<String> dna) {
        final int maxY = dna.size();
        if (maxY == 0) {
            throw new ValidationDNAException("Empty matrix");
        }
        final int maxX = dna.get(0).toCharArray().length;

        final boolean isMutant = checkMutant(dna, maxY, maxX);

        this.save(dna, isMutant);
        return isMutant;
    }

    private boolean checkMutant(List<String> dna, int maxY, int maxX) {
        int[][] directions = {{1, 0}, {1, -1}, {1, 1}, {0, 1}};
        for (int[] d : directions) {
            int dx = d[0];
            int dy = d[1];
            for (int y = 0; y < maxY; y++) {
                if (dna.get(y).toCharArray().length != maxY) {
                     throw new ValidationDNAException(String.format("Not square matrix. x=%s, y=%s", dna.get(y).toCharArray().length, maxY));
                }
                for (int x = 0; x < maxX; x++) {
                    int lastX = x + 3 * dx;
                    int lastY = y + 3 * dy;
                    if (0 <= lastX && lastX < maxX && 0 <= lastY && lastY < maxY) {
                        char n = dna.get(y).charAt(x);
                        if (validChars.contains(n)) {
                            if (n == dna.get(y + dy).charAt(x + dx) &&
                                    n == dna.get(y + 2 * dy).charAt(x + 2 * dx) &&
                                    n == dna.get(lastY).charAt(lastX)) {
                                return true;
                            }
                        } else {
                            throw new ValidationDNAException(String.format("Matrix contain an invalid char %s", n));
                        }
                    }
                }
            }
        }
        return false;
    }

    @Async
    @Override
    public void save(List<String> dna, boolean isMutant) {

        //TODO: analize use hash fuction.
        final String dnaAsString = String.join("", dna);
        DnaTest dnaResult = dnaResultRepository.findByDna(dnaAsString);

        if (dnaResult != null) {
            log.info("Dna {} already exist in the db", dna);
        } else {
            try {
                dnaResultRepository.save(new DnaTest(dnaAsString, isMutant));
                log.info("Saved Dna: {} using async thread: {}", dna, Thread.currentThread());

            } catch (DataAccessException e) {
                log.error("Error occurred while executing async saving in thread {}", Thread.currentThread());
            }
        }

    }


}

