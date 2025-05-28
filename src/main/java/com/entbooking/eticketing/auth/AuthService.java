package com.entbooking.eticketing.auth;

import com.entbooking.eticketing.dto.AuthResponse;
import com.entbooking.eticketing.dto.RegisterRequest;
import com.entbooking.eticketing.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepo;
    //private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepo, JwtService jwtService) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
    }

    public AuthResponse register(@Valid RegisterRequest request) {
        return null;
    }

    /*public AuthService(UserRepository userRepo, PasswordEncoder encoder, JwtService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = encoder;
        this.jwtService = jwtService;
    }*/

    /*public AuthResponse register(RegisterRequest request) {
        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        AppUser saved = userRepo.save(user);
        String token = jwtService.generateToken(saved);
        return new AuthResponse(token, System.currentTimeMillis() + jwtService.getExpiration());
    }*/
}
