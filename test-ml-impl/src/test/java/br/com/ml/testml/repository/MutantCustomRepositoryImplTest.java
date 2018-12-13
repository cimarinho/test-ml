package br.com.ml.testml.repository;

import br.com.ml.testml.TestMlApplicationTests;
import br.com.ml.testml.domain.Mutant;
import br.com.ml.testml.domain.Stats;
import br.com.ml.testml.repository.impl.MutantCustomRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class MutantCustomRepositoryImplTest  extends TestMlApplicationTests {

    @Autowired
    private MutantRepository mutantRepository;

    @Autowired
    private MutantCustomRepositoryImpl mutantCustomRepository;

    @Before
    public void initialize() {
        String[] dna = new String[]{"TTTCGA", "CAGTGC", "TTATGT", "AGAATG", "CCGCTA", null};
        this.mutantRepository.save(new Mutant(String.join(", ", dna), true));
    }

    @Test
    public void stats(){
        List<Stats> stats = mutantCustomRepository.getStats();
        assertFalse(stats.isEmpty());
    }
}
