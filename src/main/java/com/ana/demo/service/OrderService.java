package com.ana.demo.service;

import com.ana.demo.model.*;
import com.ana.demo.repository.OrderRepository;
import com.ana.demo.repository.ProductRepository;
import com.ana.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get an order by ID
    public Order getOrderById(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order with ID " + id + " not found"));
    }

    // Get all orders for a specific user (using email)
    public List<Order> getOrdersByUserEmail(String userEmail) {
        return orderRepository.findByUserEmail(userEmail);
    }

    // Create a new order
    public Order createOrder(OrderCreateDTO orderDTO) {
        // Resolve user by email
        User user = userRepository.findByEmail(orderDTO.getUserEmail())
                .orElseThrow(() -> new RuntimeException("User with email " + orderDTO.getUserEmail() + " not found"));

        // Create the order
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setStatus(orderDTO.getStatus());

        // Add products to the order
        for (OrderProductCreateDTO orderProductDTO : orderDTO.getOrderProducts()) {
            // Resolve product by name
            Product product = productRepository.findByName(orderProductDTO.getProductName())
                    .orElseThrow(() -> new RuntimeException("Product with name " + orderProductDTO.getProductName() + " not found"));

            // Create the order-product association
            order.addOrderProduct(createOrderProduct(order, product, orderProductDTO));
        }

        // Save the order
        return orderRepository.save(order);
    }

    // Update an existing order
    public Order updateOrder(UUID id, Order updatedOrder) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order with ID " + id + " not found"));

        if (updatedOrder.getTotalPrice() != null) {
            existingOrder.setTotalPrice(updatedOrder.getTotalPrice());
        }
        if (updatedOrder.getStatus() != null) {
            existingOrder.setStatus(updatedOrder.getStatus());
        }

        return orderRepository.save(existingOrder);
    }

    // Delete an order
    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }

    // Helper method to create an OrderProduct
    private OrderProduct createOrderProduct(Order order, Product product, OrderProductCreateDTO orderProductDTO) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        orderProduct.setQuantity(orderProductDTO.getQuantity());
        orderProduct.setPrice(orderProductDTO.getPrice());
        return orderProduct;
    }
}