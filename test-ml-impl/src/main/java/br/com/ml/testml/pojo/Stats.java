package br.com.ml.testml.pojo;

public class Stats {

    private Long count;
    private boolean isMutant;

    public Stats(Long count, boolean isMutant) {
        this.count = count;
        this.isMutant = isMutant;
    }

    public Long getCount() {
        return count;
    }

    public boolean isMutant() {
        return isMutant;
    }
}
