package com.example.service.impl;

import com.example.domain.Book;
import com.example.domain.PageBean;
import com.example.mapper.BookMapper;
import com.example.service.BookService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Service
public class BookServiceImpl implements BookService {
    @Resource
    BookMapper bookMapper;


//图书查询（面向用户）
    @Override
    public PageBean page(Integer pageNum, Integer pageSize, Integer book_status, String book_name, String book_author) {
        //拿第一页，每页3条数据
        pageNum = (pageNum-1) * pageSize;
        List<Book> book_list = bookMapper.getBookByPage(pageNum,pageSize,book_name,book_author,book_status);
        PageInfo<Book> pageInfo = new PageInfo<Book>(book_list);
        long total = pageInfo.getTotal();
        System.out.println(total);
        return new PageBean(total,pageInfo.getList());
    }

    /*
    * 获取新书推荐
    * */
    public PageBean selectNewBooks()
    {
        Page<Book> books = bookMapper.selectNewBook();
        return  new PageBean(books.getTotal(),books.getResult());
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.editBook(book);
    }

    //新增书籍
    @Override
    public void insetbook(Book book) {
        book.setBook_status("0");
        book.setBook_uploadtime(String.valueOf(LocalDateTime.now()));
    }

    @Override
    public Book FindById(Integer id) {
        return   bookMapper.getByID(id);
    }


}
