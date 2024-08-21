package com.inventory.inventory.controller;

import com.inventory.inventory.constant.UserConstants;
import com.inventory.inventory.dto.Response;
import com.inventory.inventory.entity.User;
import com.inventory.inventory.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping
    public ResponseEntity<Response> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(new Response(UserConstants.STATUS_200,UserConstants.MESSAGE_200,allUsers));

    }

    @GetMapping("/search")
    public ResponseEntity<Response> findUserByName(@RequestParam("userId") Long userId) {
        User user = userService.findById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(UserConstants.STATUS_200,UserConstants.MESSAGE_200,user));

    }

    @PostMapping
    public ResponseEntity<Response> createUser(@RequestBody User user) {
        User newUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(UserConstants.STATUS_201,UserConstants.MESSAGE_201,newUser));

    }

    @PutMapping
    public ResponseEntity<Response> updateUser(@RequestBody User user) {
        User updateUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(UserConstants.STATUS_200,UserConstants.MESSAGE_200,updateUser));

    }

    @DeleteMapping
    public ResponseEntity<Response> deleteUser(@RequestParam("userId") Long userId) {
        boolean isDeleted = userService.deleteUser(userId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(UserConstants.STATUS_200,UserConstants.MESSAGE_200,""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response(UserConstants.STATUS_417,UserConstants.MESSAGE_417_DELETE,""));

    }
}
