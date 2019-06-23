package com.api.order;

import com.api.order.status.OrderStatus;
import com.api.order.status.OrderStatusService;
import com.api.order.status.Status;
import com.api.order.ticket.OrderTicket;
import com.api.order.ticket.OrderTicketMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderPlacementService {

    private final OrderService orderService;
    private final OrderStatusService orderStatusService;
    private final OrderTicketMapper orderTicketMapper;

    public OrderPlacementService(OrderService orderService,
                                 OrderStatusService orderStatusService,
                                 OrderTicketMapper orderTicketMapper) {
        this.orderService = orderService;
        this.orderStatusService = orderStatusService;
        this.orderTicketMapper = orderTicketMapper;
    }

    OrderDTO placeOrderFromOrderTicket(OrderTicket orderTicket) {
        Order order = orderTicketMapper.createOrderForOrderTicket(orderTicket);
        order.setOrderStatus(findOrderStatusByName(Status.PLACED.getName()));
        return orderService.saveOrder(order);
    }

    OrderDTO completeOrderByOrderId(Integer orderId) {
        Order order = orderService.findByOrderId(orderId);
        order.setOrderStatus(findOrderStatusByName(Status.COMPLETED.getName()));
        return orderService.saveOrder(order);
    }

    OrderDTO cancelOrderByOrderId(Integer orderId) {
        Order order = orderService.findByOrderId(orderId);
        order.setOrderStatus(findOrderStatusByName(Status.CANCELED.getName()));
        return orderService.saveOrder(order);
    }

    private OrderStatus findOrderStatusByName(String name) {
        return orderStatusService.findByName(name);
    }

}
