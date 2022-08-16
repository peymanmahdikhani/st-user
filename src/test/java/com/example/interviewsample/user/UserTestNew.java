package com.example.interviewsample.user;

import com.example.interviewsample.data.UserEntity;
import com.example.interviewsample.service.UserService;
import com.example.interviewsample.web.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Created by Peyman Mahdikhani on 8/16/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * workspace
 */
@WebMvcTest
@ActiveProfiles("test")
public class UserTestNew {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getUser() throws Exception {
        when(userService.getUser(Mockito.anyLong())).thenReturn(Optional.of(new User(-1L, "salam")));

        Assertions.assertEquals(-1L, userService.getUser(Mockito.anyLong()).get().getId());
    }
}
