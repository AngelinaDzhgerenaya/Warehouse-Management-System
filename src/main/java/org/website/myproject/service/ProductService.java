package org.website.myproject.service;



import org.website.myproject.entity.Product;

import java.util.List;

public interface ProductService {

    Product create(Product product);

    Product findById(Long id);

    List<Product> findAll();

    Product update(Long id, Product product);

    void delete(Long id);
}
