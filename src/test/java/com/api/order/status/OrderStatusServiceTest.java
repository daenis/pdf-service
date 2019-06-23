package com.api.order.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderStatusServiceTest {

    private String name;
    private OrderStatus orderStatus;

    @Mock
    private OrderStatusRepository orderStatusRepository;

    private OrderStatusService orderStatusService;

    @BeforeEach
    void init() {
        name = "Completed";
        orderStatus = new OrderStatus();

        orderStatusService = new OrderStatusService(orderStatusRepository);
    }

    @Test
    void testFindByName() {
        when(orderStatusRepository.findByName(name)).thenReturn(orderStatus);

        OrderStatus returnedOrderStatus = orderStatusService.findByName(name);

        assertEquals(orderStatus, returnedOrderStatus, "There was an error returning the order status.");
    }

}
