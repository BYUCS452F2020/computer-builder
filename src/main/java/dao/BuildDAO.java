package dao;

import models.Build;
import models.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuildDAO
{
	private final Connection connection;

	public BuildDAO(Connection connection)
	{
		this.connection = connection;
	}


	public void insert(Build build) throws DataAccessException
	{
		System.out.println("inserting into builds");
		String sql = "INSERT INTO Builds (build_id, user_id, build_name, " +
				"motherboard, processor, cpu_cooler, memory, storage, graphics_card, power_supply, pc_case) " +
				"VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql))
		{
			stmt.setString(1, build.getBuildId());
			stmt.setString(2, build.getUserId());
			stmt.setString(3, build.getBuildName());
			stmt.setString(4, build.getMotherboard());
			stmt.setString(5, build.getProcessor());
			stmt.setString(6, build.getCpuCooler());
			stmt.setString(7, build.getMemory());
			stmt.setString(8, build.getStorage());
			stmt.setString(9, build.getGraphicsCard());
			stmt.setString(10, build.getPowerSupply());
			stmt.setString(11, build.getPcCase());

			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new DataAccessException("Error encountered while inserting into the Builds table in database");
		}
	}

	public List<Build> queryUserBuilds(String userId) throws DataAccessException
	{
		List<Build> builds = new ArrayList<Build>();

		ResultSet rs = null;
		String sql = "SELECT * FROM Builds WHERE user_id = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql))
		{
			stmt.setString(1, userId);
			rs = stmt.executeQuery();

			while (rs.next())
			{
				Build newBuild = new Build(rs.getString("build_id"),
						rs.getString("user_id"),
						rs.getString("build_name"),
						rs.getString("motherboard"),
						rs.getString("processor"),
						rs.getString("cpu_cooler"),
						rs.getString("memory"),
						rs.getString("storage"),
						rs.getString("graphics_card"),
						rs.getString("power_supply"),
						rs.getString("pc_case")
						);

				builds.add(newBuild);
			}
			return builds;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new DataAccessException("Error querying a user's builds: " + e.getMessage());
		}
		finally
		{
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public int deleteAll() throws DataAccessException
	{
		String sql = "DELETE FROM Builds";

		try (PreparedStatement stmt = connection.prepareStatement(sql))
		{
			int count = stmt.executeUpdate();
			return count;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new DataAccessException("Error encountered while deleting all builds");
		}
	}

	public int deleteOne(String buildId) throws DataAccessException
	{
		String sql = "DELETE FROM Builds WHERE build_id = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql))
		{
			stmt.setString(1, buildId);
			int count = stmt.executeUpdate();
			return count;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new DataAccessException("Error encountered while deleting one build");
		}
	}
}
