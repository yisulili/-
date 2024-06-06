package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    private Integer id;
    private String record_bookname;
    private String record_borrower;
    private String record_borrowtime;
    private String record_remandtime;
    private String record_bookisbn;
}
