package models.result;

/**
 * The outcome from accessing a database
 */

public class Result {
    public final boolean success;
    public final String message;

    public Result(boolean success) {
        this(success, null);
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
