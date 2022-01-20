package com.shopify.challenge.service;

import com.shopify.challenge.entity.Item;
import com.shopify.challenge.exception.ItemNotFoundException;
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

    public Item getItemById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Item addItem(Item item) {
        return inventoryRepository.save(item);
    }

    public Item updateItem(Item newItem, Long id) {
        return inventoryRepository.findById(id)
                .map(item -> {
                    item.setName(newItem.getName());
                    item.setPrice(newItem.getPrice());
                    return inventoryRepository.save(item);
                })
                .orElseGet(() -> {
                    newItem.setId(id);
                    return inventoryRepository.save(newItem);
                });
    }

}
