package models.result;

/**
 * Contains all the necessary fields for a registration response. All fields are required.
 */

public class RegisterResult {

    public RegisterResult(String userName) {
        this.userName = userName;
    }

    String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
