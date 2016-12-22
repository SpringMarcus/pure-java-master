package com.marcus.chiu.springmvc.a_configuration.other_configuration;

import com.marcus.chiu.springmvc.a_bean.BeanExample;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by marcus.chiu on 10/28/16.
 * @Configuration - indicates this class can be used by the Spring IoC container
 *                  as a source of bean definitions
 */
@Configuration
public class BusinessLogicConfiguration {

    /**
     * @Bean - tells Spring that this method will return an object that should be registered
     *         as a bean in Spring Application Context
     *       - initMethod - calls initialization() before/during creation of Bean
     *       - destroyMethod - calls destruction() before destruction of Bean
     * @Scope - singleton - is default
     *        - prototype - completely new Bean each time its "autowired"
     * @return - the Bean
     */
    @Bean (initMethod = "initialization", destroyMethod = "destruction")
    //@Scope ("prototype")
    public BeanExample beanExample() {
        return new BeanExample();
    }
}
