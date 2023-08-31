package com.george.bank.model.dto;

public class CustomerCreationRequest {


    private String name;

    public CustomerCreationRequest() {
    }

    public CustomerCreationRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
