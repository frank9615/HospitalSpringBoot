package com.example.HospitalSpringBoot;

import com.example.HospitalSpringBoot.entities.Doctor;
import com.example.HospitalSpringBoot.entities.User;
import com.example.HospitalSpringBoot.enums.Role;
import com.example.HospitalSpringBoot.repositories.UserRepository;
import com.example.HospitalSpringBoot.services.IUserService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest()
@ContextConfiguration(classes = HospitalSpringBootApplication.class)
public class InMemoryDBTest {

    @Autowired
    private IUserService userService;

    //Add here data inmemory


    @Test
    @Order(1)
    public void newUserTest(){
        User d = new User();
        d.setName("Test");
        d.setSurname("Test");
        d.setUsername("Test");
        d.setPassword("Test");
        d.setRole(Role.DOCTOR);

        userService.save(d);
        User u = userService.getByUsername("Test");
        assertEquals("Test", u.getUsername());
    }

    @Test
    @Order(2)
    public void updateUser(){
        User u = userService.getByUsername("Test");
        u.setPassword("Prova");
        userService.save(u);
        User retrived = userService.getByUsername("Test");
        assertEquals("Prova", retrived.getPassword());

    }
}
