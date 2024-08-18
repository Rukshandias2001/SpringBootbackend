package com.example.demo.Auth;

import com.example.demo.DTO.AuthenticationRequest;
import com.example.demo.Entities.Role;
import com.example.demo.Entities.User;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse verifyGoogleToken(String idTokenString) {
        try {
            Collection<Role> roles = new ArrayList<>();
            Role roleUser = roleRepository.findByName("ROLE_USER");
            roles.add(roleUser);
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList("130196933040-61k2u0kdkcgn7qqe191hbemsrq8le897.apps.googleusercontent.com"))
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();

                // Print user identifier
                String userId = payload.getSubject();
                System.out.println("User ID: " + userId);

                // Get profile information from payload
                String email = payload.getEmail();
                boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
                String locale = (String) payload.get("locale");
                String familyName = (String) payload.get("family_name");
                String givenName = (String) payload.get("given_name");

                // Check if user exists in the database
                User user = userRepository.findByEmail(email).orElseGet(() -> {

                    // If user does not exist, create a new user
                    User newUser = User.builder()
                            .email(email)
                            .userName(name)
                            .lastName(familyName)
                            .roles(roles)
                            .build();
                    userRepository.save(newUser);
                    return newUser;
                });

                // Generate JWT token
                String jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder().token(jwtToken).build();

            } else {
                System.out.println("Invalid ID token.");
                throw new RuntimeException("Invalid ID token.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to verify ID token.", e);
        }
    }


    public AuthenticationResponse register(RegisterRequest request) {
        Collection<Role> roles = new ArrayList<>();
        Role roleUser = roleRepository.findByName("ROLE_USER");
        roles.add(roleUser);
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userName(request.getUserName())
                .lastName(request.getLastName())
                .roles(roles)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }



    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }
}
