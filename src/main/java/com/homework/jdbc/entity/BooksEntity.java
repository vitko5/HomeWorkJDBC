package com.homework.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class BooksEntity {
    private UUID id;
    private String bookName;
    private Integer bookSize;
    private String bookISBN;
    private String bookAuthor;
    private Date bookPublication;
}
