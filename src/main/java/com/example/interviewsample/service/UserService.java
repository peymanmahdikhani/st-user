package com.example.interviewsample.service;

import com.example.interviewsample.web.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Created by Peyman Mahdikhani on 8/16/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * interview-sample
 */
public interface UserService {
    Long saveUser(User user) throws Exception;
    Optional<User> getUser(Long userId) throws Exception;
    Page<User> getAllUsers(Pageable pageable) throws Exception;
    void deleteUser(Long userId) throws Exception;
}
