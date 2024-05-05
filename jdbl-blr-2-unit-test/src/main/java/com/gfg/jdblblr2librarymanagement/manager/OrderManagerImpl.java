package com.gfg.jdblblr2librarymanagement.manager;

import com.gfg.jdblblr2librarymanagement.entity.Book;
import com.gfg.jdblblr2librarymanagement.entity.Fine;
import com.gfg.jdblblr2librarymanagement.entity.Order;
import com.gfg.jdblblr2librarymanagement.entity.User;
import com.gfg.jdblblr2librarymanagement.error.*;
import com.gfg.jdblblr2librarymanagement.model.OrderDto;
import com.gfg.jdblblr2librarymanagement.repository.BookRepository;
import com.gfg.jdblblr2librarymanagement.repository.OrderRepository;
import com.gfg.jdblblr2librarymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class OrderManagerImpl implements OrderManager{
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void borrowBook(OrderDto orderReq) throws Exception {
        String title = orderReq.getTitle();
        String username = orderReq.getUsername();
        User user =  userRepository.findByUsername(username).orElseThrow(
                ()-> new NoUserFound("No such user exists.")
        );
        if (user.getOrder() != null){
            throw new OrderAlreadyExists("User is having a book already.");
        }
        if (user.getFine().getAmount() >0){
            throw new NonZeroFine("User has to clear fine.");
        }

        Book book =  bookRepository.findByTitle(title).orElseThrow(
                ()-> new BookNotFound("No such title")
        );
        if(book.getQty()<1){
            throw new BookNotAvailable("Book is not available");
        }
        Integer qty = book.getQty();
        book.setQty(qty-1);

        Order order =  Order.builder()
                .book(book)
                .user(user)
                .createdAt(Date.valueOf(LocalDate.now().minusDays(10l)))
                .build();
        user.setOrder(order);
        userRepository.save(user);
        bookRepository.save(book);
    }

    @Override
    public void returnBook(OrderDto orderReq) throws Exception {
        String title = orderReq.getTitle();
        String username = orderReq.getUsername();

        User user =  userRepository.findByUsername(username).orElseThrow(
                ()-> new Exception("No such user exists.")
        );
        if (user.getOrder() == null){
            throw new Exception("user doesn't have any order");
        }
        Book book = user.getOrder().getBook();
        Integer qty = book.getQty();
        book.setQty(qty+1);

        Order order = user.getOrder();
        if(!order.getBook().getTitle().equals(title)){
            throw new Exception("user is returning wrong name");
        }
        LocalDate borrowedDate = order.getCreatedAt().toLocalDate();
        long daysBetween = ChronoUnit.DAYS.between(borrowedDate, LocalDate.now());
        Integer fineAmount = 0;
        if(daysBetween > 7){
            fineAmount = ( (int)daysBetween - 7) * 5;
        }

        Fine fine = user.getFine();
        fine.setAmount(fineAmount);
        user.setFine(fine);
        order.setUser(null);
        order.setBook(null);
        user.setOrder(null);
        userRepository.save(user);
        bookRepository.save(book);
        orderRepository.delete(order);


    }
}
