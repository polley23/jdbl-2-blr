package com.gfg.jdblblr2librarymanagement.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    Book book;
    @OneToOne
    User user;
    @Temporal(value = TemporalType.DATE)
    Date createdAt;
}
