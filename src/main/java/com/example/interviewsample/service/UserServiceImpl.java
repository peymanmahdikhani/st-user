package com.example.interviewsample.service;

import com.example.interviewsample.data.UserEntity;
import com.example.interviewsample.data.UserRepository;
import com.example.interviewsample.web.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by Peyman Mahdikhani on 8/16/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * interview-sample
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Long saveUser(User user) throws Exception {
        Long id = this.userRepository.save(new UserEntity(user.getId(), user.getName())).getId();
        return id;
    }

    @Override
    public Optional<User> getUser(Long userId) throws Exception {
        return this.userRepository.findById(userId)
                .map(this::userEntityToUser);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) throws Exception {
        return this.userRepository.findAll(pageable).map(this::userEntityToUser);
    }

    @Override
    public void deleteUser(Long userId) throws Exception {
        this.userRepository.deleteById(userId);
    }

    private User userEntityToUser(UserEntity userEntity){
        return new User(
                userEntity.getId(),
                userEntity.getName()
        );
    }
}
