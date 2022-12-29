package com.exam.examserver.implementation;

import com.exam.examserver.model.user.User;
import com.exam.examserver.model.user.UserRole;
import com.exam.examserver.repository.RoleRepository;
import com.exam.examserver.repository.UserRepository;
import com.exam.examserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User currentUser = this.userRepository.findByUsername(user.getUsername());
        if (currentUser != null) {
            log.error("User is already there !!");
            throw new Exception("User already present !! Cannot create User again.");
        } else {
            //We will save roles one by one
            //We won't save userRoles as it will get saved automatically when we save User because of Cascade.ALL
            for (UserRole userRole : userRoles) {
                roleRepository.save(userRole.getRole());
            }

            user.setUserRoles(userRoles);
            return this.userRepository.save(user);
        }
    }

    @Override
    public User getUserByUserName(String userName) throws Exception {
        User user = this.userRepository.findByUsername(userName);
        if (user != null) {
            return user;
        } else {
            log.error("User with userName: {} doesn't exist", userName);
            throw new Exception("User with username " + userName + " not found!");
        }
    }

    public void deleteUserByUserId(Long userId) throws Exception {
        try {
            this.userRepository.deleteById(userId);
        } catch (Exception exception) {
            log.error("Error while deleting User with userId: {} {}", userId, exception.getMessage());
            throw new Exception("Error while deleting UserId: " + userId);
        }
    }
}
