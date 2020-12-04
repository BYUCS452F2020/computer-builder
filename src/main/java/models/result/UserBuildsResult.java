package models.result;

import models.Build;
import java.util.List;

/**
 * Contains all the necessary fields for a login response. All fields are required.
 */
//TODO Create Build Model

public class UserBuildsResult extends Result{

	private String username;
	private List<Build> buildList;

	public UserBuildsResult(boolean success, String username, List<Build> buildList) {
		super(success);

        this.username = username;
        this.buildList = buildList;
    }

	public UserBuildsResult(boolean success, String message) {
		super(success, message);
	}


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public List<Build> getBuildList()
	{
		return buildList;
	}

	public void setBuildList(List<Build> buildList)
	{
		this.buildList = buildList;
	}

	@Override
	public String toString()
	{
		return "UserBuildsResult{" +
				"username='" + username + '\'' +
				", buildList=" + buildList +
				", success=" + success +
				", message='" + message + '\'' +
				'}';
	}
}
