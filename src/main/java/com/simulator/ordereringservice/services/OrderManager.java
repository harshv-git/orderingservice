package com.simulator.ordereringservice.services;


import com.simulator.ordereringservice.constants.Message;
import com.simulator.ordereringservice.exceptions.ServiceException;
import com.simulator.ordereringservice.models.FoodItems;
import com.simulator.ordereringservice.models.Order;
import com.simulator.ordereringservice.models.OrdersList;

import com.simulator.ordereringservice.models.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class OrderManager {

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private OrderProducer orderProducer;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String FOOD_ITEMS_FILE = "foodItems.json";

    public FoodItems getAllFoodItemsFromFile() throws ServiceException {
        try {
            return objectMapper.readValue( getFile(FOOD_ITEMS_FILE) , FoodItems.class);
        } catch (IOException exception){
            log.error(exception.toString());
            throw new ServiceException(Message.GET_ITEMS_FROM_FILE_FAILURE);
        } catch (Exception exception) {
            log.error(exception.toString());
            throw new ServiceException(Message.GET_ITEMS_FROM_FILE_FAILURE);
        }
    }

    public Response createOrder(Order order) throws ServiceException {
        orderProducer.addOrderToWaitingOrders(order);
        return new Response(Message.ORDER_CREATED, HttpStatus.CREATED.value());
    }

    public OrdersList getAllOrdersStatus() throws ServiceException {
        return orderProducer.getOrdersList();
    }

    private File getFile(final String name) throws IOException {
        return resourceLoader.getResource("classpath:" + name ).getFile();
    }

}
