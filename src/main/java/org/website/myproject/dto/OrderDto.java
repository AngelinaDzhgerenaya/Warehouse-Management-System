package org.website.myproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.website.myproject.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderDto {
    private Long id;

    @NotNull(message = "Товары обязательны")
    private List<OrderItemDto> items;

    private OrderStatus status;

    private LocalDateTime createdAt;

}
