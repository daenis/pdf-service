package com.api.order;

import com.api.order.status.OrderStatus;
import com.api.order.status.OrderStatusService;
import com.api.order.status.Status;
import com.api.order.ticket.OrderTicket;
import com.api.order.ticket.OrderTicketMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusService orderStatusService;
    private final OrderTicketMapper orderTicketMapper;

    public OrderService(OrderRepository orderRepository,
                        OrderStatusService orderStatusService,
                        OrderTicketMapper orderTicketMapper) {
        this.orderRepository = orderRepository;
        this.orderStatusService = orderStatusService;
        this.orderTicketMapper = orderTicketMapper;
    }

    Order createOrderFromOrderTicket(OrderTicket orderTicket) {
        Order order = orderTicketMapper.createOrderForOrderTicket(orderTicket);
        order.setOrderTime(LocalDateTime.now());
        order.setOrderStatus(findOrderStatusByName(Status.PLACED.getName()));
        orderRepository.save(order);
        return order;
    }

    Order completeOrderByOrderId(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order does not exist."));
        order.setOrderStatus(findOrderStatusByName(Status.COMPLETED.getName()));
        return orderRepository.save(order);
    }

    Order cancelOrderByOrderId(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order does not exist."));
        order.setOrderStatus(findOrderStatusByName(Status.CANCELED.getName()));
        return orderRepository.save(order);
    }

    private OrderStatus findOrderStatusByName(String name) {
        return orderStatusService.findByName(name);
    }

}
