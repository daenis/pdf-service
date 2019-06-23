package com.api.menu;

import com.api.order.item.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MENU_ITEMS")
@Getter
@Setter
@EqualsAndHashCode
public class MenuItem implements Serializable {

    private static final long serialVersionUID = 6734246237864L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "MENU_ITEM_ID")
    private Integer menuItemId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MENU_ITEM_TYPE_ID")
    private MenuItemType menuItemType;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private BigDecimal price;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuItem", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

}
