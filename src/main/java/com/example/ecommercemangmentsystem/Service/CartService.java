package com.example.ecommercemangmentsystem.Service;

import com.example.ecommercemangmentsystem.model.CartItem;
import com.example.ecommercemangmentsystem.model.Product;
import org.springframework.stereotype.Service;


    import java.util.*;
    @Service
    public class CartService {
        // Map to store each user's cart
        private Map<String, List<CartItem>> userCarts;

        public CartService(){
           this.userCarts = new HashMap<>();

        }

        // Add an item to the user's cart
        public void addToCart(String user, Product product, int quantity) {
            // Fetch the user's cart or create a new one if it doesn't exist
            List<CartItem> cart = userCarts.computeIfAbsent(user, k -> new ArrayList<>());

            // Check if the product is already in the cart
            Optional<CartItem> existingItem = cart.stream()
                    .filter(item -> item.getProduct().getId() == product.getId())
                    .findFirst();

            if (existingItem.isPresent()) {
                // If product exists, update the quantity
                existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
            } else {
                // If product doesn't exist, add it to the cart
                cart.add(new CartItem(product, quantity));
            }
        }

        // Remove an item from the user's cart
        public void removeFromCart(String user, int productId) {
            List<CartItem> cart = userCarts.get(user);
            if (cart != null) {
                cart.removeIf(item -> item.getProduct().getId() == productId);
            }
        }

        // Update the quantity of an item in the cart
        public void updateItemQuantity(String user, int productId, int newQuantity) {
            List<CartItem> cart = userCarts.get(user);
            if (cart != null) {
                cart.stream()
                        .filter(item -> item.getProduct().getId() == productId)
                        .findFirst()
                        .ifPresent(item -> item.setQuantity(newQuantity));
            }
        }

        // Get the total price of the user's cart
        public double calculateCartTotal(String user) {
            List<CartItem> cart = userCarts.getOrDefault(user, Collections.emptyList());
            return cart.stream()
                    .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                    .sum();
        }

        // Get the cart items for a specific user
        public List<CartItem> getCart(String user) {
            return userCarts.getOrDefault(user, Collections.emptyList());
        }

        // Clear the user's cart
        public void clearCart(String user) {
            userCarts.remove(user);
        }
    }


