package com.inventory.inventory.controller;

import com.inventory.inventory.constant.BorrowingConstants;
import com.inventory.inventory.constant.UserConstants;
import com.inventory.inventory.dto.BorrowingDTO;
import com.inventory.inventory.dto.ErrorResponseDto;
import com.inventory.inventory.dto.Response;
import com.inventory.inventory.entity.User;
import com.inventory.inventory.service.IBorrowingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Borrow",
        description = "CRUD REST APIs in product to CREATE, FIND, UPDATE AND DELETE Users"
)

@RestController
@RequestMapping("/api/borrows")
@RequiredArgsConstructor
public class BorrowingController {

    private final IBorrowingService borrowingService;


    @Operation(
            summary = "Create borrow REST API",
            description = "REST API to create new borrow"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping
    public ResponseEntity<Response> createBorrow(@RequestBody BorrowingDTO borrowingDTO) {
        borrowingService.borrowTool(borrowingDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(BorrowingConstants.STATUS_201,BorrowingConstants.MESSAGE_201,""));

    }


    @Operation(
            summary = "Returning borrow REST API",
            description = "REST API to borrow"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping
    public ResponseEntity<Response> returnBorrow(@RequestParam("borrowId") Long borrowId) {
       borrowingService.returnBorrow(borrowId);
       return ResponseEntity.status(HttpStatus.OK).body(new Response(BorrowingConstants.STATUS_200,BorrowingConstants.MESSAGE_200,""));

    }
}
