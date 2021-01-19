package com.xmen.mutant.repository;

import com.xmen.mutant.model.DnaTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DnaTestRepository extends JpaRepository<DnaTest, Long> {

    DnaTest findByDna(String dna);

    Long countByIsMutant(boolean isMutant);
}
