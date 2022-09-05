package com.example.interviewsample.service;

import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Created by Peyman Mahdikhani on 8/21/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * workspace
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {
    private final WebClient webClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(
                "admin",
                "{noop}pass",
                true,
                true,
                true,
                true, Sets.newHashSet(()->"ADMIN"));
    }


    public Mono findByIdWithAccounts(@PathVariable("id") String id) {
        log.info("findByIdWithAccounts: id={}", id);
        /*Flux accounts = webClientBuilder.build().get().uri("http://account-service/customer/{customer}", id).retrieve().bodyToFlux(Account.class);
        return accounts
                .collectList()
                .map(a -> new Customer(a))
                .mergeWith(repository.findById(id))
                .collectList()
                .map(CustomerMapper::map);*/
        return null;
    }
}
