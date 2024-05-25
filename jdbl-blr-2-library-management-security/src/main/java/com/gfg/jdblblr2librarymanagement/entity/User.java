package com.gfg.jdblblr2librarymanagement.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 255, nullable = false, unique = true)
    private String username;
    private String password;
    private String role;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Fine fine;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Order order;

}
