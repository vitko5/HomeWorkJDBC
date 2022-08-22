package com.homework.jdbc.dao;

import com.homework.jdbc.config.DatabaseConfig;
import com.homework.jdbc.entity.StudentEntity;
import com.homework.jdbc.exception.NoSuchStudentException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentDaoImpl implements StudentDao {

    private static final Connection connection;

    static {
        try {
            connection = DatabaseConfig.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addStudent(String firstName, String lastName, Date dateOfBirth, UUID book_id, UUID course_id) {
        assert connection != null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO student (id,firstName,lastName,dateOfBirth,book_id,course_id) VALUES (?,?,?,?,?,?)")) {
            preparedStatement.setObject(1, UUID.randomUUID());
            preparedStatement.setString(2,firstName);
            preparedStatement.setString(3,lastName);
            preparedStatement.setDate(4,dateOfBirth);
            preparedStatement.setObject(5,book_id);
            preparedStatement.setObject(6,course_id);
            System.out.println(preparedStatement.executeUpdate());
        } catch (SQLException e) {
            System.err.println("Error. Can't add studente. Some error in parameters.");
            e.printStackTrace();
        }
    }

    @Override
    public StudentEntity getStudentById(UUID id) throws NoSuchStudentException {

        assert connection != null;
        StudentEntity studentEntity;

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE id = ?::uuid")) {
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Date dateOfBirth = resultSet.getDate("dateOfBirth");
                UUID book_id = resultSet.getObject("book_id",java.util.UUID.class);
                UUID course_id = resultSet.getObject("course_id",java.util.UUID.class);
                studentEntity = new StudentEntity(id,firstName,lastName,dateOfBirth,book_id,course_id);
            }
            else
                throw new NoSuchStudentException("Error. There is no student in database with id: " + id);

        } catch (SQLException e) {
            System.err.println("Cannot find student with that id");
            e.printStackTrace();
            return null;
        }
        return studentEntity;

    }

    @Override
    public List<StudentEntity> getAllStudents() throws SQLException {
        assert connection != null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
        List<StudentEntity> studentEntities = new ArrayList<>();
        while (resultSet.next()) {
            UUID id = resultSet.getObject("id",java.util.UUID.class);
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            Date dateOfBirth = resultSet.getDate("dateOfBirth");
            UUID book_id = resultSet.getObject("book_id",java.util.UUID.class);
            UUID course_id = resultSet.getObject("course_id",java.util.UUID.class);
            studentEntities.add(new StudentEntity(id,firstName,lastName,dateOfBirth,book_id,course_id));
        }
        return studentEntities;
    }

    @Override
    public void updateStudent(UUID id,String firstName, String lastName, Date dateOfBirth, UUID book_id, UUID course_id) {
        assert connection != null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE student SET firstName=?,lastName=?,dateOfBirth=?,book_id=?,course_id=? WHERE id=?")) {
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setDate(3,dateOfBirth);
            preparedStatement.setObject(4,book_id);
            preparedStatement.setObject(5,course_id);
            preparedStatement.setObject(6,id);
            System.out.println(preparedStatement.executeUpdate());
        } catch (SQLException e) {
            System.err.println("Error. Can't update student. Some error in parameters.");
            e.printStackTrace();
        }
    }
}
