package br.com.ml.testml.facade;

import br.com.ml.testml.business.MutantBusiness;
import br.com.ml.testml.dto.StatsDTO;
import br.com.ml.testml.entity.MutantEntity;
import br.com.ml.testml.exception.MutantException;
import br.com.ml.testml.pojo.Stats;
import br.com.ml.testml.repository.MutantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class MutantFacade {

    @Autowired
    private MutantRepository mutantRepository;

    public boolean isMutant(String[] dna) throws MutantException {
        boolean isMutant = MutantBusiness.getMutant(dna).isMutant();
        return isMutant;
    }

    public StatsDTO getStats() {
        List<Stats> stats = mutantRepository.getStats();
        Long mutant = 0L, notMutant = 0L;
        if (stats != null && !stats.isEmpty()) {
            Optional<Stats> countMutant = stats.stream().filter(t -> t.isMutant()).findFirst();
            Optional<Stats> countNotMutant = stats.stream().filter(t -> !t.isMutant()).findFirst();
            if (countMutant.isPresent()) {
                mutant = countMutant.get().getCount();
            }
            if (countNotMutant.isPresent()) {
                notMutant = countNotMutant.get().getCount();
            }
        }
        return new StatsDTO(notMutant, mutant);
    }

    @Transactional
    public MutantEntity saveMutant(MutantEntity mutantEntity) {
        return mutantRepository.save(mutantEntity);
    }
}
