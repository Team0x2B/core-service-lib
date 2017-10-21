package org.x2b.studi.core;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = IntegrationTestService.class)
public class TestIntegrationService {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;




    @Before
    public void setup() {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }


    @Test
    public void contextLoads() throws Exception {

    }

    public ResultMatcher okMatcher() {
        return MockMvcResultMatchers.status().isOk();
    }

    @Test
    public void testGraphQlResponds() throws Exception {
        ResultMatcher ok = okMatcher();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/graphiql");
        mockMvc.perform(builder)
                .andExpect(ok);
    }


}
