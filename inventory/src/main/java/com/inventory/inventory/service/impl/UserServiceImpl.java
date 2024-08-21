package com.inventory.inventory.service.impl;

import com.inventory.inventory.entity.User;
import com.inventory.inventory.exception.ResourceNotFoundException;
import com.inventory.inventory.repository.UserRepository;
import com.inventory.inventory.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    
    private final UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User save(User user) {

        if (user.getId() != null) {
            Optional<User> foundedUser = userRepository.findById(user.getId());
            if (foundedUser.isPresent()) {
                User userUpdated = foundedUser.get();
                userUpdated.setName(user.getName());
                userUpdated.setRole(user.getRole());
                User saveUser = userRepository.save(userUpdated);
                return saveUser;
            }
        }
        User saveUser = userRepository.save(user);
        return saveUser;
    }

//    @Override
//    public User updateUser(User user) {
//        return null;
//    }

    @Override
    public boolean deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","id", userId.toString())
        );
        if (user != null) {
            userRepository.deleteById(userId);
            return true;
        }

        return false;
    }
}
