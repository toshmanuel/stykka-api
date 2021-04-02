package com.stykkapi.stykka.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class UserTest {

    User user;

    @Autowired
    UserRepository userRepository;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void userCanBeAdded(){
        User firstUser = new User();
        firstUser.setFirstName("Dozie");
        firstUser.setLastName("Him");
        firstUser.setEmail("dozie.don@email.com");
        firstUser.setPassword("mejaj");
        userRepository.save(firstUser);

        assertEquals(userRepository.count(), 1);
    }

    @Test
    void userInformationCanBeUpdated(){
        Optional<User> findUser = userRepository.findById("6065b77106494e5d307d5437");
        findUser.get().setEmail("don@email.go");
        userRepository.save(findUser.get());
        assertEquals("don@email.go", findUser.get().getEmail());
    }

    @Test
    void userCanBeDeleted(){

        User secondUser = new User();
        secondUser.setFirstName("Amaka");
        secondUser.setLastName("Mbah");
        secondUser.setEmail("amaka.mbah@gmail.com");
        secondUser.setPassword("9892hasjhasjha");

        userRepository.save(secondUser);

        User newUser = userRepository.findAll().get(0);
        userRepository.delete(newUser);
        assertEquals(userRepository.count(), 1);
    }
}