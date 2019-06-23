package com.api.order;

import com.api.order.status.Status;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    OrderDTO saveOrder(Order order) {
        return orderMapper.getOrderDTOForOrder(orderRepository.save(order));
    }

    List<OrderDTO> findPlacedOrders() {
        return orderRepository.findByOrderStatus_name(Status.PLACED.getName())
                              .stream()
                              .map(orderMapper::getOrderDTOForOrder)
                              .collect(Collectors.toList());
    }

    Order findByOrderId(Integer orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order does not exist."));
    }

}
