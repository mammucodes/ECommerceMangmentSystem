package com.example.ecommercemangmentsystem;

import com.example.ecommercemangmentsystem.Service.CartService;
import com.example.ecommercemangmentsystem.model.CartItem;
import com.example.ecommercemangmentsystem.model.Product;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CartServiceTest {
     CartService  cartService = new CartService();


    @Test
    void addToCart_AddNewProduct_ShouldAddToCart() {
       // Product product = new Product(1, "Test Product", 10.0);
        Product product = new Product("lenova.",600,"",10);
        product.setId(1);
        cartService.addToCart("user1", product, 2);

        List<CartItem> cart = cartService.getCart("user1");
        assertEquals(1, cart.size());
        assertEquals(product, cart.get(0).getProduct());
        assertEquals(2, cart.get(0).getQuantity());
    }

    @Test
    void addToCart_AddExistingProduct_ShouldUpdateQuantity() {
       // Product product = new Product(1, "Test Product", 10.0);
        Product product = new Product("lenova.",600,"",10);
        cartService.addToCart("user1", product, 2);
        cartService.addToCart("user1", product, 3);

        List<CartItem> cart = cartService.getCart("user1");
        assertEquals(1, cart.size());
        assertEquals(5, cart.get(0).getQuantity());
    }

    @Test
    void addToCart_AddMultipleProducts_ShouldAddAllProducts() {
       // Product product1 = new Product(1, "Product 1", 10.0);
        Product product1 = new Product("lenova.",600,"",10);
        Product product2 = new Product("google.",500,"",20);
       // Product product2 = new Product(2, "Product 2", 20.0);

        cartService.addToCart("user1", product1, 2);
        cartService.addToCart("user2", product2, 1);

        List<CartItem> cart = cartService.getCart("user1");
        assertEquals(1, cart.size());
    }

    @Test
    void removeFromCart_RemoveExistingProduct_ShouldRemoveProduct() {
       // Product product = new Product(1, "Test Product", 10.0);
        Product product = new Product("lenova.",600,"",10);
        product.setId(1);
        cartService.addToCart("user1", product, 2);

        cartService.removeFromCart("user1", 1);

        List<CartItem> cart = cartService.getCart("user1");
        assertTrue(cart.isEmpty());
    }

    @Test
    void removeFromCart_RemoveNonExistentProduct_ShouldNotThrowError() {
        cartService.removeFromCart("user1", 1);
        List<CartItem> cart = cartService.getCart("user1");
        assertTrue(cart.isEmpty());
    }

    @Test
    void updateItemQuantity_UpdateExistingProduct_ShouldChangeQuantity() {
      //  Product product = new Product(1, "Test Product", 10.0);
        Product product = new Product("lenova.",600,"",10);
        cartService.addToCart("user1", product, 2);

        cartService.updateItemQuantity("user1", 0, 5);

        List<CartItem> cart = cartService.getCart("user1");
        assertEquals(5, cart.get(0).getQuantity());
    }

    @Test
    void updateItemQuantity_UpdateNonExistentProduct_ShouldNotThrowError() {
        cartService.updateItemQuantity("user1", 1, 5);
        List<CartItem> cart = cartService.getCart("user1");
        assertTrue(cart.isEmpty());
    }

    @Test
    void calculateCartTotal_ShouldReturnCorrectTotal() {
        //Product product1 = new Product(1, "Product 1", 10.0);
       // Product product2 = new Product(2, "Product 2", 20.0);
        Product product1 = new Product("lenova.",600,"",10);
        Product product2 = new Product("google.",500,"",20);

        cartService.addToCart("user1", product1, 2);
        cartService.addToCart("user1", product2, 2);

        double total = cartService.calculateCartTotal("user1");
        assertEquals(2200, total, 0.01);
    }

    @Test
    void calculateCartTotal_EmptyCart_ShouldReturnZero() {
        double total = cartService.calculateCartTotal("user1");
        assertEquals(0.0, total, 0.01);
    }

    @Test
    void getCart_EmptyCart_ShouldReturnEmptyList() {
        List<CartItem> cart = cartService.getCart("user1");
        assertTrue(cart.isEmpty());
    }

    @Test
    void getCart_PopulatedCart_ShouldReturnCartItems() {
       // Product product = new Product(1, "Test Product", 10.0);
        Product product = new Product("lenova.",600,"",10);
        cartService.addToCart("user1", product, 2);

        List<CartItem> cart = cartService.getCart("user1");
        assertEquals(1, cart.size());
        assertEquals(product, cart.get(0).getProduct());
    }

    @Test
    void clearCart_PopulatedCart_ShouldRemoveAllItems() {
      //  Product product = new Product(1, "Test Product", 10.0);
        Product product = new Product("lenova.",600,"",10);
        cartService.addToCart("user1", product, 2);

        cartService.clearCart("user1");

        List<CartItem> cart = cartService.getCart("user1");
        assertTrue(cart.isEmpty());
    }

    @Test
    void clearCart_EmptyCart_ShouldNotThrowError() {
        cartService.clearCart("user1");
        List<CartItem> cart = cartService.getCart("user1");
        assertTrue(cart.isEmpty());
    }
}

