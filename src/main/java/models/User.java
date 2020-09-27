package models;

import java.util.Map;

public class User {

    private String userID;
    private String firstName;
    private String lastName;
    private String username;
    private int password;
    private String email;
    //private Map<String, Build> builds;

    public User(String userID, String firstName, String lastName, String username,
                String email) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
    }

    private int hashPassword(String password) {
        int hash = 1;
        for(int i = 0; i < password.length(); i++) {
            if((i % 2) == 0) {
                hash *= password.charAt(i);
            }
            else {
                hash += password.charAt(i);
            }
        }
        return hash;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
