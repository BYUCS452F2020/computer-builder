package models.request;

import com.google.gson.annotations.SerializedName;

/**
 * Contains all the fields necessary for a successful user login. All variables are required.
 */
public class UserBuildsRequest {
    public UserBuildsRequest(String username) {
        this.username = username;

    }

    @SerializedName("username")
    String username;

    public String getusername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

