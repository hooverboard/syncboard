package com.syncboard.syncboard_api.user;

import com.syncboard.syncboard_api.security.JwtService;
import com.syncboard.syncboard_api.user.dto.AuthResponse;
import com.syncboard.syncboard_api.user.dto.LoginRequest;
import com.syncboard.syncboard_api.user.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    //REGISTER USER
    public AuthResponse registerUser(RegisterRequest request){

        String name = request.getDisplayName();
        String email = request.getEmail().toLowerCase(); // set email to lowercase
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

        //create jwt token
        String token = jwtService.generateToken(saved.getEmail());

        return new AuthResponse(
                token,
                saved.getId(),
                saved.getEmail(),
                saved.getDisplayName(),
                saved.getCreatedAt()
        );
    }

    //LOGIN USER
    public AuthResponse loginUser(LoginRequest request){

        String email = request.getEmail().toLowerCase(); // set email to lowercase
        String password = request.getPassword();

        //find user
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()){
            throw new RuntimeException("User not found");
        }

        //check if pw matches
        if (!passwordEncoder.matches(password, user.get().getPasswordHash())){
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.get().getEmail());

        return new AuthResponse(
                token,
                user.get().getId(),
                user.get().getEmail(),
                user.get().getDisplayName(),
                user.get().getCreatedAt()
        );
    }
}
