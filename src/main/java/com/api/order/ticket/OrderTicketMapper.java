package com.api.order.ticket;

import com.api.menu.MenuItem;
import com.api.menu.MenuItemService;
import com.api.order.Order;
import com.api.order.item.OrderItem;
import org.springframework.stereotype.Service;

@Service
public class OrderTicketMapper {

    private final MenuItemService menuItemService;

    public OrderTicketMapper(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    public Order createOrderForOrderTicket(OrderTicket orderTicket) {
        Order order = new Order();
        orderTicket.getItems()
                   .forEach(item -> {
                       MenuItem menuItem = menuItemService.findByName(item);
                       OrderItem orderItem = new OrderItem();
                       orderItem.setMenuItem(menuItem);
                       order.addOrderItem(orderItem);
                   });
        return order;
    }

}
