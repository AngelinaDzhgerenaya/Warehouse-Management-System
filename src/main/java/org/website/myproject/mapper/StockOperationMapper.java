package org.website.myproject.mapper;

import org.springframework.stereotype.Component;
import org.website.myproject.dto.StockOperationDto;
import org.website.myproject.entity.StockOperation;

@Component
public class StockOperationMapper {
    public StockOperationDto toDto(StockOperation operation) {

        return new StockOperationDto(
                operation.getId(),
                operation.getProduct().getId(),
                operation.getWarehouse().getId(),
                operation.getType(),
                operation.getQuantity(),
                operation.getCreatedAt()
        );
    }
}
