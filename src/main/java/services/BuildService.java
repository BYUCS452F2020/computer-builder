package services;

import dao.BuildDAO;
import dao.ComponentDAO;
import dao.DataAccessException;
import dao.Database;
import models.Build;
import models.request.UserBuildsRequest;
import models.result.GetComponentsResult;
import models.result.UserBuildsResult;

import java.util.List;

public class BuildService
{
	public UserBuildsResult getUserBuilds(UserBuildsRequest userBuildsRequest)
	{
		Database database = new Database();
		UserBuildsResult userBuildsResult;

		try
		{
			String username = userBuildsRequest.getUsername();

			BuildDAO buildDAO = new BuildDAO(database.openConnection());
			List<Build> buildList = buildDAO.queryUserBuilds(username);

			userBuildsResult = new UserBuildsResult(true, username, buildList);
		}
		catch (DataAccessException e)
		{
			userBuildsResult = new UserBuildsResult(false, e.getMessage());
		}

		return userBuildsResult;
	}
}
