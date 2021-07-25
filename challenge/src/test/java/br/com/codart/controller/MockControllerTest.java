package br.com.codart.controller;

import org.junit.jupiter.api.Test;
import br.com.codart.model.Average;
import static org.hamcrest.Matchers.*;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MockControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_throw_exception_when_address_is_not_found() throws Exception {

        mockMvc.perform(get("/api/mocks/address/{cnpj}", "16640581000166"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void must_throw_exception_when_cnpj_is_invalid() throws Exception {

        mockMvc.perform(get("/api/mocks/address/{cnpj}", "00000000000000"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void should_return_success_finding_address() throws Exception {

        mockMvc.perform(get("/api/mocks/address/{cnpj}", "58443544000108"))
                .andExpect(status().isOk());

    }

    @Test
    public void must_throw_an_exception_when_values_are_null() throws Exception {

        mockMvc.perform(post("/api/mocks/averages")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void should_return_success_when_returning_average() throws Exception {

        Average average = new Average(8d, 9d);

        mockMvc.perform(post("/api/mocks/averages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(average)))
                .andExpect(jsonPath("$.average", is(8.5)))
                .andExpect(status().isOk());

    }

}
