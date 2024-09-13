package com.inventory.inventory.user;


import com.inventory.inventory.entity.User;
import com.inventory.inventory.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void shouldReturnUserWhenFindById() {

        // inputan
        User user = new User();
        user.setName("robi nurhidayat");
        user.setRole("user");

        user = userRepository.save(user);

        // yang di cari
        Optional<User> userId = userRepository.findById(user.getId());

        // hasil yang di inginkan
        Assertions.assertNotNull(userId);
        Assertions.assertEquals(1, user.getId());

    }
}
