package com.marcus.chiu.springmvc.b_controller.rest;

import com.marcus.chiu.springmvc.a_bean.BeanExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by marcus.chiu on 10/29/16.
 */
@RestController
public class RestControllerExample {

    private final String template = "Hello, %s!";

    @Autowired
    private BeanExample beanExample;

    @RequestMapping("/greeting")
    public BeanExample greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        beanExample.setText("Hello, " + name);
        return beanExample;
    }
}
