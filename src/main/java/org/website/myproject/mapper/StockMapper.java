package org.website.myproject.mapper;

import org.springframework.stereotype.Component;
import org.website.myproject.dto.StockDto;
import org.website.myproject.dto.WarehouseDto;
import org.website.myproject.entity.Stock;
import org.website.myproject.entity.Warehouse;

@Component
public class StockMapper {
    public StockDto toDto(Stock stock) {

        return new StockDto(
                stock.getId(),
                stock.getProduct().getId(),
                stock.getWarehouse().getId(),
                stock.getQuantity(),
                stock.getReservedQuantity()
        );
    }
}
