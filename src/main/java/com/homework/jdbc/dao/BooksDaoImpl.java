package com.homework.jdbc.dao;

import com.homework.jdbc.config.DatabaseConfig;
import com.homework.jdbc.entity.BooksEntity;
import com.homework.jdbc.exception.NoSuchBookException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BooksDaoImpl implements BooksDao {

    private static final Connection connection;

    static {
        try {
            connection = DatabaseConfig.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BooksEntity> readAllBooks() throws SQLException {
        assert connection != null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
        List<BooksEntity> booksEntities = new ArrayList<>();
        while (resultSet.next()) {
            UUID id = resultSet.getObject("id",java.util.UUID.class);
            String bookName = resultSet.getString("bookName");
            Integer bookSize = resultSet.getInt("bookSize");
            String bookISBN = resultSet.getString("bookISBN");
            String bookAuthor = resultSet.getString("bookAuthor");
            Date bookPublication = resultSet.getDate("bookPublication");
            booksEntities.add(new BooksEntity(id,bookName,bookSize,bookISBN,bookAuthor,bookPublication));
        }
        return booksEntities;
    }

    @Override
    public BooksEntity readBooksById(UUID bookId) throws NoSuchBookException {
        assert connection != null;
        BooksEntity booksEntity;

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books WHERE id = ?::uuid")) {
            preparedStatement.setObject(1,bookId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String bookName = resultSet.getString("bookName");
                Integer bookSize = resultSet.getInt("bookSize");
                String bookISBN = resultSet.getString("bookISBN");
                String bookAuthor = resultSet.getString("bookAuthor");
                Date bookPublication = resultSet.getDate("bookPublication");
                booksEntity = new BooksEntity(bookId,bookName,bookSize,bookISBN,bookAuthor,bookPublication);
            }
            else
                throw new NoSuchBookException("Error. There is no book in database with id: " + bookId);

        } catch (SQLException e) {
            System.err.println("Cannot find book with that id");
            e.printStackTrace();
            return null;
        }
        return booksEntity;
    }

    @Override
    public void addBook(String bookName, int bookSize, String bookISBN, String bookAuthor, Date bookDatePublication) {
        assert connection != null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO books (id,bookName,bookSize,bookISBN,bookAuthor,bookPublication) VALUES (?,?,?,?,?,?)")) {
            preparedStatement.setObject(1,UUID.randomUUID());
            preparedStatement.setString(2,bookName);
            preparedStatement.setInt(3,bookSize);
            preparedStatement.setString(4,bookISBN);
            preparedStatement.setString(5,bookAuthor);
            preparedStatement.setDate(6,bookDatePublication);
            System.out.println(preparedStatement.executeUpdate());
        } catch (SQLException e) {
            System.err.println("Error. Can't add book. Some error in parameters.");
            e.printStackTrace();
        }
    }

    @Override
    public void updateBook(UUID bookId, String bookName, int bookSize, String bookISBN, String bookAuthor, Date bookDatePublication) {
        assert connection != null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE books SET bookName=?,bookSize=?,bookISBN=?,bookAuthor=?,bookPublication=? WHERE id=?")) {
            preparedStatement.setString(1,bookName);
            preparedStatement.setInt(2,bookSize);
            preparedStatement.setString(3,bookISBN);
            preparedStatement.setString(4,bookAuthor);
            preparedStatement.setDate(5,bookDatePublication);
            preparedStatement.setObject(6,bookId);
            System.out.println(preparedStatement.executeUpdate());
        } catch (SQLException e) {
            System.err.println("Error. Can't update book. Some error in parameters.");
            e.printStackTrace();
        }
    }
}
