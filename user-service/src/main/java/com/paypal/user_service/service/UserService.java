package com.paypal.user_service.service;

import com.paypal.user_service.dto.UserResponse;
import com.paypal.user_service.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponse createUser(User user);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
}
