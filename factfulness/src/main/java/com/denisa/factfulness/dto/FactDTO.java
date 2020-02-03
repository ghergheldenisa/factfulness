package com.denisa.factfulness.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class FactDTO {
    private Integer factId;
    private String text;
    private UserDTO createdBy;
}
