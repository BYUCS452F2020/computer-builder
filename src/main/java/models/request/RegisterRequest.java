package models.request;

import com.google.gson.annotations.SerializedName;

/**
 * Contains all the fields necessary for a successful user registration. All variables are required.
 */
public class RegisterRequest {
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;
    @SerializedName("email")
    public String email;
    @SerializedName("firstName")
    public String firstName;
    @SerializedName("lastName")
    public String lastName;

    public RegisterRequest(){};

    /*public RegisterRequest(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }*/



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

}
