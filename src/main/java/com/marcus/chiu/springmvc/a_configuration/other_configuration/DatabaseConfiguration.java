package com.marcus.chiu.springmvc.a_configuration.other_configuration;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.instrument.classloading.ReflectiveLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by marcus.chiu on 10/16/16.
 * @Configuration - indicates this class contains bean(s) methods annotated with @Bean
 * @ComponentScan - equivalent to 'context:component-scan base-package="..." in web.xml
 * This provides Spring where to look up for beans/classes
 * It will scan for any @Component classes (this includes @Controller, @Service, @Repository)
 * @EnableTransactionManagement - equivalent to Spring's tx:* XML namespace
 * enables Spring's annotation-driven transaction management
 * @PropertySource - used to declare a set of properties (which are defined in a
 * properties file in application classpath) into Spring run-time Environment bean
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.marcus.chiu.springmvc.a_configuration"})
@PropertySource(value = {"classpath:application.properties"})
public class DatabaseConfiguration {

    /*
     * Either use @Value("${}") to grab from properties
     * which is configured from the PropertiesConfiguration
     *
     * Or use the Environment bean below
     */

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    /**
     * Spring has defined an 'Element' bean somewhere I don't know
     * Thanks to @PropertySource, the real values are externalized in a
     * applications.properties file under resources folder
     * Spring's Environment fetches the value corresponding to an item
     */
    @Autowired
    private Environment environment;

    /**
     * This method creates a LocalSessionFactoryBean
     * This method mirrors exactly the XML based a_configuration
     * It will be injected into Bean method transactionManager
     * @return LocalSessionFactoryBean
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.marcus.chiu.springmvc.e_entity" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    /**
     * This method is called from sessionFactory()
     * @return Properties -
     */
    private Properties hibernateProperties() {
        //create new properties object
        Properties properties = new Properties();

        //start - set properties object's configurations

        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));

        //end

        //return properties object
        return properties;
    }

    /**
     * This method is called from sessionFactory()
     * @return DataSource - the database connection, source of data, data source
     */
    @Bean
    public DataSource dataSource() {
        //create new data source object
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        //start - set the data source object's configurations

        //set database driver type
        dataSource.setDriverClassName(driverClassName);
        //set url of database
        dataSource.setUrl(url);
        //set username of database
        dataSource.setUsername(username);
        //set password of database
        dataSource.setPassword(password);

        //end

        //return data source object
        return dataSource;
    }

    /**
     * @param sessionFactory is a bean created from the sessionFactory() Bean method
     *                       is "Autowired" by Spring
     * @return HibernateTransactionManager - provide transaction support for the session
     * created by the sessionFactory
     */
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }


    //Bottom two methods for dealing with EntityManagerFactory bean

    @Bean
    public Properties hibernateProperties2(){
        final Properties properties = new Properties();

        properties.put( "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect" );
        properties.put( "hibernate.connection.driver_class", "org.postgresql.Driver" );
        properties.put( "hibernate.hbm2ddl.auto", "create-drop" );

        return properties;
    }

    /**
     * JPA EntityManagerFactory
     * @param dataSource
     * @return EntityManagerFactory - this creates EntityManager Objects
     */
    @Bean
    public EntityManagerFactory entityManagerFactory( DataSource dataSource){
        System.out.println("creating EntityManagerFactory");
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.marcus.chiu.springmvc.a_configuration");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(hibernateProperties());
        em.setPersistenceUnitName("mytestdomain");
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();

        return em.getObject();
    }
}