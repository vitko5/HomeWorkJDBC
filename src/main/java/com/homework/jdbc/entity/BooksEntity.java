package com.homework.jdbc.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BooksEntity {
    UUID id;
    String bookName;
    Integer bookSize;
    String bookISBN;
    String bookAuthor;
    Date bookPublication;
}
