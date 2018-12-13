package br.com.ml.testml.domain;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Mutant implements Serializable {

    private static final long serialVersionUID = 1L;

    public Mutant() {
        this.systemDate = new Date();
    }

    public Mutant(String dna, boolean mutant) {
        this.dna = dna;
        this.mutant = mutant;
        this.systemDate = new Date();
    }

    @Id
    private String id;

    private String dna;

    private boolean mutant;

    private Date systemDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public boolean isMutant() {
        return mutant;
    }

    public void setMutant(boolean mutant) {
        this.mutant = mutant;
    }

    public Date getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(Date systemDate) {
        this.systemDate = systemDate;
    }

    @Override
    public String toString() {
        return "Mutant{" +
                "id=" + id +
                ", dna='" + dna + '\'' +
                ", mutant=" + mutant +
                ", systemDate=" + systemDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mutant that = (Mutant) o;
        return mutant == that.mutant &&
                Objects.equals(id, that.id) &&
                Objects.equals(dna, that.dna) &&
                Objects.equals(systemDate, that.systemDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dna, mutant, systemDate);
    }
}
