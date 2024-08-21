package com.inventory.inventory.service;

import com.inventory.inventory.dto.BorrowingDTO;
import com.inventory.inventory.entity.Borrowing;

public interface IBorrowingService {

    void borrowTool(BorrowingDTO borrowingDTO);
    void returnBorrow(Long borrowId);

}
