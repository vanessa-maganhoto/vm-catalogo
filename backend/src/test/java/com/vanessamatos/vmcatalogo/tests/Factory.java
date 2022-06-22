package com.vanessamatos.vmcatalogo.tests;

import com.vanessamatos.vmcatalogo.dto.ProductDTO;
import com.vanessamatos.vmcatalogo.entities.Category;
import com.vanessamatos.vmcatalogo.entities.Product;

import java.time.Instant;

public class Factory {

    public static Product createProdcut(){
        Product product = new Product(1L, "Phone", "Good Phone", 800.0, "http://img.com/img.png", Instant.parse("2020-10-20T03:00:00Z"));
        product.getCategories().add(createCategory());
        return product;
    }

    public static ProductDTO createProductDTO(){
        Product product = createProdcut();
        return new ProductDTO(product, product.getCategories());
    }

    public static Category createCategory(){
        return new Category(2L, "Eletronics");
    }
}
