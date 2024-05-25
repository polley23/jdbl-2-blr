package com.gfg.jdblblr2librarymanagement.repository;

import com.gfg.jdblblr2librarymanagement.entity.Book;
import com.gfg.jdblblr2librarymanagement.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
