package com.taskmanagement.taskmanagement.authentication;

import com.taskmanagement.taskmanagement.authentication.Dto.AuthenticationRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("api/v1/authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Users> signUp(@Valid @RequestBody AuthenticationRequestDto user){
        return this.authenticationService.signUp(user);
    }
    @PostMapping("/sign-in")
    public String authenticate(@Valid @RequestBody AuthenticationRequestDto user){
        return this.authenticationService.signIn(user);

    }
}
