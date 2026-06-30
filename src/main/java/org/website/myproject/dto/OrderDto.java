package org.website.myproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.website.myproject.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderDto {
    private Long id;

    @NotNull(message = "Товары обязательны")
    @Size(min = 1, message = "В заказе должен быть хотя бы 1 товар")
    private List<OrderItemDto> items;

    private OrderStatus status;

    private BigDecimal totalAmount;

    private LocalDateTime createdAt;

}
