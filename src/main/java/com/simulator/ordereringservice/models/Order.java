package com.simulator.ordereringservice.models;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class Order {
    @Valid
    @NotEmpty
    private List<Item> items;
    private long orderCompletionTime;

    public long getOrderCompletionTime() {
        return orderCompletionTime;
    }

    public void setOrderCompletionTime(long orderCompletionTime) {
        this.orderCompletionTime = orderCompletionTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
