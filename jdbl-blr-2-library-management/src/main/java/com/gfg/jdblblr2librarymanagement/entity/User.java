package com.gfg.jdblblr2librarymanagement.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.data.repository.cdi.Eager;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1024, nullable = false, unique = true)
    private String username;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Fine fine;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BookOrder order;


}
