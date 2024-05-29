package com.example.service;

import com.example.domain.Book;
import com.example.domain.PageBean;

public interface BookService {
    PageBean page(Integer startIndex, Integer pageSize,Integer book_status, String book_name, String book_author);
    PageBean selectNewBooks();

    void updateBook(Book book);

    void insetbook(Book book);

    Book FindById(Integer id);

}
