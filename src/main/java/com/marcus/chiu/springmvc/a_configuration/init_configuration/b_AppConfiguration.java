package com.marcus.chiu.springmvc.a_configuration.init_configuration;

import com.marcus.chiu.springmvc.a_configuration.other_configuration.BusinessLogicConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by marcus.chiu on 10/16/16.
 * This is the root Configuration Class
 * @Configuration - indicates this class contains annotated bean method(s)
 * @EnableWebMvc - is equivalent to <mvc:annotation-driven /> in XML.
 * It enables support for @Controller-annotated classes that use @RequestMapping
 * to map incoming requests to a certain method.
 * @ComponentScan - refers to package locations to find the associated beans
 * @Import - imports another configuration class into this class (essentially appending it)
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.marcus.chiu.springmvc")
@Import({BusinessLogicConfiguration.class})
public class b_AppConfiguration {

    /**
     * Configures a view resolver to identify the real view
     * @return ViewResolver
     */
    @Bean
    public ViewResolver viewResolver() {
        //Create new View Resolver
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        //Set view class as JSP Standard Tags Library
        viewResolver.setViewClass(JstlView.class);

        //Set prefix and suffix
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        //return View Resolver
        return viewResolver;
    }

    /**
     * In the case of a validation failure, default error messages are shown.
     * To override the defaults, we need to configure the ResourceBundleMessageSource
     * @return MessageSource
     */
    @Bean
    public MessageSource messageSource() {
        //Create new ResourceBundleMessageSource object
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        //Spring will search for file named 'messages.properties' in app class path
        // ~/src/main/resources/messages.properties
        messageSource.setBasename("messages");

        //return as MessageSource
        return messageSource;
    }
}
