package org.website.myproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;

    @Size(min = 1, max = 50, message = "Название продукта пустое или слишком длинное")
    private String name;

    @Size(min = 1, max = 50, message = "Описание продукта пустое или слишком длинное")
    private String description;

    @NotNull(message = "Цена обязательна")
    @Positive(message = "Цена должна быть больше 0")
    private BigDecimal price;

    @NotNull(message = "Категория обязательна")
    private Long categoryId;
}
