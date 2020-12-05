package dao;

import com.mongodb.client.MongoDatabase;
import models.Build;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuildMDAOTest
{
	private List<Build> testBuilds;

	private Build testBuild;
	private Build testBuildTwo;
	private Build testBuildThree;
	private Build testBuildFour;
	private Build testBuildFive;


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

		testBuildFive = new Build("789", "peyton29", "MySecondPC",
				"Asus ROG Strix X399-E Gaming EATX sTR4 Motherboard",
				"AMD Threadripper 1950X 3.4 GHz 16-Core Processor",
				"Asus ROG RYUJIN 360 121.8 CFM Liquid CPU Cooler",
				"Crucial Ballistix Sport AT 64 GB (4 x 16 GB) DDR4-3000 CL17 Memory",
				"Samsung 970 EVO Plus 2 TB M.2-2280 NVME Solid State Drive",
				"MSI GeForce RTX 2080 SUPER 8 GB GAMING X TRIO Video Card",
				"Asus 850 W 80+ Platinum Certified Fully Modular ATX Power Supply",
				"Anidees AI CRYSTAL XL PRO ATX Full Tower Case");

		testBuilds = new ArrayList<Build>();
		testBuilds.add(testBuild);
		testBuilds.add(testBuildTwo);
		testBuilds.add(testBuildThree);
		testBuilds.add(testBuildFour);
		testBuilds.add(testBuildFive);

		/*try
		{
			BuildMDAO buildMDAOToTest = new BuildMDAO();
			buildMDAOToTest.deleteAllBuilds();				//
		}
		catch (DataAccessException e)
		{
			fail("DataAccessException was thrown: " + e.getMessage());
		}*/
	}

	@AfterEach
	void tearDown()
	{
	}



	@Test
	void insertBuildAndFindBuild()
	{
		BuildMDAO buildMDAOToTest = new BuildMDAO();

		try
		{
			Build foundBuild = buildMDAOToTest.findBuild("987");
			Assert.assertNull(foundBuild);

			buildMDAOToTest.insertBuild(testBuild);
			buildMDAOToTest.insertBuild(testBuildTwo);
			buildMDAOToTest.insertBuild(testBuildThree);
			buildMDAOToTest.insertBuild(testBuildFour);
			buildMDAOToTest.insertBuild(testBuildFive);

			Build foundBuild2 = buildMDAOToTest.findBuild("987");
			Assert.assertNotNull(foundBuild2);
			Assert.assertEquals(testBuild, foundBuild2);
		}
		catch (DataAccessException e)
		{
			fail("DataAccessException was thrown: " + e.getMessage());
		}
	}

	@Test
	void insertBuildsAndFindUserBuilds()
	{
		BuildMDAO buildMDAOToTest = new BuildMDAO();

		try
		{
			List<Build> foundBuilds = buildMDAOToTest.findUserBuilds("peyton29");
			Assert.assertEquals(0, foundBuilds.size());

			buildMDAOToTest.insertBuilds(testBuilds);

			List<Build> foundBuilds2 = buildMDAOToTest.findUserBuilds("peyton29");
			Assert.assertEquals(2, foundBuilds2.size());
			Assert.assertTrue(foundBuilds2.contains(testBuild));
			Assert.assertTrue(foundBuilds2.contains(testBuildFive));
		}
		catch (DataAccessException e)
		{
			fail("DataAccessException was thrown: " + e.getMessage());
		}
	}

	@Test
	void deleteOneBuild()
	{
		BuildMDAO buildMDAOToTest = new BuildMDAO();

		try
		{
			Build foundBuild = buildMDAOToTest.findBuild("987");
			Assert.assertNull(foundBuild);

			buildMDAOToTest.insertBuild(testBuild);
			buildMDAOToTest.insertBuild(testBuildTwo);
			buildMDAOToTest.insertBuild(testBuildThree);
			buildMDAOToTest.insertBuild(testBuildFour);
			buildMDAOToTest.insertBuild(testBuildFive);

			Build foundBuild2 = buildMDAOToTest.findBuild("987");
			Assert.assertNotNull(foundBuild2);
			Assert.assertEquals(testBuild, foundBuild2);

			buildMDAOToTest.deleteBuild("987");

			Build foundBuild3 = buildMDAOToTest.findBuild("987");
			Assert.assertNull(foundBuild3);
		}
		catch (DataAccessException e)
		{
			fail("DataAccessException was thrown: " + e.getMessage());
		}
	}

	@Test
	void deleteAllBuilds()
	{
		BuildMDAO buildMDAOToTest = new BuildMDAO();

		try
		{
			List<Build> foundBuilds = buildMDAOToTest.findUserBuilds("peyton29");
			Assert.assertEquals(0, foundBuilds.size());

			buildMDAOToTest.insertBuilds(testBuilds);

			List<Build> foundBuilds2 = buildMDAOToTest.findUserBuilds("peyton29");
			Assert.assertEquals(2, foundBuilds2.size());
			Assert.assertTrue(foundBuilds2.contains(testBuild));
			Assert.assertTrue(foundBuilds2.contains(testBuildFive));

			buildMDAOToTest.deleteAllBuilds();

			List<Build> foundBuilds3 = buildMDAOToTest.findUserBuilds("peyton29");
			Assert.assertEquals(0, foundBuilds3.size());
		}
		catch (DataAccessException e)
		{
			fail("DataAccessException was thrown: " + e.getMessage());
		}
	}
}