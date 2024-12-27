package com.example.ecommercemangmentsystem;

import com.example.ecommercemangmentsystem.Service.ProductService;
import com.example.ecommercemangmentsystem.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    ProductService service = new ProductService();
  //  private Map<String, Product> productCatalog=null; ;


    @Test
    public void addProductTest(){
        Product lenova = new Product("Lenova",500,"Electronics",1);
       // boolean excepted = true;
      boolean actual =   service.addProduct(lenova);

        assertTrue(actual,"failed  to addproduct");

    }
    @Test
    public void addProductTestifSameProductPassed(){
        Product lenova = new Product("Lenova",500,"Electronics",1);
        // boolean excepted = true;
        boolean actual =   service.addProduct(lenova);

        assertTrue(actual,"failed  to addproduct");

    }
    @Test
    public void addProductTestifnullpassed(){
        boolean actual =   service.addProduct(null);
        assertFalse(actual,"failed  to addproduct");
    }
    @Test
    void testDeleteProductWithSufficientQuantity() {
        Product product = new Product("lenova.",600,"",10);
        product.setId(1);
        service.addProduct(product);
        //productCatalog.put("lenova", product);
      //  System.out.println("Catalog Contents: " + productCatalog);

        System.out.println("Product ID: " + product.getId());
        System.out.println("Product Quantity: " + product.getQuantity());

        System.out.println(product);
        assertTrue(service.deleteProduct(1, 5));
        assertEquals(5, product.getQuantity());
    }

    @Test
    void testDeleteProductWithInsufficientQuantity() {
        Product product = new Product("Product1",600,"",5);
        service.addProduct(product);
       // productCatalog.put("Product1", product);

        assertFalse(service.deleteProduct(1, 10));
        assertEquals(5, product.getQuantity());
    }

    @Test
    void testDeleteProductDoesNotExist() {
        Product product = new Product("Pixel",800,"",15);
        service.addProduct(product);
      //  productCatalog.put("Product1", product);
        assertFalse(service.deleteProduct(2, 5));
    }

    @Test
    void testDeleteProductQuantityBecomesZero() {
        Product product = new Product("Lenova",800,"", 5);
        //productCatalog.put("Lenova", product);
        product.setId(1);
        service.addProduct(product);
        assertTrue(service.deleteProduct(1, 5));
        System.out.println(product.getQuantity());
        assertEquals(0,product.getQuantity());
        //assertFalse(service.containsKey("Product1"));
    }

    @Test
    void testDeleteProductEmptyCatalog() {
        assertFalse(service.deleteProduct(1, 5));
    }

    @Test
    void testDeleteProductNegativeQuantity() {
        Product product = new Product( "Product1",500,"" ,1);
        product.setId(1);
        service.addProduct(product);
      //  productCatalog.put("Product1", product);
        assertFalse(service.deleteProduct(1, 5));
    }

    @Test
    void testDeleteProductIdDoesNotMatch() {
        Product product = new Product( "Product1",200,"", 10);
       // productCatalog.put("Product1", product);
        service.addProduct(product);
        assertFalse(service.deleteProduct(2, 5));
    }


    @Test
    public void getQuantityTest(){
        Product lenova = new Product("Lenova",500,"Electronics",2);
        Product lenov = new Product("Lenova",600,"Electronics",1);
        service.addProduct(lenova);
        service.addProduct(lenov);
       List<Product > actual= service.getAllProducts();

       assertEquals(1, actual.size());

        assertEquals(3, actual.get(0).getQuantity());
        System.out.println(actual.size());
       for(Product p:actual){
           System.out.print(p);
       }
    }
}
