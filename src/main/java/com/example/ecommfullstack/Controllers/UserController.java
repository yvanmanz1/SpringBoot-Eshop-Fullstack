package com.example.ecommfullstack.Controllers;

import javax.validation.constraints.Null;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommfullstack.Models.AppUserRole;
import com.example.ecommfullstack.Models.LoginRequest;
import com.example.ecommfullstack.Models.RegistrationRequest;
import com.example.ecommfullstack.Models.User;
import com.example.ecommfullstack.Security.JwtResponse;
import com.example.ecommfullstack.Security.JwtTokenProvider;
import com.example.ecommfullstack.Services.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
   

    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        userService.registerUser(registrationRequest.getEmail(), registrationRequest.getName(), registrationRequest.getPassword());
        return ResponseEntity.ok("Registration successful. Please login to continue."); // Return a success message
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Login unsuccessful. Please check your credentials.");
        }
        
        String token = jwtTokenProvider.createToken(user.getId(), user.getEmail(), user.getRole().name());
        System.out.println(token);
        
        if (user.getRole() == AppUserRole.ADMIN) {
            return ResponseEntity.ok(new JwtResponse(token, "/admin/landing")); 
        } else {
            return ResponseEntity.ok(new JwtResponse(token, "/homepage")); 
        }
    }

}
