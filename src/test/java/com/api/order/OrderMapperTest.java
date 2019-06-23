package com.api.order;

import com.api.menu.MenuItem;
import com.api.order.item.OrderItem;
import com.api.order.status.OrderStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderMapperTest {

    private BigDecimal burgerPrice;
    private String burgerName;
    private MenuItem burgerMenuItem;
    private OrderItem burgerOrderItem;

    private BigDecimal friesPrice;
    private String friesName;
    private MenuItem friesMenuItem;
    private OrderItem friesOrderItem;

    private Integer orderId;
    private String statusName;
    private OrderStatus orderStatus;
    private Order order;

    private OrderMapper orderMapper;

    @BeforeEach
    void init() {
        burgerPrice = new BigDecimal(4.00);
        burgerName = "Burger";
        burgerMenuItem = new MenuItem();
        burgerOrderItem = new OrderItem();
        burgerMenuItem.setName(burgerName);
        burgerMenuItem.setPrice(burgerPrice);
        burgerOrderItem.setMenuItem(burgerMenuItem);

        friesPrice = new BigDecimal(2.00);
        friesName = "Fries";
        friesMenuItem = new MenuItem();
        friesOrderItem = new OrderItem();
        friesMenuItem.setName(friesName);
        friesMenuItem.setPrice(friesPrice);
        friesOrderItem.setMenuItem(friesMenuItem);

        orderId = 1;
        statusName = "Complete";
        orderStatus = new OrderStatus();
        order = new Order();
        order.setOrderId(orderId);
        orderStatus.setName(statusName);
        order.addOrderItem(burgerOrderItem);
        order.addOrderItem(friesOrderItem);
        order.setOrderStatus(orderStatus);

        orderMapper = new OrderMapper();
    }

    @Test
    void testGetOrderDTOForOrder() throws JsonProcessingException {
        OrderDTO predictedOrderDTO = new OrderDTO();
        predictedOrderDTO.setOrderId(orderId);
        predictedOrderDTO.setItems(Arrays.asList(burgerName, friesName));
        predictedOrderDTO.setPrice(burgerPrice.add(friesPrice));
        predictedOrderDTO.setStatus(statusName);
        String predictedOrderDTOAsString = new ObjectMapper().writeValueAsString(predictedOrderDTO);

        OrderDTO createdOrderDTO = orderMapper.getOrderDTOForOrder(order);

        String createdOrderDTOAsString = new ObjectMapper().writeValueAsString(createdOrderDTO);

        assertEquals(predictedOrderDTOAsString, createdOrderDTOAsString, "There was an error creating the order DTO.");
    }

}
