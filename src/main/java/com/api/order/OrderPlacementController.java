package com.api.order;

import com.api.order.ticket.OrderTicket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderPlacementController {

    private final OrderPlacementService orderPlacementService;

    public OrderPlacementController(OrderPlacementService orderPlacementService) {
        this.orderPlacementService = orderPlacementService;
    }

    @PostMapping("/order-service/v1/orders/place")
    public ResponseEntity<OrderDTO> placeOrderFromOrderTicket(@RequestBody OrderTicket orderTicket) {
        OrderDTO order = orderPlacementService.placeOrderFromOrderTicket(orderTicket);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/order-service/v1/orders/complete/{order-id}")
    public ResponseEntity<OrderDTO> completeOrderByOrderId(@PathVariable("order-id") Integer orderId) {
        OrderDTO order = orderPlacementService.completeOrderByOrderId(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/order-service/v1/orders/cancel/{order-id}")
    public ResponseEntity<OrderDTO> cancelOrderByOrderId(@PathVariable("order-id") Integer orderId) {
        OrderDTO order = orderPlacementService.cancelOrderByOrderId(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
