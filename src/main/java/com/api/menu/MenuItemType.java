package com.api.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MENU_ITEM_TYPES")
@Getter
@Setter
public class MenuItemType implements Serializable {

    private static final long serialVersionUID = 234238908L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ITEM_TYPE_ID")
    private Integer menuItemTypeId;

    @Column(name = "NAME")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuItemType", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MenuItem> menuItems = new ArrayList<>();

}
