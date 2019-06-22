package com.api;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "MENU_ITEMS")
@Getter
@Setter
public class MenuItem implements Serializable {

    private static final long serialVersionUID = 6734246237864L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ITEM_ID")
    private Integer menuItemId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MENU_ITEM_TYPE_ID")
    private MenuItemType menuItemType;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private BigDecimal price;

}
