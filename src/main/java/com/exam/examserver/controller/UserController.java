package com.exam.examserver.controller;

import com.exam.examserver.model.Role;
import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import com.exam.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //Creating User
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        Set<UserRole> userRoles = new HashSet<>();
        Role role = new Role(44L);
        role.setRoleName("NORMAL");

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
