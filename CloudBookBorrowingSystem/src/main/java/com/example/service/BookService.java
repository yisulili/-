package com.example.service;

import com.example.domain.Book;
import com.example.domain.PageBean;
import com.example.domain.User;

import java.util.List;

public interface BookService {
    PageBean page(Integer startIndex, Integer pageSize,Integer book_status, String book_name, String book_author);
    PageBean selectNewBooks();

    void updateBook(Book book);

    void insetbook(Book book);

    Book FindById(Integer id);

    void borrowBook(Book book);

    List<Book> selectMyborrow(String book_borrower,String book_name, String book_author);
    public boolean returnBook(int id);
    public boolean returnBookU(int id);
}
