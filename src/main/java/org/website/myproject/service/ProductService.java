package org.website.myproject.service;



import org.website.myproject.dto.ProductDto;


import java.util.List;

public interface ProductService {

    ProductDto create(ProductDto product);

    ProductDto findById(Long id);

    List<ProductDto> findAll();

    ProductDto update(Long id, ProductDto product);

    String delete(Long id);
}
