package br.com.ml.testml.repository.impl;

import br.com.ml.testml.domain.Mutant;
import br.com.ml.testml.domain.Stats;
import br.com.ml.testml.repository.MutantCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class MutantCustomRepositoryImpl implements MutantCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;


    public List<Stats> getStats() {

        Aggregation aggregation = newAggregation(
                group("mutant").count().as("count"),
                project("count").and("isMutant").previousOperation(),
                sort(Sort.Direction.DESC, "count")
        );

        AggregationResults<Stats> aggregationResults
                = mongoTemplate.aggregate(aggregation, Mutant.class, Stats.class);

        return aggregationResults.getMappedResults();

    }
}
