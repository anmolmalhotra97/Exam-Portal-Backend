package com.exam.examserver.service;

import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;

import java.util.Set;

public interface UserService {

    //creates the user with the defined roles
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //Get user by username
    public User getUserByUserName(String userName) throws Exception;

    //Delete user by username
    public void deleteUserByUserId(Long userId) throws Exception;
}
