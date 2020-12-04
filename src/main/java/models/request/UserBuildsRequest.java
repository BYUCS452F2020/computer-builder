package models.request;

import com.google.gson.annotations.SerializedName;

/**
 * Contains all the fields necessary for a successful user login. All variables are required.
 */
public class UserBuildsRequest {

    @SerializedName("username")
    private String username;

    public UserBuildsRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

