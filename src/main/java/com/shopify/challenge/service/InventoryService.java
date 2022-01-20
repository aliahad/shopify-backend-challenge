package com.shopify.challenge.service;

import com.shopify.challenge.entity.Item;
import com.shopify.challenge.exception.ItemNotFoundException;
import com.shopify.challenge.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public void deleteItem(Long id) {
        inventoryRepository.deleteById(id);
    }

    public ResponseEntity<Resource> getAllItemsAsCsv() {
        String[] csvHeader = Arrays.stream(Item.class.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList())
                .toArray(String[]::new);

        List<List<String>> csvBody = new ArrayList<>();

        inventoryRepository.findAll().forEach(
                item -> csvBody.add(
                        Arrays.asList(
                                item.getId().toString(),
                                item.getName(),
                                item.getPrice().toString()
                        )
                ));

        ByteArrayInputStream byteArrayOutputStream;

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(
                    new PrintWriter(out),
                    CSVFormat.DEFAULT.withHeader(csvHeader)
            );

            for (List<String> record : csvBody) {
                csvPrinter.printRecord(record);
            }

            csvPrinter.flush();

            byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        InputStreamResource fileInputStream = new InputStreamResource(byteArrayOutputStream);

        String csvFileName = "items.csv";

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFileName);
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

        return new ResponseEntity<>(
                fileInputStream,
                headers,
                HttpStatus.OK
        );
    }

}
