package com.example.interviewsample.user;

import com.example.interviewsample.data.UserEntity;
import com.example.interviewsample.data.UserRepository;
import com.example.interviewsample.service.UserService;
import com.example.interviewsample.service.UserServiceImpl;
import com.example.interviewsample.web.User;
import com.example.interviewsample.web.UserController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Created by Peyman Mahdikhani on 8/16/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * workspace
 */
@ExtendWith(MockitoExtension.class)
public class UserTestJustStandAloneService {

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
