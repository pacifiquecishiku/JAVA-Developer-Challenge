package com.witsoftware.challenge.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).addFilters().build();
    }

    @Test
    public void deveSeConectarPelaUrlCorreta() throws Exception {
        mockMvc.perform(get("/sum?a=12&b=45"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/subtract?a=12&b=45"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/multiply?a=12&b=45"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/divide?a=12&b=45"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/xxx?a=12&b=45"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveRetornarErroQuandoOsOperandoEstiveremErrados() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/sum?a=4ugh&b=45"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();

        Assert.assertTrue(responseBody.contains("\"result\":null"));
        Assert.assertTrue(responseBody.contains("\"hasError\":true"));
        Assert.assertTrue(responseBody.contains("4ugh"));
    }
}