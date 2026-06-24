package org.website.myproject.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDto {
    private Long id;

    @Size(min = 1, max = 50, message = "Название склада пустое или слишком длинное")
    private String name;

    @Size(min = 1, max = 50, message = "Адрес склада пустой или слишком длинный")
    private String address;
}
