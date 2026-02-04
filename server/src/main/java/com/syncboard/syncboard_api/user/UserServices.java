package com.syncboard.syncboard_api.user;

import com.syncboard.syncboard_api.security.JwtService;
import com.syncboard.syncboard_api.user.dto.AuthResponse;
import com.syncboard.syncboard_api.user.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    public AuthResponse registerUser(RegisterRequest request){

        String name = request.getDisplayName();
        String email = request.getEmail();
        String password = request.getPassword();

        //check if email is taken
        if (userRepository.existsByEmail(email)){
            throw new RuntimeException("Email is already taken");
        }

        User newUser = new User();
        newUser.setDisplayName(name);
        newUser.setEmail(email);
        String hashedPassword = passwordEncoder.encode(password);
        newUser.setPasswordHash(hashedPassword);

        User saved = userRepository.save(newUser);

        String token = jwtService.generateToken(saved.getEmail());

        return new AuthResponse(
                token,
                saved.getId(),
                saved.getEmail(),
                saved.getDisplayName(),
                saved.getCreatedAt()
        );
    }
}
