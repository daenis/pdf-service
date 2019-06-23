package com.api.order.ticket;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderTicket implements Serializable {

    private List<String> items = new ArrayList<>();

}
