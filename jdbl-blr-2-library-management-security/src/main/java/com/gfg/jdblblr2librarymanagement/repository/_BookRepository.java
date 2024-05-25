package com.gfg.jdblblr2librarymanagement.repository;


import com.gfg.jdblblr2librarymanagement.entity._Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  _BookRepository extends CrudRepository<_Book,String> {
    Optional<_Book> findByTitle(String title);
}
