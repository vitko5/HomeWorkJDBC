package com.homework.jdbc.dao;

import com.homework.jdbc.config.DatabaseConfig;
import com.homework.jdbc.entity.CourseEntity;
import com.homework.jdbc.exception.NoSuchCourseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CourseDaoImpl implements CourseDao {

    private static final Connection connection;

    static {
        try {
            connection = DatabaseConfig.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCourse(String name, String duration) {
        assert connection != null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO course (id,courseName,courseDuration) VALUES (?,?,?)")) {
            preparedStatement.setObject(1, UUID.randomUUID());
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,duration);
            System.out.println(preparedStatement.executeUpdate());
        } catch (SQLException e) {
            System.err.println("Error. Can't add course. Some error in parameters.");
            e.printStackTrace();
        }
    }

    @Override
    public List<CourseEntity> getAllCourse() throws SQLException {
        assert connection != null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM course");
        List<CourseEntity> courseEntities = new ArrayList<>();
        while (resultSet.next()) {
            UUID id = resultSet.getObject("id",java.util.UUID.class);
            String courseName = resultSet.getString("courseName");
            String courseDuration = resultSet.getString("courseDuration");
            courseEntities.add(new CourseEntity(id,courseName,courseDuration));
        }
        return courseEntities;
    }

    @Override
    public CourseEntity getCourseById(UUID courseId) throws NoSuchCourseException {

        assert connection != null;
        CourseEntity courseEntity;

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE id = ?::uuid")) {
            preparedStatement.setObject(1,courseId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String courseName = resultSet.getString("courseName");
                String courseDuration = resultSet.getString("courseDuration");
                courseEntity = new CourseEntity(courseId,courseName,courseDuration);
            }
            else
                throw new NoSuchCourseException("Error. There is no course in database with id: " + courseId);

        } catch (SQLException e) {
            System.err.println("Cannot find course with that id");
            e.printStackTrace();
            return null;
        }
        return courseEntity;

    }

    @Override
    public void updateCourse(UUID courseId, String courseName, String courseDuration) {
        assert connection != null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE course SET courseName=?,courseDuration=? WHERE id=?")) {
            preparedStatement.setString(1,courseName);
            preparedStatement.setString(2,courseDuration);
            preparedStatement.setObject(3,courseId);
            System.out.println(preparedStatement.executeUpdate());
        } catch (SQLException e) {
            System.err.println("Error. Can't update student. Some error in parameters.");
            e.printStackTrace();
        }
    }
}
