package com.homework.jdbc.dao;

import com.homework.jdbc.entity.CourseEntity;
import com.homework.jdbc.exception.NoSuchCourseException;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface CourseDao {

    void addCourse(String name, String duration);
    List<CourseEntity> getAllCourse() throws SQLException;
    CourseEntity getCourseById(UUID courseId) throws SQLException, NoSuchCourseException;
    void updateCourse (UUID courseId, String courseName, String courseDuration);

}
