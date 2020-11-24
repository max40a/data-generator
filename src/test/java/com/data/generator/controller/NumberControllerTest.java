package com.data.generator.controller;

import com.data.generator.service.NumberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NumberController.class)
class NumberControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private NumberService numberService;

    @Test
    void generateLongs_should_return_required_data() throws Exception {
        List<Long> responseBody = List.of(1L, 2L, 3L);
        when(numberService.generateLongs(anyInt())).thenReturn(responseBody);

        mvc.perform(get("/number/longs")
            .contentType(MediaType.APPLICATION_JSON)
            .param("limit", String.valueOf(3)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(responseBody.size())))
            .andExpect(jsonPath("[0]", is(1)))
            .andExpect(jsonPath("[1]", is(2)))
            .andExpect(jsonPath("[2]", is(3)));
    }
}
