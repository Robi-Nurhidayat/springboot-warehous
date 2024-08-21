package com.inventory.inventory.service.impl;

import com.inventory.inventory.entity.User;
import com.inventory.inventory.exception.ResourceNotFoundException;
import com.inventory.inventory.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() {
        // Arrange
        User user1 = new User(1L, "User 1", "ROLE_USER");
        User user2 = new User(2L, "User 2", "ROLE_ADMIN");
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Act
        List<User> allUsers = userService.getAllUsers();

        // Assert
        assertNotNull(allUsers);
        assertEquals(2, allUsers.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void save_NewUser_ShouldSaveAndReturnUser() {
        // Arrange
        User newUser = new User(null, "User 3", "ROLE_USER");
        when(userRepository.save(newUser)).thenReturn(newUser);

        // Act
        User savedUser = userService.save(newUser);

        // Assert
        assertNotNull(savedUser);
        assertEquals("User 3", savedUser.getName());
        assertEquals("ROLE_USER", savedUser.getRole());
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void save_ExistingUser_ShouldUpdateAndReturnUser() {
        // Arrange
        User existingUser = new User(1L, "User 1", "ROLE_USER");
        User updatedUser = new User(1L, "User Updated", "ROLE_ADMIN");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(updatedUser);

        // Act
        User savedUser = userService.save(updatedUser);

        // Assert
        assertNotNull(savedUser);
        assertEquals("User Updated", savedUser.getName());
        assertEquals("ROLE_ADMIN", savedUser.getRole());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void deleteUser_ExistingUser_ShouldReturnTrue() {
        // Arrange
        User existingUser = new User(1L, "User 1", "ROLE_USER");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));

        // Act
        boolean isDeleted = userService.deleteUser(1L);

        // Assert
        assertTrue(isDeleted);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUser_NonExistingUser_ShouldThrowException() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            userService.deleteUser(1L);
        });
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, never()).deleteById(1L);
    }
}
