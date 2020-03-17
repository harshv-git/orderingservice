package com.simulator.ordereringservice.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Item {
    @NotBlank
    private String name;
    @NotNull
    private Integer price;
    @NotNull
    private Integer etc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getEtc() {
        return etc;
    }

    public void setEtc(Integer etc) {
        this.etc = etc;
    }
}
