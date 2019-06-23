package com.api.order;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    OrderDTO getOrderDTOForOrder(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setItems(getItemNamesFromOrder(order));
        orderDTO.setPrice(getPriceOfOrder(order));
        orderDTO.setStatus(order.getOrderStatus().getName());
        return orderDTO;
    }

    private List<String> getItemNamesFromOrder(Order order) {
        return order.getOrderItems()
                    .stream()
                    .map(item -> item.getMenuItem().getName())
                    .collect(Collectors.toList());
    }

    private BigDecimal getPriceOfOrder(Order order) {
        return order.getOrderItems()
                    .stream()
                    .map(item -> item.getMenuItem().getPrice())
                    .reduce(new BigDecimal(0), BigDecimal::add);
    }

}
