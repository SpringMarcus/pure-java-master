package com.marcus.chiu.springmvc.a_configuration.other_configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class PropertiesConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        // create Property Configurer
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();

        // configurations
        properties.setIgnoreResourceNotFound(true);
        properties.setIgnoreUnresolvablePlaceholders(true);

        List<Resource> resources = new ArrayList<>();

        // standard properties
        resources.add(new ClassPathResource("application.properties"));

        // user defined properties (overrides standard properties)
        String userPropertiesPath = System.getProperty("user.name") + "_application.properties";
        ClassPathResource userProperties = new ClassPathResource(userPropertiesPath);
        if (userProperties.exists()) {
            resources.add(userProperties);
        }

        // add resources array to Property Configurer
        properties.setLocations(resources.toArray(new Resource[resources.size()]));

        return properties;
    }
}

