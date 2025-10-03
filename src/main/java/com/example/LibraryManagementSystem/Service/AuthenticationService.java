package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTO.LoginRequestDTO;
import com.example.LibraryManagementSystem.DTO.LoginResponseDTO;
import com.example.LibraryManagementSystem.DTO.RegisterRequestDTO;
import com.example.LibraryManagementSystem.Entity.User;
import com.example.LibraryManagementSystem.JWT.JwtService;
import com.example.LibraryManagementSystem.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public User registerNormalUser(RegisterRequestDTO registerRequestDTO) {
        if (userRepo.findByEmail(registerRequestDTO.getEmail()).isPresent()){
            throw new RuntimeException("User already registered");
        }

        Set<String> roles = new HashSet<String>();
        roles.add("ROLE_USER");

        User user = new User();
        user.setName(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);

        return userRepo.save(user);
    }

    public User registerAdminUser(RegisterRequestDTO registerRequestDTO) {
        if (userRepo.findByEmail(registerRequestDTO.getEmail()).isPresent()){
            throw new RuntimeException("User already registered");
        }

        Set<String> roles = new HashSet<String>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");

        User user = new User();
        user.setName(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);

        return userRepo.save(user);
    }


    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()
                )
        );

        User user = userRepo.findByEmail(loginRequestDTO.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);

        return LoginResponseDTO.builder()
                .token(token)
                .username(user.getName())
                .roles(user.getRoles())
                .build();
    }
}
