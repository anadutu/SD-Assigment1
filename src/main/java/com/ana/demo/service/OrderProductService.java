package com.ana.demo.service;

import com.ana.demo.model.Order;
import com.ana.demo.model.OrderProduct;
import com.ana.demo.model.Product;
import com.ana.demo.repository.OrderProductRepository;
import com.ana.demo.repository.OrderRepository;
import com.ana.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderProductService(OrderProductRepository orderProductRepository,
                               OrderRepository orderRepository,
                               ProductRepository productRepository) {
        this.orderProductRepository = orderProductRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // Get all order products for a specific order
    public List<OrderProduct> getOrderProductsByOrderId(UUID orderId) {
        return orderProductRepository.findByOrderId(orderId);
    }

    // Get all order products for a specific product (using product name)
    public List<OrderProduct> getOrderProductsByProductName(String productName) {
        return orderProductRepository.findByProductName(productName);
    }

    // Add a product to an order
    public OrderProduct addProductToOrder(UUID orderId, String productName, Integer quantity, Double price) {
        // Resolve the order by ID
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order with ID " + orderId + " not found"));

        // Resolve the product by name
        Product product = productRepository.findByName(productName)
                .orElseThrow(() -> new RuntimeException("Product with name " + productName + " not found"));

        // Create the order-product association
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        orderProduct.setQuantity(quantity);
        orderProduct.setPrice(price);

        return orderProductRepository.save(orderProduct);
    }

    // Update an existing order product
    public OrderProduct updateOrderProduct(UUID id, Integer quantity, Double price) {
        OrderProduct existingOrderProduct = orderProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderProduct with ID " + id + " not found"));

        if (quantity != null) {
            existingOrderProduct.setQuantity(quantity);
        }
        if (price != null) {
            existingOrderProduct.setPrice(price);
        }

        return orderProductRepository.save(existingOrderProduct);
    }

    // Delete an order product
    public void deleteOrderProduct(UUID id) {
        orderProductRepository.deleteById(id);
    }
}