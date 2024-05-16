package com.example.ecommfullstack.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.ecommfullstack.Models.User;
import com.example.ecommfullstack.Repository.UserRepository;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenProvider {

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    private Key jwtSecretKey;
    
    private UserRepository userRepository;
    
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    protected void init() {
        try {
            this.jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
            if (jwtSecretKey == null) {
                System.out.println("jwtSecretKey is null after initialization.");
            } else {
                System.out.println("jwtSecretKey initialized successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error occurred during jwtSecretKey initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(jwtSecretKey)
                .compact();
    }

    public String createToken(Long id,String email, String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        return Jwts.builder()
                .setSubject(email)
                .claim("id", id)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(jwtSecretKey)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String subject = claims.getSubject();
        // Assuming the subject is an email address
        // You may need to adjust this logic based on your specific use case
        Optional<User> userOptional = userRepository.findByEmail(subject);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getId();
        } else {
            // Handle the case where the user with the provided email is not found
            // For example, log a warning or return null
            System.err.println("User not found for email: " + subject);
            return null;
        }
    }





    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
