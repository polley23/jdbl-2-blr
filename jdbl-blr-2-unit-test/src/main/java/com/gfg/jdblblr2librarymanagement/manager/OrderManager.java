package com.gfg.jdblblr2librarymanagement.manager;

import com.gfg.jdblblr2librarymanagement.model.OrderDto;

public interface OrderManager {
    void borrowBook(OrderDto order) throws Exception;
    void returnBook(OrderDto order) throws Exception;
}
