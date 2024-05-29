package com.example.mapper;

import com.example.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where user_email = #{user_email} and " +
            "user_password = #{user_password} and user_status != '1'")
    User getUser(User user);



    @Insert("insert into user(user_name, user_password, user_email,user_role,user_status) values (#{user_name},#{user_password},#{user_email},#{user_role},#{user_status})")
    void insert(User user);

}
