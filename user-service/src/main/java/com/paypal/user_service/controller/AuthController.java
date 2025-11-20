package com.paypal.user_service.controller;

import com.paypal.user_service.dto.JwtTokenResponse;
import com.paypal.user_service.dto.LoginRequest;
import com.paypal.user_service.dto.SignUpRequest;
import com.paypal.user_service.entity.User;
import com.paypal.user_service.repository.UserRepository;
import com.paypal.user_service.utils.JWTUtil;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        Optional<User> existingUser = userRepository.findByEmail(signUpRequest.getEmail());
        if(existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());
        if(userOpt.isEmpty()) {
            return ResponseEntity.status(401).body("User Not Found");
        }
        User user = userOpt.get();
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        Map<String, Object> claims = Map.of("role", user.getRole());
        String token = jwtUtil.generateToken(claims, user.getEmail());
        return ResponseEntity.ok(new JwtTokenResponse(token));
    }
}
