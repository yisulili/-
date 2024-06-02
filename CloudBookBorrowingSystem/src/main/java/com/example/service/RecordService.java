package com.example.service;

import com.example.domain.PageBean;
import com.example.domain.Record;
import com.example.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface RecordService {
    int addRecord(Record record);

    List<Record> searchRecord(@Param("pageNum")int pageNum, @Param("pageSize")int pageSize,String record_borrower,String record_bookname);
}
