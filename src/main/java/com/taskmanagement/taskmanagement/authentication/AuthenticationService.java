package com.taskmanagement.taskmanagement.authentication;

import com.taskmanagement.taskmanagement.authentication.Dto.AuthenticationRequestDto;
import com.taskmanagement.taskmanagement.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public ResponseEntity<Users> signUp(AuthenticationRequestDto data) {
        Users user = new Users();
        user.setUsername(data.username());
        user.setPassword(encoder.encode(data.password()));
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    public String signIn(AuthenticationRequestDto user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.username(),user.password())
        );
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.username());
        } else {
            return "Failed";
        }

    }
}
