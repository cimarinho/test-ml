package br.com.ml.testml.facade;

import br.com.ml.testml.TestMlApplicationTests;
import br.com.ml.testml.domain.Mutant;
import br.com.ml.testml.exception.MutantException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class MutantFacadeTest extends TestMlApplicationTests {

    @Autowired
    MutantFacade mutantFacade;

    @Test
    public void is_mutant() throws MutantException {
        String [] dna =  new String[]{"AAGCGA", "AAATAC", "ATAAAT", "AGAAAC", "CCCCCC", "TCACTG"};
        assertTrue(this.mutantFacade.isMutant(dna));
    }

    @Test
    public void not_is_mutant() throws MutantException {
        String[] dna = new String[]{"TTTCGA", "CAGTGC", "TTATGT", "AGAATG", "CCGCTA", "TCACTG"};
        assertFalse(this.mutantFacade.isMutant(dna));
    }

    @Test(expected = MutantException.class)
    public void error() throws MutantException {
        String[] dna = new String[10];
        assertFalse(this.mutantFacade.isMutant(dna));
    }

    @Test(expected = MutantException.class)
    public void error_item() throws MutantException {
        String[] dna = new String[]{"TTTCGA", "CAGTGC", "TTATGT", "AGAATG", "CCGCTA", null};
        assertFalse(this.mutantFacade.isMutant(dna));
    }

    @Test
    public void saveMutant() {
        String[] dna = new String[]{"TTTCGA", "CAGTGC", "TTATGT", "AGAATG", "CCGCTA", null};
        Mutant mutantEntity = this.mutantFacade.saveMutant(new Mutant(String.join(", ", dna), true));
        assertNotNull(mutantEntity.getId());
    }
}
