package com.example.interviewsample.user;

import com.example.interviewsample.service.UserService;
import com.example.interviewsample.web.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by Peyman Mahdikhani on 8/16/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * workspace
 */
@WebMvcTest
@ActiveProfiles("test")
public class UserTestWebMvc {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getUser() throws Exception {
        when(userService.getUser(Mockito.anyLong())).thenReturn(Optional.of(new User(-1L, "salam")));

        Assertions.assertEquals(-1L, userService.getUser(Mockito.anyLong()).get().getId());
    }

    @WithMockUser("myuser_also_wrong")
    @Test
    public void testWithAnyUser() throws Exception {
        when(userService.getUser(Mockito.anyLong())).thenReturn(Optional.of(new User(-1L, "salam")));
        String userStr = objectMapper.writeValueAsString(new User(-1L, "salam"));
        mockMvc.perform(get("/user/1")).andExpect(MockMvcResultMatchers.content().string(userStr));
    }

    @Test
    public void testWithHttpBasic() throws Exception {
        when(userService.getUser(Mockito.anyLong())).thenReturn(Optional.of(new User(-1L, "salam")));


        String userStr = objectMapper.writeValueAsString(new User(-1L, "salam"));
        mockMvc
                .perform(get("/user/0").with(httpBasic("admin","pass")))
                .andExpect(MockMvcResultMatchers.content().json(userStr));
    }

    @Test
    public void testAccessIndexWithoutAuthentication() throws Exception {
        mockMvc
                .perform(get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
