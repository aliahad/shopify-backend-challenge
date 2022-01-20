package com.shopify.challenge.controller;

import com.shopify.challenge.entity.Item;
import com.shopify.challenge.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return inventoryService.getAllItems();
    }

}
