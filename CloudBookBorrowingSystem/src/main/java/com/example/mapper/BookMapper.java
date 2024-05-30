package com.example.mapper;

import com.example.domain.Book;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@Mapper
public interface BookMapper {
    /*
    * 返回所有书籍，最新排序
    * */
    @Select("select * from book where book_status != '3' order by book_uploadtime DESC limit 1,5")
    Page<Book> selectNewBook();

    /*
    * 查询书籍总数
    **/
    @Select("select count(*) from book ")
    long count();

    /*查询图书（面向用户）
    * 图书借阅模块块
    * */
    //Page<Book> getBookByPage(Integer book_id, String book_name, String book_author);
   @Select("select * from book where (#{book_name} is null or book_name like concat('%', #{book_name}, '%')) " +
           "and (#{book_author} is null or book_author  like concat('%', #{book_author}, '%'))" +
           "and (#{book_status} is null or book_status =  #{book_status} )"
   )
    List<Book> getBookByPage(@Param("pageNum")int pageNum, @Param("pageSize")int pageSize, String book_name, String book_author,Integer book_status);


   /*
   * 根据id返回书籍信息
   * */
    @Select("select * from book where book_id = #{book_id}")
    Book getByID(Integer book_id);

    /*编辑图书 */
    int editBook(Book book);

//借阅图书（面向用户） 归还图书
    @Update("update book set book_borrower = #{book_borrower},book_borrowtime = #{book_borrowtime},book_returntime = #{book_returntime}," +
            "book_status = #{book_status} where book_id = #{book_id}")
    int borrowBook(Book book);

    /*新增书籍*/
    @Insert("insert into book(book_name,book_isbn,book_press,book_author,book_pagination,book_price,book_uploadtime,book_status) " +
            "values (#{book_name},#{book_isbn},#{book_press},#{book_author},#{book_pagination},#{book_price},#{book_uploadtime},#{book_status})")
    int insert(Book book);

    /*查询当前借阅（面向用户）普通排序
    */
    @Select("select * from book where (#{book_name} is null or book_name like concat('%', #{book_name}, '%')) " +
            "and (#{book_author} is null or book_author  like concat('%', #{book_author}, '%'))" +
            "and book_status in (1,2)")
    List<Book> selecMyborrow(String book_borrower,String book_name, String book_author);
    /*查询当前借阅（面向用户）降序
     */
    @Select("select * from book where (#{book_name} is null or book_name like concat('%', #{book_name}, '%')) " +
            "and (#{book_author} is null or book_author  like concat('%', #{book_author}, '%'))" +
            "and book_status in (1,2) order by book_borrowtime desc")
    List<Book> selecMyborrowDesc(String book_borrower,String book_name, String book_author);
    /*查询当前借阅（面向用户）升序
     */
    @Select("select * from book where (#{book_name} is null or book_name like concat('%', #{book_name}, '%')) " +
            "and (#{book_author} is null or book_author  like concat('%', #{book_author}, '%'))" +
            "and book_status in (1,2) order by book_borrowtime asc")
    List<Book> selecMyborrowAsc(String book_borrower,String book_name, String book_author);
}
