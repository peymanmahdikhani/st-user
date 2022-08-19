package com.example.interviewsample.user;

import com.example.interviewsample.data.UserEntity;
import com.example.interviewsample.data.UserRepository;
import com.example.interviewsample.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Created by Peyman Mahdikhani on 8/16/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * interview-sample
 */
@SpringBootTest
public class UserTestsSpringBoot {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getUser() throws Exception {
        when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new UserEntity(-1L, "salam")));

        Assertions.assertEquals(-1L, userService.getUser(Mockito.anyLong()).get().getId());
    }
}
