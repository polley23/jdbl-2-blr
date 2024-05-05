package com.gfg.jdblblr2librarymanagement.controller;

import com.gfg.jdblblr2librarymanagement.manager.BookManager;
import com.gfg.jdblblr2librarymanagement.manager.FineManager;
import com.gfg.jdblblr2librarymanagement.manager.OrderManager;
import com.gfg.jdblblr2librarymanagement.manager.UserManager;
import com.gfg.jdblblr2librarymanagement.model.BookDto;
import com.gfg.jdblblr2librarymanagement.model.FineDto;
import com.gfg.jdblblr2librarymanagement.model.OrderDto;
import com.gfg.jdblblr2librarymanagement.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Api {
    @Autowired
    BookManager bookManager;
    @Autowired
    UserManager userManager;
    @Autowired
    OrderManager orderManager;
    @Autowired
    FineManager fineManager;

    // Create Book
    @PostMapping("/api/book")
    ResponseEntity addBook(@RequestBody BookDto bookDto){
        bookManager.addBook(bookDto);
        return ResponseEntity.ok().build();
    }
    // Get Book
    @GetMapping("/api/book")
    ResponseEntity getBook(@RequestParam("title") String title){
        try {
            BookDto bookDto = bookManager.getBooks(title);
            return ResponseEntity.ok(bookDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Create user
    @PostMapping("api/user")
    ResponseEntity createUser(@RequestBody UserDto userDto){
        userManager.createUser(userDto.getUsername());
        return ResponseEntity.ok().build();

    }

    // Create order
    @PostMapping("/api/order")
    ResponseEntity createOrder(@RequestBody OrderDto orderDto){
        try {
            orderManager.borrowBook(orderDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

    // return book
    @DeleteMapping("/api/order/username/{username}/title/{title}")
    ResponseEntity returnOrder(@PathVariable("username")  String username,
                               @PathVariable("title")  String title){
        try {
            OrderDto orderDto = new OrderDto();
            orderDto.setTitle(title);
            orderDto.setUsername(username);
            orderManager.returnBook(orderDto);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get Fine
    @GetMapping("/api/fine/user/{username}")
    ResponseEntity getFine(@PathVariable("username")  String username){
        try {
           Integer amount = fineManager.getForUser(username);
            return ResponseEntity.ok(amount);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Pay Fine
    @PutMapping("/api/fine")
    ResponseEntity payFine(@RequestBody FineDto fineDto){
        try {
            fineManager.payForUser(fineDto.getUsername(),fineDto.getAmount() );
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }





}
