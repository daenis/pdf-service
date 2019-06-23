package com.api.order;

import com.api.order.ticket.OrderTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderPlacementControllerTest {

    private Integer orderId;
    private OrderTicket orderTicket;
    private OrderDTO orderDTO;

    @Mock
    private OrderPlacementService orderPlacementService;

    private OrderPlacementController orderPlacementController;

    @BeforeEach
    void init() {
        orderId = 1;
        orderTicket = new OrderTicket();
        orderDTO = new OrderDTO();

        orderPlacementController = new OrderPlacementController(orderPlacementService);
    }

    @Test
    void testPlaceOrderFromOrderTicket() {
        ResponseEntity<OrderDTO> predictedResponse = new ResponseEntity<>(orderDTO, HttpStatus.CREATED);

        when(orderPlacementService.placeOrderFromOrderTicket(orderTicket)).thenReturn(orderDTO);

        ResponseEntity<OrderDTO> actualResponse = orderPlacementController.placeOrderFromOrderTicket(orderTicket);

        assertEquals(predictedResponse, actualResponse, "There was an error returning the response.");
    }

    @Test
    void testCompleteOrderByOrderId() {
        ResponseEntity<OrderDTO> predictedResponse = new ResponseEntity<>(orderDTO, HttpStatus.OK);

        when(orderPlacementService.completeOrderByOrderId(orderId)).thenReturn(orderDTO);

        ResponseEntity<OrderDTO> actualResponse = orderPlacementController.completeOrderByOrderId(orderId);

        assertEquals(predictedResponse, actualResponse, "There was an error returning the response.");
    }

    @Test
    void testCancelOrderByOrderId() {
        ResponseEntity<OrderDTO> predictedResponse = new ResponseEntity<>(orderDTO, HttpStatus.OK);

        when(orderPlacementService.cancelOrderByOrderId(orderId)).thenReturn(orderDTO);

        ResponseEntity<OrderDTO> actualResponse = orderPlacementController.cancelOrderByOrderId(orderId);

        assertEquals(predictedResponse, actualResponse, "There was an error returning the response.");
    }

}
