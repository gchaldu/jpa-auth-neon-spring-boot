package com.youtics.spring_jpa_neon.user.service;


import com.youtics.spring_jpa_neon.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {
    List<User> findAllUsers();
    User save(User user);
}
