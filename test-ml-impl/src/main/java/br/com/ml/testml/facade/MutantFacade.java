package br.com.ml.testml.facade;

import br.com.ml.testml.business.MutantBusiness;
import br.com.ml.testml.domain.Mutant;
import br.com.ml.testml.dto.StatsDTO;
import br.com.ml.testml.exception.MutantException;
import br.com.ml.testml.domain.Stats;
import br.com.ml.testml.repository.impl.MutantCustomRepositoryImpl;
import br.com.ml.testml.repository.MutantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MutantFacade {

    @Autowired
    private MutantCustomRepositoryImpl mutantCustomRepository;

    @Autowired
    private MutantRepository mutantRepository;

    public boolean isMutant(String[] dna) throws MutantException {
        return MutantBusiness.getMutant(dna).isMutant();
    }

    public StatsDTO getStats() {
        List<Stats> stats = mutantCustomRepository.getStats();
        Long notMutant = 0L, total = 0L;
        if (stats != null && !stats.isEmpty()) {
            Optional<Stats> countMutant = stats.stream().filter(t -> !t.isMutant()).findFirst();
            Optional<Stats> countNotMutant = stats.stream().filter(t -> t.isMutant()).findFirst();
            if (countMutant.isPresent()) {
                total += countMutant.get().getCount();
                ;
            }
            if (countNotMutant.isPresent()) {
                notMutant = countNotMutant.get().getCount();
                total += notMutant;
            }
        }
        return new StatsDTO(notMutant, total);
    }

    public Mutant saveMutant(Mutant mutantEntity) {
        return mutantRepository.save(mutantEntity);
    }
}
