package br.com.ml.testml.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "MUTANT")
public class MutantEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public MutantEntity() {
        this.systemDate = new Date();
    }

    public MutantEntity(String dna, boolean mutant) {
        this.dna = dna;
        this.mutant = mutant;
        this.systemDate = new Date();
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name = "DNA")
    private String dna;

    @NotNull
    @Column(name = "MUTANT")
    private boolean mutant;

    @Column(name = "SYSTEM_DATE")
    public Date systemDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return "MutantEntity{" +
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
        MutantEntity that = (MutantEntity) o;
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
