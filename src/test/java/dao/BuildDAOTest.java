package dao;

import models.Build;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuildDAOTest
{
	private BuildDAO buildDAOToTest;
	private Database database;

	private Build testBuild;
	private Build testBuildTwo;
	private Build testBuildThree;
	private Build testBuildFour;


	@BeforeEach
	void setUp()
	{
		testBuild = new Build("987", "peyton29", "MyFirstPC",
				"Asus ROG Strix X399-E Gaming EATX sTR4 Motherboard",
				"AMD Threadripper 1950X 3.4 GHz 16-Core Processor",
				"Asus ROG RYUJIN 360 121.8 CFM Liquid CPU Cooler",
				"Crucial Ballistix Sport AT 64 GB (4 x 16 GB) DDR4-3000 CL17 Memory",
				"Samsung 970 EVO Plus 2 TB M.2-2280 NVME Solid State Drive",
				"MSI GeForce RTX 2080 SUPER 8 GB GAMING X TRIO Video Card",
				"Asus 850 W 80+ Platinum Certified Fully Modular ATX Power Supply",
				"Anidees AI CRYSTAL XL PRO ATX Full Tower Case");

		testBuildTwo = new Build("654", "julieeh2", "FireStorm", "Mother",
				"Persephone", "Ice", "Brain", "Closet",
				"Video", "Lightning", "Lamp");

		testBuildThree = new Build("321", "forrest2", "Toyota Supra", "Tony",
				"Jim", "Helga", "Myrtle", "Bullwinkle",
				"Rocky", "Borus", "Natasha");

		testBuildFour = new Build("000", "westenm", "Gale", "Arizona",
				"Alabama", "Austin", "Jane", "Elizabeth",
				"Lydia", "Charlotte", "Darcy");

		database = new Database();

		try
		{
			buildDAOToTest = new BuildDAO(database.openConnection());
//			buildDAOToTest.deleteAll();
		}
		catch (DataAccessException e)
		{
			e.printStackTrace();
			System.out.println("Error accessing database: " + e.getMessage());
		}
	}

	@AfterEach
	void tearDown()
	{
		if (database.isOpenConnection())
		{
			try
			{
				database.closeConnection(false);
			}
			catch (DataAccessException e)
			{
				e.printStackTrace();
				System.out.println("Failed to close connection in tear down: " + e.getMessage());
			}
		}

		database = null;
		buildDAOToTest = null;
		testBuild = null;
		testBuildTwo = null;
		testBuildFour = null;
		testBuildThree = null;
	}

	@Test
	@DisplayName("Should insert given build into the database")
	void insert()
	{
		try
		{
			buildDAOToTest.insert(testBuild);
			database.closeConnection(true);
		}
		catch (DataAccessException e)
		{
			fail("DataAccessException was thrown: " + e.getMessage());
		}
	}

	@Test
	@DisplayName("Should query all builds for a given user in the database")
	void queryUserBuilds()
	{
		try
		{
			buildDAOToTest.insert(testBuild);
			buildDAOToTest.insert(testBuildTwo);
			buildDAOToTest.insert(testBuildThree);
			buildDAOToTest.insert(testBuildFour);

			List<Build> userBuilds = buildDAOToTest.queryUserBuilds("peyton29");
			database.closeConnection(true);

			assertNotNull(userBuilds);
			assertEquals(1, userBuilds.size());

			System.out.println(userBuilds);
		}
		catch (DataAccessException e)
		{
			fail("DataAccessException was thrown: " + e.getMessage());
		}
	}

	@Test
	@DisplayName("Should delete all builds in the database")
	void deleteAll()
	{
		try
		{
			buildDAOToTest.insert(testBuild);
			buildDAOToTest.insert(testBuildTwo);
			buildDAOToTest.insert(testBuildThree);
			buildDAOToTest.insert(testBuildFour);

			int count = buildDAOToTest.deleteAll();
			database.closeConnection(true);

			assertEquals(4, count);
		}
		catch (DataAccessException e)
		{
			fail("DataAccessException was thrown: " + e.getMessage());
		}
	}

	@Test
	@DisplayName("Should delete a given build")
	void deleteOne()
	{
		try
		{
			buildDAOToTest.insert(testBuild);
			buildDAOToTest.insert(testBuildTwo);
			buildDAOToTest.insert(testBuildThree);
			buildDAOToTest.insert(testBuildFour);

			int count = buildDAOToTest.deleteOne(testBuild.getBuildId());
			database.closeConnection(true);

			assertEquals(1, count);
		}
		catch (DataAccessException e)
		{
			fail("DataAccessException thrown: " + e.getMessage());
		}
	}
}