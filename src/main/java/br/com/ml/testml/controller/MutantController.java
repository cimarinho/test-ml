package br.com.ml.testml.controller;

import br.com.ml.testml.dto.MutantDTO;
import br.com.ml.testml.entity.MutantEntity;
import br.com.ml.testml.exception.MutantException;
import br.com.ml.testml.facade.MutantFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static br.com.ml.testml.controller.Endpoints.ROOT;

@RestController
@RequestMapping(ROOT)
public class MutantController {

    @Autowired
    private MutantFacade mutantFacade;

    @RequestMapping(value = "/mutant", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody MutantDTO mutantDTO) {
        try {
            boolean isMutant = mutantFacade.isMutant(mutantDTO.getDna());
            if (!mutantFacade.isMutant(mutantDTO.getDna())) {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
            this.mutantFacade.saveMutant(new MutantEntity(String.join(", ", mutantDTO.getDna()), isMutant));
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
