package com.inventory.inventory.controller;

import com.inventory.inventory.constant.UserConstants;
import com.inventory.inventory.dto.ErrorResponseDto;
import com.inventory.inventory.dto.Response;
import com.inventory.inventory.entity.User;
import com.inventory.inventory.service.IUserService;
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
        name = "CRUD REST APIs for Users",
        description = "CRUD REST APIs in Users to CREATE, UPDATE AND DELETE Users"
)
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @Operation(
            summary = "Get all users REST API",
            description = "REST API to Get all users"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
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
    @GetMapping
    public ResponseEntity<Response> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(new Response(UserConstants.STATUS_200,UserConstants.MESSAGE_200,allUsers));

    }


    @Operation(
            summary = "Create users REST API",
            description = "REST API to create new users"
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
    public ResponseEntity<Response> createUser(@RequestBody User user) {
        User newUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(UserConstants.STATUS_201,UserConstants.MESSAGE_201,newUser));

    }


    @Operation(
            summary = "update users REST API",
            description = "REST API to update users"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
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
    @PutMapping
    public ResponseEntity<Response> updateUser(@RequestBody User user) {
        User updateUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(UserConstants.STATUS_200,UserConstants.MESSAGE_200,updateUser));

    }

    @Operation(
            summary = "Delete users REST API",
            description = "REST API to delete users by id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
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
    public ResponseEntity<Response> deleteUser(@RequestParam("userId") Long userId) {
        boolean isDeleted = userService.deleteUser(userId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(UserConstants.STATUS_200,UserConstants.MESSAGE_200,""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response(UserConstants.STATUS_417,UserConstants.MESSAGE_417_DELETE,""));

    }
}
