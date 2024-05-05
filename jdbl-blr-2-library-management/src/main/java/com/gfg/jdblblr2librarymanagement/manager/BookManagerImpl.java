package com.gfg.jdblblr2librarymanagement.manager;

import com.gfg.jdblblr2librarymanagement.entity.Book;
import com.gfg.jdblblr2librarymanagement.model.BookDto;
import com.gfg.jdblblr2librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookManagerImpl implements BookManager{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public void addBook(BookDto bookReq) {
        Book book = Book.builder().author(bookReq.getAuthor())
                .qty(bookReq.getQty())
                .title(bookReq.getTitle())
                .build();
        bookRepository.save(book);
    }

    @Override
    public BookDto getBooks(String title) throws Exception {
        Book book = bookRepository.findByTitle(title).orElseThrow(
                ()-> new Exception("No such title is present.")
        );
//        return new BookDto(
//                book.getTitle(),
//                book.getAuthor(),
//                book.getQty()
//        );
        return BookDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .qty(book.getQty())
                .build();
    }
}
