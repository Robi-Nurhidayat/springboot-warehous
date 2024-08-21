package com.inventory.inventory.controller;

import com.inventory.inventory.constant.BorrowingConstants;
import com.inventory.inventory.constant.UserConstants;
import com.inventory.inventory.dto.BorrowingDTO;
import com.inventory.inventory.dto.Response;
import com.inventory.inventory.entity.User;
import com.inventory.inventory.service.IBorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrows")
@RequiredArgsConstructor
public class BorrowingController {

    private final IBorrowingService borrowingService;

    @PostMapping
    public ResponseEntity<Response> createBorrow(@RequestBody BorrowingDTO borrowingDTO) {
        borrowingService.borrowTool(borrowingDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(BorrowingConstants.STATUS_201,BorrowingConstants.MESSAGE_201,""));

    }

    @DeleteMapping
    public ResponseEntity<Response> returnBorrow(@RequestParam("borrowId") Long borrowId) {
       borrowingService.returnBorrow(borrowId);
       return ResponseEntity.status(HttpStatus.OK).body(new Response(BorrowingConstants.STATUS_200,BorrowingConstants.MESSAGE_200,""));

    }
}
