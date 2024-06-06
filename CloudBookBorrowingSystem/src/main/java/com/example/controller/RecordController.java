package com.example.controller;

import com.example.domain.Record;
import com.example.domain.Resp;
import com.example.service.RecordService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecordController {
    @Resource
    private RecordService recordService;


/*借阅记录模块
* 查阅借阅记录*/
    @PostMapping("/searchRecord")
    private Resp searchRecord(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize, String record_borrower,String record_bookname){
        List<Record> records = recordService.searchRecord(pageNum,pageSize,record_borrower,record_bookname);
        System.out.println("分页查询" + pageNum +" " + pageSize);
        return new Resp(200,"查询成功",records);
    }
}
