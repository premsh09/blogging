package com.blogapp56.controller;

import com.blogapp56.entity.User;
import com.blogapp56.payload.LoginDto;
import com.blogapp56.payload.Signup;
import com.blogapp56.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //http://localhost:8080/api/auth/sign-up
    @PostMapping("/sign-up")
    public ResponseEntity<String> createUser(@RequestBody Signup signup){
        if(userRepository.existsByEmail(signup.getEmail())){
            return new ResponseEntity<>("email id is already registered", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(userRepository.existsByUsername(signup.getUsername())){
            return new ResponseEntity<>("user is already registered",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        User user = new User();
        user.setName(signup.getName());
        user.setEmail(signup.getEmail());
        user.setUsername(signup.getUsername());
        user.setPassword(passwordEncoder.encode(signup.getPassword()));

        userRepository.save(user);
        return new ResponseEntity<>("user is registered", HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        return new ResponseEntity<>("Sign-in is successful", HttpStatus.OK);
    }
}
