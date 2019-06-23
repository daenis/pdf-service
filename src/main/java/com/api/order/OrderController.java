package com.api.order;

import com.api.order.ticket.OrderTicket;
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

    @PostMapping("/order-service/v1/orders")
    public ResponseEntity<OrderDTO> createOrderFromOrderTicket(@RequestBody OrderTicket orderTicket) {
        OrderDTO order = orderService.createOrderFromOrderTicket(orderTicket);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/order-service/v1/orders/placed")
    public ResponseEntity<List<OrderDTO>> findPlacedOrders() {
        List<OrderDTO> orders = orderService.findPlacedOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/order-service/v1/orders/complete/{order-id}")
    public ResponseEntity<OrderDTO> completeOrderByOrderId(@PathVariable("order-id") Integer orderId) {
        OrderDTO order = orderService.completeOrderByOrderId(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/order-service/v1/orders/cancel/{order-id}")
    public ResponseEntity<OrderDTO> cancelOrderByOrderId(@PathVariable("order-id") Integer orderId) {
        OrderDTO order = orderService.cancelOrderByOrderId(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
