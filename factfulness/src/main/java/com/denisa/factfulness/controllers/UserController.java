package com.denisa.factfulness.controllers;

import com.denisa.factfulness.dto.UserDTO;
import com.denisa.factfulness.model.Role;
import com.denisa.factfulness.model.User;
import com.denisa.factfulness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<Integer> register(
            @RequestParam String name,
            @RequestParam String username,
            @RequestParam String password) {
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(Role.USER);
        Integer userId = userService.register(user);
        if (userId == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(userId);
    }

    @GetMapping("/current")
    public UserDTO getCurrentUser() {
        return userService.getCurrentUser();
    }
}
