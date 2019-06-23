package com.api.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuItemServiceTest {

    private String name;
    private MenuItem menuItem;

    @Mock
    private MenuItemRepository menuItemRepository;

    private MenuItemService menuItemService;

    @BeforeEach
    void init() {
        name = "Hot Dog";
        menuItem = new MenuItem();

        menuItemService = new MenuItemService(menuItemRepository);
    }

    @Test
    void testFindByName() {
        when(menuItemRepository.findByName(name)).thenReturn(menuItem);

        MenuItem returnedMenuItem = menuItemService.findByName(name);

        assertEquals(menuItem, returnedMenuItem, "There was an error returning the menu item.");
    }

}
