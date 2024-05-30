package com.example.service.impl;

import com.example.domain.Book;
import com.example.domain.PageBean;
import com.example.domain.Resp;
import com.example.domain.User;
import com.example.mapper.BookMapper;
import com.example.service.BookService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        book.setBook_uploadtime(dateFormat.format(new Date()));
        bookMapper.insert(book);
    }

    @Override
    public Book FindById(Integer id) {
        return   bookMapper.getByID(id);
    }

    /*借阅图书*/
    @Override
    public void borrowBook(Book book) {
        book.setBook_status("1");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        book.setBook_borrowtime(dateFormat.format(new Date()));
        bookMapper.editBook(book);
    }




    @Override
    public List<Book> selectMyborrow(String book_borrower,String book_name, String book_author) {
        return bookMapper.selecMyborrow(book_borrower,book_name,book_author);
    }

    /*归还图书，如果是用户则将书籍状态变为归还中，如果是管理员则将书籍状态改为可借阅*/

    public boolean returnBook(int id)
    {
        Book book = bookMapper.getByID(id);
        book.setBook_status("2");
        bookMapper.borrowBook(book);
        return true;
    }
    public boolean returnBookU(int id){
        Book book = bookMapper.getByID(id);
        book.setBook_returntime(null);
        book.setBook_borrower(null);
        book.setBook_borrowtime(null);
        book.setBook_status("0");
        bookMapper.borrowBook(book);
        return true;
    }

}
