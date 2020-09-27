package dao;

import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {

    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(User user) throws DataAccessException {
        String sql = "INSERT INTO Users (user_id, first_name, last_name, " +
                "username, password, email) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUserID());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getUsername());
            stmt.setInt(5, user.getPassword());
            stmt.setString(6, user.getEmail());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting into the Users table in database");
        }
    }

    public int deleteAll() throws DataAccessException {
        int count;
        String sql = "DELETE FROM Users";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            count = stmt.executeUpdate();

            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while deleting all users");
        }
    }

    public int deleteOne(String userID) throws DataAccessException {
        int count;
        String sql = "DELETE FROM Users WHERE Username = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userID);
            count = stmt.executeUpdate();

            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while deleting a user");
        }
    }
}
