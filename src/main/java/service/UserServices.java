package service;


import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import models.User;
import models.request.*;
import models.result.*;

import java.sql.Connection;


/**
 * User services class that handles all requests and responses to the database from the server.
 */
public class UserServices {
    public RegisterResult register(RegisterRequest r) throws DataAccessException
    {
        System.out.println(r.getUsername());
        Database db = new Database();

        Connection conn = db.openConnection();
        //System.out.println(conn.toString());
        try {
            UserDAO userDao = new UserDAO(conn);
            User u = new User(r.getUsername(), r.getFirstName(),r.getLastName(), r.getEmail());
            u.setPassword(r.getPassword());
            userDao.insert(u);
            db.closeConnection(true);
            System.out.println("success");
            return new RegisterResult(r.getUsername());
        } catch (DataAccessException e) {
            db.closeConnection(false);
            return new RegisterResult(e.getMessage());
        }
    }

    public LoginResult login(LoginRequest r) throws DataAccessException
    {
        System.out.println(r.getUsername());
        Database db = new Database();

         /*Connection conn = db.openConnection();
        //System.out.println(conn.toString());
        INCOMPLETE, see TO-DO below
        try {
            UserDAO userDao = new UserDAO(conn);
            //TODO Add find to UserDAO for login
             User u = userDao.find(r.getUsername());
            if (u == null) {
                db.closeConnection(false);
                return new LoginResult("Bad login credentials (cant find you)");
            }
            if (!u.getPassword().equals(r.getPassword()))
            {
                db.closeConnection(false);
                return new LoginResult("Bad login credentials (bad password)");
            }
            System.out.println("success");
            db.closeConnection(true);
            return new LoginResult(r.getUsername());


        } catch (DataAccessException e) {
            db.closeConnection(false);
            return new LoginResult("Bad login credentials");
        }
        */
        return null;
    }
}

