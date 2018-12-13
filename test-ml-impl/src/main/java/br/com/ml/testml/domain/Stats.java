package br.com.ml.testml.domain;

public class Stats {

    private Long count;
    private boolean isMutant;

    public Long getCount() {
        return count;
    }

    public boolean isMutant() {
        return isMutant;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setMutant(boolean mutant) {
        isMutant = mutant;
    }
}
