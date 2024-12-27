package com.example.ecommercemangmentsystem.Service;

import com.example.ecommercemangmentsystem.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    int idCounter = 1;
    private Map<String, Product> productCatalog;
    Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductService() {
        this.productCatalog = new HashMap();
    }

    //this method takes product id and product name as input and add them to list of prodcutcatlog
//this method requires a valid input object we sre not validating null object if passed
    // returns true if product added succesfully
    //or return false if not added successfuly for any reason
    // if price changes  for same product we are adding latestprice according to our  existing code
    public boolean addProduct(Product product) {

        try {
            if (productCatalog.containsKey(product.getName().toLowerCase())) {
                int quantity = product.getQuantity();
                int existingQunatity = productCatalog.get(product.getName().toLowerCase()).getQuantity();
                int newQuantity = quantity + existingQunatity;
                product.setQuantity(newQuantity);
                product.setId(idCounter);
                productCatalog.put(product.getName().toLowerCase(), product);
                return true;
            } else {
                product.setId(idCounter++);
                productCatalog.put(product.getName().toLowerCase(), product);
                return true;
            }
        } catch (Exception ex ){
           logger.info("exception occurred"+ex.getMessage());
            return false;
        }

    }



// write now I dont need to write update() we can do it same in add .if in future if I change the code logic in addProduct .I will
  //  write update() functionality
    public boolean updateProductName(String oldproductName,  String newProductName ){
        return false;
    }
    //this methods takes input as prodcut id and quantity
    public boolean   deleteProduct(int id ,int quantity) {
        Iterator<Map.Entry<String, Product>> iterator = productCatalog.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Product> entry = iterator.next();
            Product product = entry.getValue(); // Get the Product object
            int productId = product.getId();    // Get the product ID (int)
            if (productId == id) {              // Compare the int values using ==
                if (product.getQuantity() >= quantity) { // Check if sufficient quantity exists
                    product.setQuantity(product.getQuantity() - quantity); // Reduce quantity
                    if (product.getQuantity() == 0) {
                        iterator.remove(); // Remove the product if quantity becomes 0
                    }
                    logger.info("Successfully performed the delete ");
                    return true; //
                } else {
                    logger.info("Insufficient quantity to delete.");
                    return false; // Unable to delete due to insufficient quantity
                }
            }
        }
        logger.info("failed to delte data problem form input  passed or something else");
        return false;
    }
    public Product searchProductsByName(String name) {
        return productCatalog.get(name.toLowerCase());
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productCatalog.values());
    }
    }

