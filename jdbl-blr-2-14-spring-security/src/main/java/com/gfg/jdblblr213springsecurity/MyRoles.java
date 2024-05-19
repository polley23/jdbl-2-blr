package com.gfg.jdblblr213springsecurity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String role;
}
