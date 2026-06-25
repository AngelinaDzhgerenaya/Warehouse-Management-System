package org.website.myproject.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.website.myproject.enums.OperationType;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockOperationDto {
    private Long id;

    @NotNull(message = "Продукт обязателен")
    private Long productId;

    @NotNull(message = "Склад обязателен")
    private Long warehouseId;

    @NotNull(message = "Тип операции обязателен")
    private OperationType type;

    @NotNull(message = "Количество обязательно")
    @Positive(message = "Количество должно быть больше 0")
    private Integer quantity;

    private LocalDateTime createdAt;
}

