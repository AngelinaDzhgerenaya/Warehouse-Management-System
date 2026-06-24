package org.website.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.website.myproject.dto.ProductDto;
import org.website.myproject.entity.Category;
import org.website.myproject.entity.Product;
import org.website.myproject.entity.Stock;
import org.website.myproject.exceptions.ConflictException;
import org.website.myproject.exceptions.NotFoundException;
import org.website.myproject.mapper.ProductMapper;
import org.website.myproject.repository.CategoryRepository;
import org.website.myproject.repository.ProductRepository;
import org.website.myproject.repository.StockRepository;
import org.website.myproject.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private final CategoryRepository categoryRepository;

    private final StockRepository stockRepository;


    @Override
    public ProductDto create(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() ->
                new NotFoundException("Такой категории не существует"));
        Product product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .category(category)
                .build();

        Product saved = productRepository.save(product);
        return productMapper.toDto(saved);
    }
    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Такого продукта не существует"));
        return productMapper.toDto(product);
    }
    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductDto update(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Такого продукта не существует"));
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() ->
                new NotFoundException("Такой категории не существует"));
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);

        Product saved = productRepository.save(product);
        return productMapper.toDto(saved);
    }

    @Override
    public String delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                        new NotFoundException("Такого продукта не существует"));
        List<Stock> stocks = stockRepository.findByProduct(product);
        if (!stocks.isEmpty()) {
            throw new ConflictException("Этот продукт ещё есть на складе");
        }
        productRepository.deleteById(id);
        return "Продукт удалён";
    }


}
