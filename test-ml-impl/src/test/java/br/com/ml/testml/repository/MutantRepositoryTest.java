package br.com.ml.testml.repository;

import br.com.ml.testml.TestMlApplicationTests;
import br.com.ml.testml.entity.MutantEntity;
import br.com.ml.testml.pojo.Stats;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class MutantRepositoryTest extends TestMlApplicationTests {

    @Autowired
    private MutantRepository mutantRepository;

    @Before
    public void initialize() {
        String[] dna = new String[]{"TTTCGA", "CAGTGC", "TTATGT", "AGAATG", "CCGCTA", null};
        MutantEntity mutantEntity = this.mutantRepository.save(new MutantEntity(String.join(", ", dna), true));
    }

    @Test
    public void getStats() {
        List<Stats> stats = mutantRepository.getStats();
        assertFalse(stats.isEmpty());
    }

}
