package br.com.ml.testml.repository;

import br.com.ml.testml.entity.MutantEntity;
import br.com.ml.testml.pojo.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MutantRepository extends JpaRepository<MutantEntity, Long> {


    @Query("SELECT new br.com.ml.testml.pojo.Stats(count(m), m.mutant ) FROM MutantEntity m group by mutant ")
    List<Stats> getStats();

}
