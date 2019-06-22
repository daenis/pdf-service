package com.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Column(name = "ORDER_TIME")
    private LocalDateTime orderTime;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MENU_ITEM_ORDER",
        joinColumns = @JoinColumn(name = "MENU_ITEM_ID"),
        inverseJoinColumns = @JoinColumn(name = "ORDER_ID")
    )
    private List<MenuItem> menuItems = new ArrayList<>();

    private void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
        menuItem.getOrders().add(this);
    }

    private void removeMenuItem(MenuItem menuItem) {
        menuItems.remove(menuItem);
        menuItem.getOrders().remove(this);
    }

}
