package com.simulator.ordereringservice.services;

import com.simulator.ordereringservice.constants.Message;
import com.simulator.ordereringservice.exceptions.ServiceException;
import com.simulator.ordereringservice.models.Item;
import com.simulator.ordereringservice.models.Order;
import com.simulator.ordereringservice.models.OrdersList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

@Component
@Slf4j
public class OrderProducer {

    @Autowired
    @Qualifier("queue")
    private Deque<Order> waitingOrders;
    @Autowired
    @Qualifier("queue")
    private Deque<Order> completedOrders;

    public void addOrderToWaitingOrders(Order order) throws ServiceException {
        try{
            order.setOrderCompletionTime(getOrderCompletionTimeInMiliSec(order));
            waitingOrders.addLast(order);
        } catch (Exception exception){
            log.error(exception.toString());
            throw new ServiceException(Message.ORDER_CREATION_FAILURE);
        }
    }

    public OrdersList getOrdersList() throws ServiceException {
        try {
            updateOrdersStatus();
            return createOrderList();
        } catch (Exception exception){
            log.error(exception.toString());
            throw new ServiceException(Message.GET_ORDERS_FAILURE);
        }
    }

    private long getOrderCompletionTimeInMiliSec(Order order){
        long totalPreparationTimeInMiliSec = 0;
        for(Item orderItem : order.getItems()){
            totalPreparationTimeInMiliSec += orderItem.getEtc() * 60 * 1000;
        }
        return totalPreparationTimeInMiliSec + System.currentTimeMillis();
    }

    private void updateOrdersStatus(){
        while (!waitingOrders.isEmpty() && isOrderPrepared()){
            completedOrders.addLast(waitingOrders.removeFirst());
        }
    }
    private boolean isOrderPrepared(){
        return waitingOrders.getFirst().getOrderCompletionTime() < System.currentTimeMillis();
    }

    private OrdersList createOrderList(){
        OrdersList orders = new OrdersList();
        orders.setCompletedOrders((List<Order>) completedOrders);
        orders.setWaitingOrders((List<Order>) waitingOrders);
        return orders;
    }
}
