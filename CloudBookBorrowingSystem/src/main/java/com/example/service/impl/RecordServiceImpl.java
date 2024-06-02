package com.example.service.impl;

import com.example.domain.Book;
import com.example.domain.PageBean;
import com.example.domain.Record;

import com.example.domain.User;
import com.example.mapper.RecordMapper;
import com.example.service.RecordService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Resource
    private RecordMapper recordMapper;

    @Override
    public int addRecord(Record record) {
        return recordMapper.insert(record);
    }

    @Override
    public List<Record> searchRecord(int pageNum, int pageSize,String record_borrower,String record_bookname) {
        pageNum = (pageNum-1) * pageSize;
        List<Record> records = recordMapper.searchRecord(pageNum,pageSize,record_borrower,record_bookname);
        PageInfo<Record> pageInfo = new PageInfo<Record>(records);
        long total = pageInfo.getTotal();
        return pageInfo.getList();
    }

}
