package com.api.order;

import com.api.order.status.OrderStatus;
import com.api.order.status.OrderStatusService;
import com.api.order.status.Status;
import com.api.order.ticket.OrderTicket;
import com.api.order.ticket.OrderTicketMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderStatusService orderStatusService;
    private final OrderTicketMapper orderTicketMapper;

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, OrderStatusService orderStatusService, OrderTicketMapper orderTicketMapper) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.orderStatusService = orderStatusService;
        this.orderTicketMapper = orderTicketMapper;
    }

    OrderDTO createOrderFromOrderTicket(OrderTicket orderTicket) {
        Order order = orderTicketMapper.createOrderForOrderTicket(orderTicket);
        order.setOrderTime(LocalDateTime.now());
        order.setOrderStatus(findOrderStatusByName(Status.PLACED.getName()));
        orderRepository.save(order);
        return orderMapper.getOrderDTOForOrder(order);
    }

    List<OrderDTO> findPlacedOrders() {
        return orderRepository.findByOrderStatus_name(Status.PLACED.getName()).stream().map(orderMapper::getOrderDTOForOrder).collect(Collectors.toList());
    }

    OrderDTO completeOrderByOrderId(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order does not exist."));
        order.setOrderStatus(findOrderStatusByName(Status.COMPLETED.getName()));
        return orderMapper.getOrderDTOForOrder(order);
    }

    OrderDTO cancelOrderByOrderId(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order does not exist."));
        order.setOrderStatus(findOrderStatusByName(Status.CANCELED.getName()));
        return orderMapper.getOrderDTOForOrder(order);
    }

    private OrderStatus findOrderStatusByName(String name) {
        return orderStatusService.findByName(name);
    }

}
