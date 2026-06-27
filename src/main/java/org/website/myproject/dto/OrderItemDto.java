package org.website.myproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long id;

    @NotNull(message = "Продукт обязателен")
    private Long productId;

    private Long stockId;

    @NotNull(message = "Количество обязательно")
    @Positive(message = "Количество должно быть больше 0")
    private Integer quantity;

    private BigDecimal priceAtMoment;

    private BigDecimal totalPrice;
}
