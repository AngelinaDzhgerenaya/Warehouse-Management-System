package org.website.myproject.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.website.myproject.entity.Category;
import org.website.myproject.repository.CategoryRepository;

import java.util.Objects;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public DataLoader(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {

        Category category = new Category();
        category.setName("Телефоны");

        if (Objects.equals(categoryRepository.findById(1L).orElseThrow().getName(), category.getName()))
        {
            System.out.println("Категория уже сохранена в БД");
        }
        else{
            categoryRepository.save(category);

            System.out.println("Категория сохранена в БД");
        }

    }
}
