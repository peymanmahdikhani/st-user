package com.example.interviewsample.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.util.DigestUtils;

/**
 * Created by Peyman Mahdikhani on 8/19/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * workspace
 */

public class PasswordHashTest {
    public static final String password = "password";


    @Test
    public void testHash(){
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println("md5DigestAsHex = " + md5DigestAsHex);
        md5DigestAsHex = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println("md5DigestAsHex = " + md5DigestAsHex);
    }

    @Test
    public void tesNoOp(){
        PasswordEncoder noOp = NoOpPasswordEncoder.getInstance();
        String encode = noOp.encode(password);
        System.out.println("noOp = " + encode);
        encode = noOp.encode(password);
        System.out.println("noOp = " + encode);
    }

    @Test
    public void testLdap(){
        PasswordEncoder passwordEncoder = new LdapShaPasswordEncoder();
        String encode = passwordEncoder.encode(password);
        System.out.println("ldap = " + encode);
        encode = passwordEncoder.encode(password);
        System.out.println("ldap = " + encode);
        boolean matches = passwordEncoder.matches(password, encode);
        System.out.println("ldap matches = " + matches);
        assert matches;
    }

    @Test
    public void testSha256(){
        PasswordEncoder passwordEncoder = new StandardPasswordEncoder();
        String encode = passwordEncoder.encode(password);
        System.out.println("Sha256 = " + encode);
        encode = passwordEncoder.encode(password);
        System.out.println("Sha256 = " + encode);
        boolean matches = passwordEncoder.matches(password, encode);
        System.out.println("Sha256 matches = " + matches);
        assert matches;
    }

    @Test
    public void testBcrypt(){//default encoder for spring security
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(password);
        System.out.println("Bcrypt = " + encode);
        encode = passwordEncoder.encode(password);
        System.out.println("Bcrypt = " + encode);
        boolean matches = passwordEncoder.matches(password, encode);
        System.out.println("Bcrypt matches = " + matches);
        assert matches;
    }
}
