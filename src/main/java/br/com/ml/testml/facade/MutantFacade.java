package br.com.ml.testml.facade;

import br.com.ml.testml.business.MutantBusiness;
import br.com.ml.testml.entity.MutantEntity;
import br.com.ml.testml.exception.MutantException;
import br.com.ml.testml.repository.MutantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class MutantFacade {

    @Autowired
    private MutantRepository mutantRepository;

    public boolean isMutant(String[] dna) throws MutantException {
        boolean isMutant = MutantBusiness.getMutant(dna).isMutant();
        return isMutant;
    }

    @Transactional
    public MutantEntity saveMutant(MutantEntity mutantEntity) {
        return mutantRepository.save(mutantEntity);
    }
}
