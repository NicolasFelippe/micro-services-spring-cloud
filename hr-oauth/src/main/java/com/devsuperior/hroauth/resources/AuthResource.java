package com.devsuperior.hroauth.resources;

import com.devsuperior.hroauth.entities.User;
import com.devsuperior.hroauth.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AuthResource {
    private final UserService userService;

    public AuthResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/search/{email}")
    public ResponseEntity<UserDetails> findByEmail(@PathVariable("email") String email){
        try {
            return ResponseEntity.ok(userService.loadUserByUsername(email));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
