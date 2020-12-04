package service;


import com.mongodb.client.MongoDatabase;
import dao.*;
import models.User;
import models.request.*;
import models.result.*;

import javax.swing.text.html.StyleSheet;
import java.sql.Connection;


/**
 * User services class that handles all requests and responses to the database from the server.
 */
public class UserServices {
    public RegisterResult register(RegisterRequest r) throws DataAccessException
    {
        System.out.println("registering: " + r.getUsername());
        //Old SQLite DB
        //Database db = new Database();

        //New mongoDB datbase
        MongoDatabase db = MongoDBDatabase.getDB();

        System.out.println("got db");

        //Don't need to make "connections" anymore
        //Connection conn = db.openConnection();
        //System.out.println(conn.toString());
        try {
            //Code for sqlite database
            //UserDAO userDao = new UserDAO(conn);

            //New MongoDB code
            MongoDBUserDAO userDao = new MongoDBUserDAO();

            System.out.println("made mongodbuserdao");

            User u = new User(r.getUsername(), r.getFirstName(),r.getLastName(), r.getEmail());
            u.setPassword(r.getPassword());
            userDao.insert(u);

            //Don't close connections anymore
            //db.closeConnection(true);
            System.out.println("success");
            return new RegisterResult(r.getUsername());
        } catch (DataAccessException e) {
            //db.closeConnection(false);
            return new RegisterResult(e.getMessage());
        }
    }

    public LoginResult login(LoginRequest r) throws DataAccessException
    {
        System.out.println(r.getUsername());
        //Database db = new Database();

        //Connection conn = db.openConnection();

        try {
            //UserDAO userDao = new UserDAO(conn);
            MongoDBUserDAO userDAO = new MongoDBUserDAO();
            //TODO Add find to UserDAO for login
             User u = userDAO.find(r.getUsername(), r.getPassword());
            if (u == null) {
                //db.closeConnection(false);
                return new LoginResult("Badlogincredentials");
            }

            System.out.println("success");
            //db.closeConnection(true);
            return new LoginResult(r.getUsername());


        } catch (DataAccessException e) {
            //db.closeConnection(false);
            return new LoginResult(null);
        }

    }
}

