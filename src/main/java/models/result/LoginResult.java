package models.result;
/**
 * Contains all the necessary fields for a login response. All fields are required.
 */
public class LoginResult {
    public LoginResult(String username) {
        this.username = username;
    }

    String username;

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

}
