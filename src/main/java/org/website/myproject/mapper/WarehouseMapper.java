package org.website.myproject.mapper;

import org.springframework.stereotype.Component;
import org.website.myproject.dto.WarehouseDto;
import org.website.myproject.entity.Warehouse;

@Component
public class WarehouseMapper {
    public WarehouseDto toDto(Warehouse warehouse) {

        return new WarehouseDto(
                warehouse.getId(),
                warehouse.getName(),
                warehouse.getAddress()
        );
    }
}
