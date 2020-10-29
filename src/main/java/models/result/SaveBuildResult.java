package models.result;

/**
 * Contains all the necessary fields for a registration response. All fields are required.
 */

public class SaveBuildResult {

    public SaveBuildResult(boolean success, String userName) {
        this.success = success;
        this.username = userName;
    }

    String username;
    boolean success;

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
