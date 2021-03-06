package br.com.ml.testml.repository;

import br.com.ml.testml.TestMlApplicationTests;
import br.com.ml.testml.domain.Mutant;
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
        this.mutantRepository.save(new Mutant(String.join(", ", dna), true));
    }

    @Test
    public void findAll() {
        List<Mutant> stats = mutantRepository.findAll();
        assertFalse(stats.isEmpty());
    }

}
