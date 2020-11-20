package models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;
import java.util.Objects;

public class User {

    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("username")
    private String username;
    private int password;
    @SerializedName("email")
    private String email;
    //private Map<String, Build> builds;

    public User(String username, String firstName, String lastName,
                String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(String username, String firstName, String lastName,
                int password, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    private int hashPassword(String password) {
        int hash = 1;
        for(int i = 0; i < password.length(); i++) {
            if((i % 2) == 0) {
                hash += (i % password.charAt(i));
            }
            else if((i % 5) == 0) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, email);
    }
}

