package com.api.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order-service/v1/orders/placed")
    public ResponseEntity<List<OrderDTO>> findPlacedOrders() {
        List<OrderDTO> orders = orderService.findPlacedOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
