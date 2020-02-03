package com.denisa.factfulness.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDTO implements Serializable {
    private Integer userId;
    private String username;
    private String password;
    private String name;
}
