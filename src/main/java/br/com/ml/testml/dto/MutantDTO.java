package br.com.ml.testml.dto;

import java.io.Serializable;

public class MutantDTO implements Serializable {

    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}
