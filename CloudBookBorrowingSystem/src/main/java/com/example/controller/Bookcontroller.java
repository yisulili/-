package com.example.controller;

import com.example.domain.Book;
import com.example.domain.PageBean;
import com.example.domain.Resp;
import com.example.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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

}
