package br.com.ml.testml.controller;

import br.com.ml.testml.TestMlApplicationTests;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
public class MutantControllerTest extends TestMlApplicationTests {


    @Test
    public void create_sucess() throws Exception {
        this.mvc.perform(
                post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"dna\": [\"ATGCGA\", \"AAGTGC\", \"ATATGT\", \"AGAAGG\", \"CCCCTA\", \"TCACTG\"]}"))
                .andExpect(status().isOk());
    }

    @Test
    public void not_mutant() throws Exception {
        this.mvc.perform(
                post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"dna\": [\"TTTCGA\", \"CAGTGC\", \"TTATGT\", \"AGAATG\", \"CCGCTA\", \"TCACTG\"]}"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void create_unsucess() throws Exception {
        this.mvc.perform(
                post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"dna\": [\"ATGCGA\", \"ATATGT\", \"AGAAGG\", \"CCCCTA\", \"TCACTG\"]}"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void create_unsucess_badrequest() throws Exception {
        this.mvc.perform(
                post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"dna\": []}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void stats_ok() throws Exception {
        this.mvc.perform(get("/stats"))
                .andExpect(status().isOk());
    }

}
