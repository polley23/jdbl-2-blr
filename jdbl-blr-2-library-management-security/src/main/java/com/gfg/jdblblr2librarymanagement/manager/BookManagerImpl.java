package com.gfg.jdblblr2librarymanagement.manager;

import com.gfg.jdblblr2librarymanagement.entity.Book;
import com.gfg.jdblblr2librarymanagement.entity._Book;
import com.gfg.jdblblr2librarymanagement.model.BookDto;
import com.gfg.jdblblr2librarymanagement.repository.BookRepository;
import com.gfg.jdblblr2librarymanagement.repository._BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookManagerImpl implements BookManager{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private _BookRepository bookCacheRepository;
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

        Optional<_Book> _bookOpt = bookCacheRepository.findByTitle(title);
        if(_bookOpt.isEmpty()){
            Book book = bookRepository.findByTitle(title).orElseThrow(
                    ()-> new Exception("No such title is present.")
            );
            bookCacheRepository.save(
                    _Book.builder().qty(book.getQty())
                            .author(book.getAuthor())
                            .title(book.getTitle())
                            .build()
            );
            return BookDto.builder()
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .qty(book.getQty())
                    .build();

        }
        _Book _book = _bookOpt.get();
        return BookDto.builder()
                .title(_book.getTitle())
                .author(_book.getAuthor())
                .qty(_book.getQty())
                .build();
    }
}
