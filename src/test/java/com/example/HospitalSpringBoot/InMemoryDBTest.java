package com.example.HospitalSpringBoot;


import com.example.HospitalSpringBoot.entities.User;
import com.example.HospitalSpringBoot.enums.Role;
import com.example.HospitalSpringBoot.services.IUserService;
import lombok.extern.java.Log;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest()
@ContextConfiguration(classes = HospitalSpringBootApplication.class)
@Log
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InMemoryDBTest {

    @Autowired
    private IUserService userService;

    @Test
    @Order(1)
    public void getAllUsers(){
        List<User> users = userService.getAll();
        assertEquals(3, users.size());
    }

    @Test
    @Order(2)
    public void getById(){
        User u = userService.getById(1L);
        assertEquals("a", u.getUsername());
    }

    @Test
    @Order(3)
    public void save(){
        User u = userService.getByUsername("a");
        assertEquals("a", u.getUsername());
    }

    @Test
    @Order(4)
    public void update(){
        String newPassword = "nuovapassowrd";
        User u = userService.getByUsername("a");
        u.setPassword(newPassword);
        userService.save(u);
        User retrived = userService.getByUsername("a");
        assertEquals(newPassword, retrived.getPassword());
    }

    @Test
    @Order(5)
    public void delete(){
        User u = userService.getById(1L);
        userService.delete(u);
        User deleted = null;
        try{
            deleted = userService.getById(1L);
        }catch(NoSuchElementException e){
            log.info("Eccezione lanciata, elemento non presente");
        }finally {
            assertNull(deleted);
        }
    }

}
