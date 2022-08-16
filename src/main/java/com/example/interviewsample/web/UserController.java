package com.example.interviewsample.web;

import com.example.interviewsample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Created by Peyman Mahdikhani on 8/16/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * interview-sample
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
//@Validated
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public final Optional<User> getUser(@PathVariable("userId") Long userId) throws Exception {
        return this.userService.getUser(userId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public final Page<User> getUsers(@NotNull final Pageable pageable) throws Exception {
        return this.userService.getAllUsers(pageable);
    }

    @PostMapping
    public final ResponseEntity saveUser(@Valid @RequestBody User user) throws Exception {
        Long userId = this.userService.saveUser(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/user/" + userId);
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public final void updateUser(@Valid @RequestBody User user, @PathVariable("userId") Long userId) throws Exception {
        user.setId(userId);
        this.userService.saveUser(user);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") Long userId) throws Exception {
        this.userService.deleteUser(userId);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
