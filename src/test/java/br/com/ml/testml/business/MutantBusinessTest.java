package br.com.ml.testml.business;

import br.com.ml.testml.exception.MutantException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MutantBusinessTest {

    MutantBusiness mutant;

    @Before
    public void setup() throws MutantException {
        String [] dna =  new String[]{"AAGCGATTGC", "AAATACATCC", "ATAAATACCC", "AGAAACATGT", "CCCCCCCCCC", "TCACTGGTGG"};
        this.mutant = MutantBusiness.getMutant(dna);
    }

    @Test(expected = MutantException.class )
    public void mutant_not_valid() throws MutantException {
        String [] dna =  new String[]{"MMMMM"};
        MutantBusiness.getMutant(dna);
    }
    @Test
    public void isMutant() {
        mutant.lines(4,1,10);
        assertEquals(mutant.getCountDna(), 1);
    }

    @Test
    public void not_isMutant() throws MutantException {
        String [] dna =  new String[]{"ATTCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCGCTA", "TCACTG"};
        MutantBusiness mutant = MutantBusiness.getMutant(dna);
        assertEquals(mutant.getCountDna(), 0);
    }

    @Test
    public void lines_is_mutant() {
        mutant.lines(4,1,10);
        assertEquals(mutant.getCountDna(), 1);
    }

    @Test
    public void lines_not_is_mutant() {
        mutant.lines(1,1,10);
        assertEquals(mutant.getCountDna(), 0);
    }

    @Test
    public void column_is_mutant() {
        mutant.column(0,0,10);
        assertEquals(mutant.getCountDna(), 1);
    }

    @Test
    public void column_not_is_mutant() {
        mutant.column(1,1,10);
        assertEquals(mutant.getCountDna(), 0);
    }

    @Test
    public void diagonal_is_mutant() {
        mutant.diagonal(0,0,10);
        assertEquals(mutant.getCountDna(), 1);
    }

    @Test
    public void diagnonal_not_is_mutant() {
        mutant.diagonal(2,1,10);
        assertEquals(mutant.getCountDna(), 0);
    }


    @Test
    public void diagonalReverse_is_mutant() {
        mutant.diagonalReverse(3,4);
        assertEquals(mutant.getCountDna(), 1);
    }

    @Test
    public void diagonalReverse_not_is_mutant() {
        mutant.diagonalReverse(2,1);
        assertEquals(mutant.getCountDna(), 0);
    }

}
