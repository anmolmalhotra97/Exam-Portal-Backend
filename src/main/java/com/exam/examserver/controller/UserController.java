package com.exam.examserver.controller;

import com.exam.examserver.model.Role;
import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import com.exam.examserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //Creating User
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        log.info("Creating User with username: {}", user.getUsername());
        user.setProfile("default.png");
        Set<UserRole> userRoles = new HashSet<>();
        Role role = new Role(44L);
        role.setRoleName("NORMAL");

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        userRoles.add(new UserRole(null, user, role));
        return this.userService.createUser(user, userRoles);
    }

    //Get User
    @GetMapping("/{userName}")
    public User getUser(@PathVariable String userName) throws Exception {
        return this.userService.getUserByUserName(userName);
    }

    //Delete User
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) throws Exception {
        this.userService.deleteUserByUserId(userId);
    }
}
