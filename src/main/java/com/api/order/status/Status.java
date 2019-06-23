package com.api.order.status;

public enum Status {

    COMPLETED("Completed"),
    CANCELED("Canceled"),
    PLACED("Placed");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
