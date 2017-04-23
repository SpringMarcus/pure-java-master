package com.marcus.chiu.springmvc._examples;

import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Slf4j
@Service
public class ExampleService {
    @Autowired
    private RestOperations restOperations;
    @Autowired
    private MemcachedClient memcachedClient;

    public String executeExample() {
        return "Complete";
    }
}

