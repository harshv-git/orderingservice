package com.simulator.ordereringservice.controllers;

import com.simulator.ordereringservice.exceptions.ServiceException;
import com.simulator.ordereringservice.models.FoodItems;
import com.simulator.ordereringservice.models.Order;
import com.simulator.ordereringservice.models.OrdersList;
import com.simulator.ordereringservice.models.Response;
import com.simulator.ordereringservice.services.OrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class OrderingController {

    @Autowired
    OrderManager orderManager;

    @GetMapping("/items")
    public FoodItems getAllFoodItems() throws ServiceException {
        return orderManager.getAllFoodItemsFromFile();
    }

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createOrder(@Valid @RequestBody Order order) throws ServiceException {
        return orderManager.createOrder(order);
    }

    @GetMapping("/status")
    public OrdersList getOrdersStatus() throws ServiceException {
        return orderManager.getAllOrdersStatus();
    }
}
