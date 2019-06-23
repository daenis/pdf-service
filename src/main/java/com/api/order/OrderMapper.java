package com.api.order;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    OrderDTO getOrderDTOForOrder(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setItems(order.getOrderItems()
                               .stream()
                               .map(item -> item.getMenuItem().getName())
                               .collect(Collectors.toList()));
        orderDTO.setPrice(order.getOrderItems()
                               .stream()
                               .map(item -> item.getMenuItem().getPrice())
                               .reduce(new BigDecimal(0), BigDecimal::add));
        orderDTO.setStatus(order.getOrderStatus().getName());
        return orderDTO;
    }

}
