package com.api.order;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderDTO implements Serializable {

    private Integer orderId;
    private List<String> items = new ArrayList<>();
    private String status;
    private BigDecimal price;

}
