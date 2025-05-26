package com.youtics.spring_jpa_neon.user.service;


import com.youtics.spring_jpa_neon.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface UserService {
    Optional<User> findByUsername(String username);
    List<User> findAllUsers();
    User save(User user);
}
