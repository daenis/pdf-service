package com.api.order.ticket;

import com.api.menu.MenuItem;
import com.api.menu.MenuItemService;
import com.api.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderTicketMapperTest {

    private String itemName;
    private OrderTicket orderTicket;
    private MenuItem menuItem;

    @Mock
    private MenuItemService menuItemService;

    private OrderTicketMapper orderTicketMapper;

    @BeforeEach
    void init() {
        itemName = "Hot Dog";
        orderTicket = new OrderTicket();
        menuItem = new MenuItem();

        orderTicket.setItems(Collections.singletonList(itemName));
        menuItem.setName(itemName);

        orderTicketMapper = new OrderTicketMapper(menuItemService);
    }

    @Test
    void testCreateOrderForOrderTicket() {
        when(menuItemService.findByName(itemName)).thenReturn(menuItem);

        Order createdOrder = orderTicketMapper.createOrderForOrderTicket(orderTicket);

        MenuItem itemInCreatedOrder = createdOrder.getOrderItems().get(0).getMenuItem();
        assertEquals(menuItem, itemInCreatedOrder, "There was an error creating the order.");
    }

}
