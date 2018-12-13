package br.com.ml.testml.controller;

import br.com.ml.testml.dto.MutantDTO;
import br.com.ml.testml.dto.StatsDTO;
import br.com.ml.testml.domain.Mutant;
import br.com.ml.testml.facade.MutantFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static br.com.ml.testml.controller.Endpoints.ROOT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(ROOT)
public class MutantController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MutantController.class);

    @Autowired
    private MutantFacade mutantFacade;

    @RequestMapping(value = "/mutant", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody MutantDTO mutantDTO) {
        try {
            boolean isMutant = mutantFacade.isMutant(mutantDTO.getDna());
            this.mutantFacade.saveMutant(new Mutant(String.join(", ", mutantDTO.getDna()), isMutant));
            if (!mutantFacade.isMutant(mutantDTO.getDna())) {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Erro no DNA", e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = {"/stats"}, method = GET)
    public StatsDTO get() throws Exception {

        return mutantFacade.getStats();
    }

}
