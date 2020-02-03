package com.denisa.factfulness.service;

import com.denisa.factfulness.dto.UserDTO;
import com.denisa.factfulness.model.User;
import com.denisa.factfulness.repository.UserRepository;
import com.denisa.factfulness.security.ApplicationPasswordEncoder;
import com.denisa.factfulness.security.ApplicationUserDetails;
import com.denisa.factfulness.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationPasswordEncoder encoder;

    public Integer register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user).getUserId();
    }

    public UserDTO getCurrentUser() {
        ApplicationUserDetails userDetails = SecurityUtils.getCurrentUser();
        UserDTO currentUser = new UserDTO();
        currentUser.setUsername(userDetails.getUsername());
        currentUser.setUserId(userDetails.getUserId());
        return currentUser;
    }
}
