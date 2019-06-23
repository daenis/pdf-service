package com.api.order.status;

import com.api.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDER_STATUSES")
@Getter
@Setter
public class OrderStatus implements Serializable {

    private static final long serialVersionUID = 74382478923L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ORDER_STATUS_ID")
    private Integer orderStatusId;

    @Column(name = "NAME")
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderStatus", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

}
