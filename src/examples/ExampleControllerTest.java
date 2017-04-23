package com.marcus.chiu.springmvc._examples;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Boring test used as an example.
 */
@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class ExampleControllerTest {

    @InjectMocks
    private ExampleController exampleController;

    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(exampleController).build();
        //.setMessageConverters(new MappingJackson2HttpMessageConverter()).build
    }

    @Test
    public void exampleViewTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(
            get("/"));

        // print result to the console, for debuging
        MvcResult mvcResult = resultActions
            .andDo(print())
            .andReturn();

//        String content = mvcResult.getResponse().getContentAsString();

        // check results
        resultActions
            .andExpect(status().isOk())
            //.andExpect(content().contentType(MediaType.TEXT_HTML_VALUE))
            .andExpect(forwardedUrl("exampleView"));

    }

}

