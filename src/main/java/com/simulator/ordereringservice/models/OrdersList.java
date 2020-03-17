package com.simulator.ordereringservice.models;

import java.util.List;

public class OrdersList {
    private List<Order> completedOrders;
    private List<Order> waitingOrders;

    public List<Order> getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(List<Order> completedOrders) {
        this.completedOrders = completedOrders;
    }

    public List<Order> getWaitingOrders() {
        return waitingOrders;
    }

    public void setWaitingOrders(List<Order> waitingOrders) {
        this.waitingOrders = waitingOrders;
    }
}
