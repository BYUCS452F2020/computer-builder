package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database class performs the connection between the data access objects and the database.
 */
public class Database {

    Connection connection;

    /**
     * Opens a connection to a sqlite database.
     * @return the connection to the sqlite database.
     * @throws DataAccessException - if there was a problem connecting to the database.
     */
    public Connection openConnection() throws DataAccessException {
        try {
            String dbName = "computerbuilder.sqlite";

            final String CONNECTION_URL = "jdbc:sqlite:" + dbName;

            connection = DriverManager.getConnection(CONNECTION_URL);

            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to open connection to database");
        }
        return connection;
    }

    public Connection getConnection() throws DataAccessException {
        if(connection == null) {
            return openConnection();
        }
        else {
            return connection;
        }
    }

    public boolean isOpenConnection() {
        return connection != null;
    }

    /**
     * Allows for a data access object to close the connection to the database.
     * @param commit determines if the database should commit changes.
     * @throws DataAccessException if the connection is bad.
     */
    public void closeConnection(boolean commit) throws DataAccessException {
        try {
            if (commit) {
                connection.commit();
            }
            else {
                connection.rollback();
            }

            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to close database connection");
        }
    }

    /**
     * Creates the tables for the sqlite database.
     * @throws DataAccessException if connection or execution fails.
     */
    //TODO: Add build table to methods
    public void createTables() throws DataAccessException {
        try (Statement stmt = connection.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS Users " +
                    "(" +
                    "username text not null unique, " +
                    "first_name text not null, " +
                    "last_name text not null, " +
                    "password bigint not null, " +
                    "email text not null, " +
                    "primary key (username)" +
                    ");" +
                    "CREATE TABLE IF NOT EXISTS Components " +
                    "(" +
                    "component_id text not null unique, " +
                    "component_name text not null, " +
                    "component_type text not null, " +
                    "manufacturer text not null, " +
                    "performance_rating int not null, " +
                    "price decimal(10,2) not null, " +
                    "cpu_family text, " +
                    "tdp bigint, " +
                    "image_url text, " +
                    "primary key (component_id)" +
                    ");" +
                    "CREATE TABLE IF NOT EXISTS Builds " +
                    "(" +
                    "build_id text not null unique, " +
                    "user_id text not null, " +
                    "build_name text not null, " +
                    "motherboard text not null, " +
                    "processor text not null, " +
                    "cpu_cooler text not null, " +
                    "memory text not null, " +
                    "storage text not null, " +
                    "graphics_card text, " +
                    "power_supply text not null, " +
                    "pc_case text not null, " +
                    "primary key (build_id)" +
                    ")";
                    // Todo: Was there another component that was optional besides the graphics_card?
                    // Todo: Enable foreign key constraints in Builds table?
                    // FOREIGN KEY(user_id) REFERENCES Users(username)

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("SQL Error encountered while creating tables");
        }
    }

    /**
     * Clears all entities from the database tables.
     * @throws DataAccessException if connection or execution fails.
     */
    public void clearTables() throws DataAccessException {
        try (Statement stmt = connection.createStatement()) {
            String sql = "DELETE FROM Users; " +
                    "DELETE FROM Components;" +
                    "DELETE FROM Builds";

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }

    /**
     * Deletes the tables from the sqlite database.
     * @throws DataAccessException if connection or execution fails.
     */
    public void deleteTables() throws DataAccessException {
        try (Statement stmt = connection.createStatement()) {
            String sql = "DROP TABLE IF EXISTS Users; " +
                    "DROP TABLE IF EXISTS Components;" +
                    "DROP TABLE IF EXISTS Builds";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("SQL Error encountered while dropping tables");
        }
    }
}