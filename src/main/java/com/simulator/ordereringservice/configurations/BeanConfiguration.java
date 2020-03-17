package com.simulator.ordereringservice.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.ordereringservice.models.Order;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Deque;
import java.util.LinkedList;

@Configuration
public class BeanConfiguration {
    @Bean(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean(name = "queue")
    @Scope("prototype")
    public Deque<Order> createQueue(){
        return new LinkedList<>();
    }
}
