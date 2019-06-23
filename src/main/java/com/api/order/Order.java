package com.api.order;

import com.api.menu.MenuItem;
import com.api.order.item.OrderItem;
import com.api.order.status.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
@EqualsAndHashCode
public class Order implements Serializable {

    private static final long serialVersionUID = 89342473892L;

    // TODO: ID is not generating properly (create two orders with multiple items and note the second order's order ID)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Column(name = "ORDER_TIME")
    private LocalDateTime orderTime;

    @ManyToOne
    @JoinColumn(name = "ORDER_STATUS_ID")
    private OrderStatus orderStatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Transient
    @JsonProperty("totalPrice")
    private BigDecimal totalPrice() {
        return orderItems
                .stream()
                .map(item -> item.getMenuItem().getPrice())
                .reduce(new BigDecimal(0), BigDecimal::add);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
        orderItem.setOrder(null);
    }

}
