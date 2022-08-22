package com.homework.jdbc.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentEntity {
    UUID id;
    String firstName;
    String lastName;
    Date dateOfBirth;
    UUID book_id;
    UUID course_id;
}
