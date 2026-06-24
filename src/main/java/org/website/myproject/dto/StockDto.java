package org.website.myproject.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {
    private Long id;

    @NotNull(message = "Продукт обязателен")
    private Long productId;

    @NotNull(message = "Склад обязателен")
    private Long warehouseId;

    @NotNull(message = "Количество обязательно")
    @Positive(message = "Количество должно быть больше 0")
    private Integer quantity;

    private Integer reservedQuantity;
}
