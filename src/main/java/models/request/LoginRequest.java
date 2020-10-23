package models.request;

import com.google.gson.annotations.SerializedName;

/**
 * Contains all the fields necessary for a successful user login. All variables are required.
 */
public class LoginRequest {
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @SerializedName("userName")
    String username;
    @SerializedName("password")
    String password;

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
}

