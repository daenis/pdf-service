package com.api.order.item;

import com.api.menu.MenuItem;
import com.api.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ORDER_ITEMS")
@Getter
@Setter
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 893472894723L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ORDER_ITEM_ID")
    private Integer orderItemId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MENU_ITEM_ID")
    private MenuItem menuItem;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

}
