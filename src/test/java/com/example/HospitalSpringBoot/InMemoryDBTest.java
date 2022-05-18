package com.example.HospitalSpringBoot;


import com.example.HospitalSpringBoot.entities.User;
import com.example.HospitalSpringBoot.enums.Role;
import com.example.HospitalSpringBoot.services.IUserService;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest()
@ContextConfiguration(classes = HospitalSpringBootApplication.class)
@Log
public class InMemoryDBTest {

    @Autowired
    private IUserService userService;



    @Test
    public void newUserTest(){
        User u = userService.getByUsername("a");
        assertEquals("a", u.getUsername());
    }

    @Test
    public void updateUser(){
        String newPassword = "nuovapassowrd";
        User u = userService.getByUsername("a");
        u.setPassword(newPassword);
        userService.save(u);
        User retrived = userService.getByUsername("a");
        assertEquals(newPassword, retrived.getPassword());
    }

}
