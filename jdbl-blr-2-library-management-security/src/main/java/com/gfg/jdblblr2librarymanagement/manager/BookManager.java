package com.gfg.jdblblr2librarymanagement.manager;

import com.gfg.jdblblr2librarymanagement.model.BookDto;

public interface BookManager {
    void addBook(BookDto book);
    BookDto getBooks(String title) throws Exception;
}
