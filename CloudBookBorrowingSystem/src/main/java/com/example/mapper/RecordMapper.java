package com.example.mapper;

import com.example.domain.Book;
import com.example.domain.PageBean;
import com.example.domain.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecordMapper {

    @Insert("insert into record(record_bookname, record_bookisbn, record_borrower, record_borrowtime, record_remandtime) " +
            "values(#{record_bookname},#{record_bookisbn},#{record_borrower},#{record_borrowtime},#{record_remandtime})")
    int insert(Record record);

    @Select("select *from record where (#{record_borrower} is null or record_borrower like concat('%',record_borrower,'%'))" +
            "and (#{record_bookname} is null or record_bookname like concat('%',record_bookname,'%'))" +
            "order by record_remandtime desc")
    List<Record> searchRecord(@Param("pageNum")int pageNum, @Param("pageSize")int pageSize, String record_borrower,String record_bookname);

}
