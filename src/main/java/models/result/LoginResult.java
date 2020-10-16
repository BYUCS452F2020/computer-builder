package models.result;
/**
 * Contains all the necessary fields for a login response. All fields are required.
 */
public class LoginResult {
    public LoginResult(String userName) {
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
