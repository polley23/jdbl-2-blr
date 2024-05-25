package com.gfg.jdblblr2librarymanagement.controller;

import com.gfg.jdblblr2librarymanagement.manager.BookManager;
import com.gfg.jdblblr2librarymanagement.manager.FineManager;
import com.gfg.jdblblr2librarymanagement.manager.OrderManager;
import com.gfg.jdblblr2librarymanagement.manager.UserManager;
import com.gfg.jdblblr2librarymanagement.model.BookDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ApiTest {
    @Mock
    BookManager bookManager;
    @Mock
    UserManager userManager;
    @Mock
    OrderManager orderManager;
    @Mock
    FineManager fineManager;
    @InjectMocks
    Api api;

    @Test
    void addBook() {
        Mockito.doNothing().when(bookManager).addBook(Mockito.any());
        api.addBook(new BookDto());
        Mockito.verify(bookManager,Mockito.times(1)).addBook(Mockito.any());
    }
}