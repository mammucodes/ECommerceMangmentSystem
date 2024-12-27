package com.example.ecommercemangmentsystem.Service;


import com.example.ecommercemangmentsystem.model.CartItem;
import com.example.ecommercemangmentsystem.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    private Map<String, List<Order>> userOrders = new HashMap<>(); // Order history

    @Autowired
    private CartService cartService; // Dependency on CartService

    // Place an order for the user
    public void placeOrder(String user) {
        // Step 1: Get items from the user's cart
        List<CartItem> cartItems = cartService.getCart(user);

        if (cartItems.isEmpty()) {
            throw new IllegalStateException("Cart is empty. Cannot place order.");
        }

        // Step 2: Calculate the total price
        double totalPrice = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        // Step 3: Create an Order object
        Order newOrder = new Order(cartItems, totalPrice, new Date());

        // Step 4: Add the order to the user's order history
        userOrders.computeIfAbsent(user, k -> new ArrayList<>()).add(newOrder);

        // Step 5: Clear the user's cart
        cartService.clearCart(user);
    }

    // Retrieve order history for a specific user
    public List<Order> getOrderHistory(String user) {
        return userOrders.getOrDefault(user, Collections.emptyList());
    }
}

