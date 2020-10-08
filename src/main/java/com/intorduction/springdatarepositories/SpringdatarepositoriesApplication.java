package com.intorduction.springdatarepositories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class SpringdatarepositoriesApplication {

    public static void main(String[] args) {
        ApplicationContext run = SpringApplication.run(SpringdatarepositoriesApplication.class, args);
        Arrays.stream(run.getBeanDefinitionNames()).forEach(System.out::println);
    }

}
