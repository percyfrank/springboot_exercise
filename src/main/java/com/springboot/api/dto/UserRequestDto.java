package com.springboot.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class UserRequestDto {
    private String id;
    private String name;
    private String password;

}
