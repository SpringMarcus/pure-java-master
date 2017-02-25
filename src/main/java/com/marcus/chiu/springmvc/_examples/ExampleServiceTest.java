package com.marcus.chiu.springmvc._examples;

import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.MemcachedClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestOperations;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ExampleServiceTest {

    @InjectMocks
    private ExampleService exampleService;

    @Mock
    private RestOperations restOperations;

    @Mock
    private MemcachedClient memcachedClient;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void executeExampleTest() {
        String result = exampleService.executeExample();

        assertThat(result).isEqualTo("Complete");
    }
}

