package com.marcus.chiu.springmvc._examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class ExampleController {
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String exampleView(Model model)  {
        return "exampleView";
    }



}