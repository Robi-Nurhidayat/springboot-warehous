package com.inventory.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingDTO {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
}
