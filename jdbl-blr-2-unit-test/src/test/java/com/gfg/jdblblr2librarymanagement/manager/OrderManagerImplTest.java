package com.gfg.jdblblr2librarymanagement.manager;

import ch.qos.logback.core.testUtil.MockInitialContext;
import com.gfg.jdblblr2librarymanagement.entity.Book;
import com.gfg.jdblblr2librarymanagement.entity.Fine;
import com.gfg.jdblblr2librarymanagement.entity.Order;
import com.gfg.jdblblr2librarymanagement.entity.User;
import com.gfg.jdblblr2librarymanagement.error.*;
import com.gfg.jdblblr2librarymanagement.model.OrderDto;
import com.gfg.jdblblr2librarymanagement.repository.BookRepository;
import com.gfg.jdblblr2librarymanagement.repository.OrderRepository;
import com.gfg.jdblblr2librarymanagement.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderManagerImplTest {
    @Mock
    BookRepository bookRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    OrderRepository orderRepository;
    @InjectMocks
    OrderManagerImpl orderManager;

    @Test()
    void borrowBookWithNoUserFound() {

        Mockito.when(
                userRepository.findByUsername(
                        Mockito.anyString()
                )
        ).thenReturn(Optional.empty());
        Assertions.assertThrows(NoUserFound.class,
                ()->{
                    orderManager.borrowBook(
                           new OrderDto()
                    );
                }

                );
    }

    @Test()
    void borrowBookWithExistingOrder() {
        Order testOrder = Order.builder().book(new Book()).build();
        User testUser = User.builder().username("user").order(testOrder).build();
        Mockito.when(
                userRepository.findByUsername(
                        Mockito.anyString()
                )
        ).thenReturn(Optional.of(testUser));
        Assertions.assertThrows(OrderAlreadyExists.class,
                ()->{
                    orderManager.borrowBook(
                            new OrderDto("title", "user")
                    );
                }

        );
    }

    @Test()
    void borrowBookWithUserHavingFine() {
        Fine fine = Fine.builder().amount(10).build();
        User testUser = User.builder().username("user").fine(fine).build();
        Mockito.when(
                userRepository.findByUsername(
                        Mockito.anyString()
                )
        ).thenReturn(Optional.of(testUser));
        Assertions.assertThrows(NonZeroFine.class,
                ()->{
                    orderManager.borrowBook(
                            new OrderDto("title", "user")
                    );
                }

        );
    }

    @Test()
    void borrowBookWithNoValidBook() {
        Fine fine = Fine.builder().amount(0).build();
        User testUser = User.builder().username("user").fine(fine).build();

        Mockito.when(
                userRepository.findByUsername(
                        "user"
                )
        ).thenReturn(Optional.of(testUser));

        Mockito.when(
                bookRepository.findByTitle("title")
        ).thenReturn(Optional.empty());
        Assertions.assertThrows(BookNotFound.class,
                ()->{
                    orderManager.borrowBook(
                            new OrderDto("title", "user")
                    );
                }

        );
    }

    @Test()
    void borrowBookWithBookNotAvailable() {
        Fine fine = Fine.builder().amount(0).build();
        User testUser = User.builder().username("user").fine(fine).build();
        Book book = Book.builder().qty(0).build();

        Mockito.when(
                userRepository.findByUsername(
                        "user"
                )
        ).thenReturn(Optional.of(testUser));

        Mockito.when(
                bookRepository.findByTitle("title")
        ).thenReturn(Optional.of(book));
        Assertions.assertThrows(BookNotAvailable.class,
                ()->{
                    orderManager.borrowBook(
                            new OrderDto("title", "user")
                    );
                }

        );
    }

    @Test()
    void borrowBookSuccess() {
        Fine fine = Fine.builder().amount(0).build();
        User testUser = User.builder().username("user").fine(fine).build();
        Book book = Book.builder().qty(2).build();

        Mockito.when(
                userRepository.findByUsername(
                        "user"
                )
        ).thenReturn(Optional.of(testUser));

//        Mockito.when(
//                userRepository.save(
//                        Mockito.any(User.class)
//                )
//        ).thenReturn(testUser);


        Mockito.when(
                bookRepository.findByTitle("title")
        ).thenReturn(Optional.of(book));

        try {
            orderManager.borrowBook(new OrderDto("title", "user"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        book.setQty(1);

        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
        Mockito.verify(bookRepository, Mockito.times(1)).save(book);

    }
}