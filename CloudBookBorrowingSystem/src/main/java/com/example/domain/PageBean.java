package com.example.domain;

import com.github.pagehelper.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/*
* 分页查询的结果封装类
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {
    private long total; //总书籍记录数
    private List<Book> rows; //书籍列表

}
