package models.result;

/**
 * Contains all the necessary fields for a registration response. All fields are required.
 */

public class RegisterResult {

    public RegisterResult(String userName) {
        this.username = userName;
    }

    String username;

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }
}
