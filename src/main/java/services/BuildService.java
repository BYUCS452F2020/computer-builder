package services;

import dao.BuildDAO;
import dao.DataAccessException;
import dao.Database;
import models.Build;
import models.request.SaveBuildRequest;
import models.request.UserBuildsRequest;
import models.result.SaveBuildResult;
import models.result.UserBuildsResult;

import java.util.List;

public class BuildService
{
	public UserBuildsResult getUserBuilds(UserBuildsRequest userBuildsRequest) throws DataAccessException
	{
		Database database = new Database();
		UserBuildsResult userBuildsResult;

		try
		{
			String username = userBuildsRequest.getUsername();

			BuildDAO buildDAO = new BuildDAO(database.openConnection());
			List<Build> buildList = buildDAO.queryUserBuilds(username);

			userBuildsResult = new UserBuildsResult(true, username, buildList);
			database.closeConnection(true);
		}
		catch (DataAccessException e)
		{
			database.closeConnection(false);
			userBuildsResult = new UserBuildsResult(false, e.getMessage());
		}

		return userBuildsResult;
	}

	public SaveBuildResult insertNewBuild(SaveBuildRequest sbReq) throws DataAccessException
	{
		Database database = new Database();
		SaveBuildResult saveBuildResult;

		try
		{
			String username = sbReq.getUsername();
			String hashinput = sbReq.getUsername() + sbReq.getCpu() + sbReq.getMotherboard() + sbReq.getGpu() +
					sbReq.getRam() + sbReq.getStorage() + sbReq.getPsu() + sbReq.getCooler() + sbReq.getPc_case();
			int hash = 1;
			for(int i = 0; i < hashinput.length(); i++) {
				if((i % 2) == 0) {
					hash += (i % hashinput.charAt(i));
				}
				else if((i % 5) == 0) {
					hash *= hashinput.charAt(i);
				}
				else {
					hash += hashinput.charAt(i);
				}
			}

			System.out.println(sbReq.getUsername() + Integer.toString(hash));
			Build buildToAdd = new Build(sbReq.getUsername()  + Integer.toString(hash), sbReq.getUsername(),
					sbReq.getBuild_name(), sbReq.getMotherboard(), sbReq.getCpu(), sbReq.getCooler(), sbReq.getRam(),
					sbReq.getStorage(), sbReq.getGpu(), sbReq.getPsu(), sbReq.getPc_case());

			BuildDAO buildDAO = new BuildDAO(database.openConnection());
			buildDAO.insert(buildToAdd);

			saveBuildResult = new SaveBuildResult(true, username);
			database.closeConnection(true);
		}
		catch (DataAccessException e)
		{
			database.closeConnection(false);
			saveBuildResult = new SaveBuildResult(false, e.getMessage());
		}

		return saveBuildResult;
	}
}
