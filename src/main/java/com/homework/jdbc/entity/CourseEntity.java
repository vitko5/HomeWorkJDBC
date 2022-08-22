package com.homework.jdbc.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseEntity {
    UUID id;
    String name;
    String duration;
}
