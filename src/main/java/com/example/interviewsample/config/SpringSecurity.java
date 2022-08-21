package com.example.interviewsample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by Peyman Mahdikhani on 8/19/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * workspace
 */
@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter{

//    @Autowired
//    private UserDetailsService userDetailsService;//MyUserDetailService

    public SecurityCustomAuthenticationFilter securityCustomAuthenticationFilter(AuthenticationManager authenticationManager){
        SecurityCustomAuthenticationFilter securityCustomAuthenticationFilter = new SecurityCustomAuthenticationFilter(
                new AntPathRequestMatcher("/user/**", HttpMethod.DELETE.toString())
        );
        securityCustomAuthenticationFilter.setAuthenticationManager(authenticationManager);
        return securityCustomAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
        //supports all type of encryption automatically by prefixes {bcrypt} or {noop} or {sha256} ... in passwords
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(securityCustomAuthenticationFilter(authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class).csrf().disable();

        http.authorizeRequests(
                (requests) -> requests
                        //expect some url from authentication
                        .antMatchers("/", "/somepath/**").permitAll()
                        //expect some url from authentication with methods
                        .antMatchers(HttpMethod.GET,"/someapi").permitAll()
                        //mvcMatchers
                        .mvcMatchers(HttpMethod.GET, "/apipath/{userId}").permitAll()
        );

        http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
        http.formLogin();
        http.httpBasic();
    }


    //Way 2 to have own in memory authentication
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}pass")// {noop}pass is for defining password encoder
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("pass")
                .roles("USER");

        auth.inMemoryAuthentication()
                .withUser("user2")
                .password("{noop}passwd")
                .roles("USER");
//        auth.userDetailsService(userDetailsService);

    }*/

    //Way 1 to have own in memory authentication
   /* @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User
                .withDefaultPasswordEncoder()
                .username("admin")
                .password("pass")
                .roles("ADMIN")
                .build();

        UserDetails user = User
                .withDefaultPasswordEncoder()
                .username("user")
                .password("pass")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(Lists.newArrayList(admin, user));
    }*/
}
