package com.homework.jdbc.dao;

import com.homework.jdbc.entity.StudentEntity;
import com.homework.jdbc.exception.NoSuchStudentException;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface StudentDao {

    void addStudent (String firstName, String lastName, Date dateOfBirth, UUID book_id, UUID course_id);

    StudentEntity getStudentById(UUID id) throws NoSuchStudentException;
    List<StudentEntity> getAllStudents() throws SQLException, NoSuchStudentException;

    void updateStudent (UUID id,String firstName, String lastName, Date dateOfBirth, UUID book_id, UUID course_id);

}
