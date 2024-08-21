package com.inventory.inventory.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Borrow",
        description = "Schema to hold Borrow information"
)

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingDTO {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
}
