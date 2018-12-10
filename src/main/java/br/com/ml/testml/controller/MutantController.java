package br.com.ml.testml.controller;

import br.com.ml.testml.business.MutantBusiness;
import br.com.ml.testml.dto.MutantDTO;
import br.com.ml.testml.exception.MutantException;
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


    @RequestMapping(value = "/mutant", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody MutantDTO mutantDTO) {
        try {
            if (! MutantBusiness.getMutant(mutantDTO.getDna()).isMutant()) {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        } catch (MutantException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
