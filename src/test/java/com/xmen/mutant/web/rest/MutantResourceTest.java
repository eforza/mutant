package com.xmen.mutant.web.rest;

import com.xmen.mutant.TestUtil;
import com.xmen.mutant.service.MutantService;
import com.xmen.mutant.service.dto.DnaDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the MutantResource REST service.
 *
 * @see MutantResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantResourceTest {

    private final List<String> dnaMutant = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
    private final List<String> dnaHuman = Arrays.asList("ATGCGT", "CTGTAC", "TTAAGT", "AGAAGG", "CCTCTA", "TCACTG");
    private final List<String> humanOneMatchOnly = Arrays.asList("TAGTGC", "TTATGT", "CGACGG", "GAGTCA", "TCACTG", "AAAAAA");

    private final List<String> dnaMutantDiagonal = Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCTCTA", "TCACTG");
    private final List<String> dnaMutantDiagonalRight = Arrays.asList("ATGCGA", "CTGTAC", "TTAAGT", "AGAAGG", "CCTCTA", "AAAATC");
    private final List<String> dnaMutantHorizontal = Arrays.asList("AAAAAA", "CAGTGC", "TTATGT", "AGATGG", "CCCCTA", "AAAAAA");
    private final List<String> dnaMutantVertical = Arrays.asList("ATGCGA", "ATGTGC", "ATATGG", "AGATAG", "CCCATG", "TCACTG");
    private final List<String> dnaMutantVerticalHorizontal = Arrays.asList("AAAAAA", "ATGTGC", "GTATGG", "AGATAG", "CCCATG", "TCACTG");
    private final List<String> dnaMutantDiagonalDioagonalHorizontal= Arrays.asList("TAGTGC", "TTATGT", "AGACGG", "GAGTCA", "TCACTG", "AAAAAA");

    private final List<String> emptyList = Arrays.asList();
    private final List<String> notSquareMatrix = Arrays.asList("ATGCGA", "CTGTAC", "TTAAGT", "AGAAGG", "CCTCTA", "CCTCTA", "TCACTG");
    private final List<String> smallMatrix = Arrays.asList("ATG", "CTG", "TTA");
    private final List<String> invalidChar= Arrays.asList("ATGCGA", "CTGTGC", "TTATGT", "AGATGG", "CCCTTA", "TCACXG");;

    @Autowired
    private MutantService mutantService;

    /**
     * Mock the REST controller.
     */
    private MockMvc mutantResourceMockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        final MutantResource mutantResource = new MutantResource(mutantService);
        this.mutantResourceMockMvc = MockMvcBuilders.standaloneSetup(mutantResource)
                .build();
    }

    @Test
    public void isMutant() throws Exception {
        DnaDTO request = new DnaDTO(dnaMutant);

        mutantResourceMockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void isMutantDiagonal() throws Exception {
        DnaDTO request = new DnaDTO(dnaMutantDiagonal);

        mutantResourceMockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void isMutantDiagonalRight() throws Exception {
        DnaDTO request = new DnaDTO(dnaMutantDiagonalRight);

        mutantResourceMockMvc.perform(post("/mutant")

                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void isMutantHorizontal() throws Exception {
        DnaDTO request = new DnaDTO(dnaMutantHorizontal);

        mutantResourceMockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void isMutantVertical() throws Exception {
        DnaDTO request = new DnaDTO(dnaMutantVertical);

        mutantResourceMockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void isMutantVerticalHorizontal() throws Exception {
        DnaDTO request = new DnaDTO(dnaMutantVerticalHorizontal);

        mutantResourceMockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void isMutantTestDiagonal() throws Exception {
        DnaDTO request = new DnaDTO(dnaMutantDiagonal);

        mutantResourceMockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isOk());
    }


    @Test
    public void isHuman() throws Exception {
        DnaDTO request = new DnaDTO(dnaHuman);

        mutantResourceMockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isForbidden());

    }

    @Test
    public void isHumanOneMatchOnly() throws Exception {
        DnaDTO request = new DnaDTO(humanOneMatchOnly);

        mutantResourceMockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isForbidden());

    }

    @Test
    public void errorEmptyList() throws Exception {
        DnaDTO request = new DnaDTO(emptyList);

        mutantResourceMockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void errorNull() throws Exception {
        DnaDTO request = new DnaDTO(null);

        mutantResourceMockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void errorNotSquareMatrix() throws Exception {
        DnaDTO request = new DnaDTO(notSquareMatrix);

        mutantResourceMockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void errorSmallMatrix() throws Exception {
        DnaDTO request = new DnaDTO(smallMatrix);

        mutantResourceMockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void errorInvalidChar() throws Exception {
        DnaDTO request = new DnaDTO(invalidChar);

        mutantResourceMockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isBadRequest());

    }



}
