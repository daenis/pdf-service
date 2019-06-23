package com.api.order;

import com.api.order.status.OrderStatus;
import com.api.order.status.OrderStatusService;
import com.api.order.status.Status;
import com.api.order.ticket.OrderTicket;
import com.api.order.ticket.OrderTicketMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderPlacementServiceTest {

    private Integer orderId;
    private OrderTicket orderTicket;
    private OrderStatus orderStatus;
    private Order order;
    private OrderDTO orderDTO;

    @Mock
    private OrderService orderService;

    @Mock
    private OrderStatusService orderStatusService;

    @Mock
    private OrderTicketMapper orderTicketMapper;

    private OrderPlacementService orderPlacementService;

    @BeforeEach
    void init() {
        orderId = 1;
        orderTicket = new OrderTicket();
        orderStatus = new OrderStatus();
        order = new Order();
        orderDTO = new OrderDTO();

        orderPlacementService = new OrderPlacementService(orderService, orderStatusService, orderTicketMapper);
    }

    @Test
    void testPlaceOrderFromOrderTicket() {
        when(orderTicketMapper.createOrderForOrderTicket(orderTicket)).thenReturn(order);
        when(orderStatusService.findByName(Status.PLACED.getName())).thenReturn(orderStatus);
        when(orderService.saveOrder(order)).thenReturn(orderDTO);

        OrderDTO returnedOrderDTO = orderPlacementService.placeOrderFromOrderTicket(orderTicket);

        assertEquals(orderDTO, returnedOrderDTO, "There was an error placing the order.");
    }

    @Test
    void testPlaceOrderFromOrderTicket_orderStatusIsUpdated() {
        when(orderTicketMapper.createOrderForOrderTicket(orderTicket)).thenReturn(order);
        when(orderStatusService.findByName(Status.PLACED.getName())).thenReturn(orderStatus);
        when(orderService.saveOrder(order)).thenReturn(orderDTO);

        orderPlacementService.placeOrderFromOrderTicket(orderTicket);

        assertEquals(orderStatus, order.getOrderStatus(), "There was an error setting the order status.");
    }

    @Test
    void testCompleteOrderByOrderId() {
        when(orderService.findByOrderId(orderId)).thenReturn(order);
        when(orderStatusService.findByName(Status.COMPLETED.getName())).thenReturn(orderStatus);
        when(orderService.saveOrder(order)).thenReturn(orderDTO);

        OrderDTO returnedOrderDTO = orderPlacementService.completeOrderByOrderId(orderId);

        assertEquals(orderDTO, returnedOrderDTO, "There was an error completing the order.");
    }

    @Test
    void testCompleteOrderByOrderId_orderStatusIsUpdated() {
        when(orderService.findByOrderId(orderId)).thenReturn(order);
        when(orderStatusService.findByName(Status.COMPLETED.getName())).thenReturn(orderStatus);
        when(orderService.saveOrder(order)).thenReturn(orderDTO);

        orderPlacementService.completeOrderByOrderId(orderId);

        assertEquals(orderStatus, order.getOrderStatus(), "There was an error setting the order status.");
    }

    @Test
    void testCancelOrderByOrderId() {
        when(orderService.findByOrderId(orderId)).thenReturn(order);
        when(orderStatusService.findByName(Status.CANCELED.getName())).thenReturn(orderStatus);
        when(orderService.saveOrder(order)).thenReturn(orderDTO);

        OrderDTO returnedOrderDTO = orderPlacementService.cancelOrderByOrderId(orderId);

        assertEquals(orderDTO, returnedOrderDTO, "There was an error canceling the order.");
    }

    @Test
    void testCancelOrderByOrderId_orderStatusIsUpdated() {
        when(orderService.findByOrderId(orderId)).thenReturn(order);
        when(orderStatusService.findByName(Status.CANCELED.getName())).thenReturn(orderStatus);
        when(orderService.saveOrder(order)).thenReturn(orderDTO);

        orderPlacementService.cancelOrderByOrderId(orderId);

        assertEquals(orderStatus, order.getOrderStatus(), "There was an error setting the order status.");
    }

}
