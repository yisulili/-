package com.example.domain;
import lombok.Data;


@Data
public class User {

    private Integer user_id;
    private String user_name;
    private String user_password;
    private String user_email;
    private String user_role;
    private String user_status;


}
