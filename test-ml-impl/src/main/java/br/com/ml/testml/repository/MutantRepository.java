package br.com.ml.testml.repository;

import br.com.ml.testml.domain.Mutant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MutantRepository extends MongoRepository<Mutant, String> {


}
