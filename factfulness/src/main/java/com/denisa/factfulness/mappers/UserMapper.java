package com.denisa.factfulness.mappers;

import com.denisa.factfulness.dto.UserDTO;
import com.denisa.factfulness.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserDTO mapToUserDTO(User user) {
        if(user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setName(user.getName());
        return userDTO;
    }

    public User mapToUser(UserDTO userDTO) {
        if(userDTO == null) {
            return null;
        }
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        return user;
    }
}
