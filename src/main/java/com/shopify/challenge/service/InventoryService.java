package com.shopify.challenge.service;

import com.shopify.challenge.entity.Item;
import com.shopify.challenge.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public List<Item> getAllItems() {
        return inventoryRepository.findAll();
    }

}
