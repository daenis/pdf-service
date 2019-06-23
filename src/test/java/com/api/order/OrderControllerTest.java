package com.api.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    private OrderDTO orderDTO;
    private List<OrderDTO> orderDTOs;

    @Mock
    private OrderService orderService;

    private OrderController orderController;

    @BeforeEach
    void init() {
        orderDTO = new OrderDTO();
        orderDTOs = Collections.singletonList(orderDTO);

        orderController = new OrderController(orderService);
    }

    @Test
    void testFindPlacedOrders() {
        ResponseEntity<List<OrderDTO>> predictedResponse = new ResponseEntity<>(orderDTOs, HttpStatus.OK);

        when(orderService.findPlacedOrders()).thenReturn(orderDTOs);

        ResponseEntity<List<OrderDTO>> returnedResponse = orderController.findPlacedOrders();

        assertEquals(predictedResponse, returnedResponse, "The expected response was not returned.");
    }

}
