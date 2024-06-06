package com.example.controller;

import com.example.domain.Book;
import com.example.domain.PageBean;
import com.example.domain.Resp;
import com.example.domain.User;
import com.example.service.BookService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Bookcontroller {

    @Resource
    private BookService bookService;

    /*<图书借阅>
    * 按页返回书籍
    * 分类查询
    * */
    @GetMapping("/getPageBooks")
    public Resp getPageBooks(@RequestParam(defaultValue = "1") Integer pageNum, //开始的页数，
                        @RequestParam(defaultValue = "10") Integer pageSize,  //一页的书籍记录
                             Integer book_status, String book_name, String book_author
    ){
        System.out.println("分页查询" + pageNum +" " + pageSize);
        PageBean pageBean = bookService.page(pageNum,pageSize,book_status, book_name, book_author);
        return new Resp(200,"查询成功",pageBean);

    }

    /*
    * 新书推荐
    * */
    @GetMapping("/selectNewBooks")
    public Resp selectNewBooks(){
        return new Resp(200,"查询成功",bookService.selectNewBooks());
    }

    /*<编辑图书>
    * 面向管理员
    * */
    @PutMapping("/editBook")
    public Resp editBook(@RequestBody Book book){
        bookService.updateBook(book);
        return new Resp(200,"修改成功",null);
    }


    /*查看书籍信息*/
    @GetMapping("/findById/{id}")
    public Resp getByID(@PathVariable Integer id){
        Book book =  bookService.FindById(id);
        return new Resp(200,"查询成功",book);
    }

    /*新添书籍*/
    @PostMapping("/insert")
    public Resp insert(@RequestBody Book book){
        bookService.insetbook(book);
        return new Resp(200,"新增成功",null);
    }

    /*借阅书籍(面向用户)*/
    @PostMapping("/borrowBook")
    public Resp borroBook(@RequestBody Book book){
        bookService.borrowBook(book);
        return new Resp(200,"借阅成功,请按时归还",null);
    }
    /*当前借阅 （面向用户）*/
    @PostMapping("/selectMyborrow")
    public Resp selectMyborrow(String book_borrower,String book_name, String book_author){
        List<Book> books = bookService.selectMyborrow(book_borrower,book_name,book_author);
        return new Resp(200,"查询成功",books);
    }
    @PostMapping("/selectMyborrowDesc")
    public Resp selectMyborrowDesc(String book_borrower,String book_name, String book_author){
        List<Book> books = bookService.selectMyborrow(book_borrower,book_name,book_author);
        return new Resp(200,"查询成功",books);
    }

    @PostMapping("/selectMyborrowAsc")
    public Resp selectMyborrowAsc(String book_borrower,String book_name, String book_author){
        List<Book> books = bookService.selectMyborrow(book_borrower,book_name,book_author);
        return new Resp(200,"查询成功",books);
    }

    /*查询当前借阅 管理员的*/
    @PostMapping("/selectUserBorrow")
    public Resp selectUserBorrow(@RequestParam(defaultValue = "1") Integer pageNum, //开始的页数，
                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                 String book_borrower,String book_name, String book_author){
        PageBean books = bookService.selectUserBorrow(pageNum,pageSize ,book_borrower,book_name,book_author);
        return new Resp(200,"查询成功",books);
    }

    /*用户的归还图书*/
    @PostMapping("/returnBook")
    public Resp returnBook(Integer id){
        boolean b = bookService.returnBook(id);
        return new Resp(200,"请等待管理员确认归还",b);
    }
    /*管理员的确认归还图书*/
    @PostMapping("/confirmReturnBook")
    public Resp confirmReturnBook(Integer id){
        int b = bookService.confirmReturnBook(id);
        return new Resp(200,"该书籍可借阅",b);
    }


}
