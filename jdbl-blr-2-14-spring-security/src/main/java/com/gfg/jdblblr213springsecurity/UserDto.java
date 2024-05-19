package com.gfg.jdblblr213springsecurity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    String username;
    String password;
    String role;
}
