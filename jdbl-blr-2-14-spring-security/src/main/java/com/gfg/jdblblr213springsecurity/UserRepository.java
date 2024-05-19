package com.gfg.jdblblr213springsecurity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
}
