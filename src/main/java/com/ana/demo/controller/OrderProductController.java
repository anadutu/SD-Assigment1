package com.ana.demo.controller;

import com.ana.demo.model.OrderProduct;
import com.ana.demo.service.OrderProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/order-products")
public class OrderProductController {

    private final OrderProductService orderProductService;

    // Get all order products for a specific order
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderProduct>> getOrderProductsByOrderId(@PathVariable UUID orderId) {
        return ResponseEntity.ok(orderProductService.getOrderProductsByOrderId(orderId));
    }

    // Get all order products for a specific product (using product name)
    @GetMapping("/product/{productName}")
    public ResponseEntity<List<OrderProduct>> getOrderProductsByProductName(@PathVariable String productName) {
        return ResponseEntity.ok(orderProductService.getOrderProductsByProductName(productName));
    }

    // Add a product to an order
    @PostMapping
    public ResponseEntity<OrderProduct> addProductToOrder(
            @RequestParam UUID orderId,
            @RequestParam String productName,
            @RequestParam Integer quantity,
            @RequestParam Double price) {
        return ResponseEntity.ok(orderProductService.addProductToOrder(orderId, productName, quantity, price));
    }

    // Update an existing order product
    @PutMapping("/{id}")
    public ResponseEntity<OrderProduct> updateOrderProduct(
            @PathVariable UUID id,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) Double price) {
        return ResponseEntity.ok(orderProductService.updateOrderProduct(id, quantity, price));
    }

    // Delete an order product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderProduct(@PathVariable UUID id) {
        orderProductService.deleteOrderProduct(id);
        return ResponseEntity.noContent().build();
    }
}