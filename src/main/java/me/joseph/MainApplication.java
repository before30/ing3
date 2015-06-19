package me.joseph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan
@PropertySources(value = {@PropertySource("classpath:config.properties"), @PropertySource("classpath:messages.properties")})
public class MainApplication extends SpringBootServletInitializer {

    @Autowired
    private ApplicationContext context;

    @PostConstruct
    public void setUp(){

        System.out.println("-------SETUP------");

    }

    @PreDestroy
    public void tearDown(){
        System.out.println("-------TEARDOWN------");

    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
