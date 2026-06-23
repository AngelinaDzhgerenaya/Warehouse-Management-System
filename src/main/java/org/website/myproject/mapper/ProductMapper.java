package org.website.myproject.mapper;

import org.springframework.stereotype.Component;
import org.website.myproject.dto.ProductDto;
import org.website.myproject.entity.Product;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product) {

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getId()
        );
    }
}
