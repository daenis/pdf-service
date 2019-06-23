package com.api.order;

import com.api.order.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    private Integer orderId;
    private Order order;
    private OrderDTO orderDTO;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private OrderRepository orderRepository;

    private OrderService orderService;

    @BeforeEach
    void init() {
        orderId = 1;
        order = new Order();
        orderDTO = new OrderDTO();

        orderService = new OrderService(orderMapper, orderRepository);
    }

    @Test
    void testSaveOrder() {
        when(orderRepository.save(order)).thenReturn(order);
        when(orderMapper.getOrderDTOForOrder(order)).thenReturn(orderDTO);

        OrderDTO returnedOrderDTO = orderService.saveOrder(order);

        assertEquals(orderDTO, returnedOrderDTO, "There was an error saving the order.");
    }

    @Test
    void testFindPlacedOrders() {
        List<OrderDTO> predictedOrderDTOs = Collections.singletonList(orderDTO);

        when(orderRepository.findByOrderStatus_name(Status.PLACED.getName())).thenReturn(Collections.singletonList(order));
        when(orderMapper.getOrderDTOForOrder(order)).thenReturn(orderDTO);

        List<OrderDTO> returnedOrderDTOs = orderService.findPlacedOrders();

        assertEquals(predictedOrderDTOs, returnedOrderDTOs, "There was an error returning the placed orders.");
    }

    @Test
    void testFindByOrderId() {
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Order returnedOrder = orderService.findByOrderId(orderId);

        assertEquals(order, returnedOrder, "There was an error returning the order.");
    }

    @Test
    void testFindByOrderId_orderDoesNotExist_runtimeExceptionIsThrown() {
        when(orderRepository.findById(orderId)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> orderService.findByOrderId(orderId));
    }

}
