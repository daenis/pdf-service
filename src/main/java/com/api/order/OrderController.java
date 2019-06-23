package com.api.order;

import com.api.order.ticket.OrderTicket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order-service/v1/orders")
    public ResponseEntity<Order> createOrderFromOrderTicket(@RequestBody OrderTicket orderTicket) {
        Order order = orderService.createOrderFromOrderTicket(orderTicket);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/order-service/v1/orders/complete/{order-id}")
    public ResponseEntity<Order> completeOrderByOrderId(@PathVariable("order-id") Integer orderId) {
        Order order = orderService.completeOrderByOrderId(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/order-service/v1/orders/cancel/{order-id}")
    public ResponseEntity<Order> cancelOrderByOrderId(@PathVariable("order-id") Integer orderId) {
        Order order = orderService.cancelOrderByOrderId(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
