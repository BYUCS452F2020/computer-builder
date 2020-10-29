package dao;

import models.User;

import java.sql.*;

public class UserDAO {

    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(User user) throws DataAccessException {
        String sql = "INSERT INTO Users (username, first_name, last_name, " +
                "password, email) VALUES(?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setInt(4, user.getPassword());
            stmt.setString(5, user.getEmail());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting into the Users table in database");
        }
    }

    public User find(String username, String password) throws DataAccessException {
        User user;
        ResultSet rs = null;
        String sql = "SELECT * FROM Users WHERE username = ?;";

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            System.out.println("finding user with name: " + username);
            rs = stmt.executeQuery();
            if(rs.next()) {
                user = new User(rs.getString("username"), rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"));
                user.setPassword(password);
                int queryPassword = rs.getInt("password");
                System.out.println("Password retrieved: " + queryPassword);
                if(user.getPassword() == queryPassword) {
                    return user;
                }
                else {
                    throw new DataAccessException("Incorrect password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while querying a user");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
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
        String sql = "DELETE FROM Users WHERE username = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userID);
            count = stmt.executeUpdate();

            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while deleting one user");
        }
    }
}
