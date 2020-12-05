package dao;

import com.google.gson.Gson;
import com.mongodb.MongoClientException;
import com.mongodb.client.*;
import models.Build;
import models.Component;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class BuildMDAO
{

	public void insertBuild(Build build) throws DataAccessException
	{
		MongoDatabase database;

		try (MongoClient mongo = MongoClients.create())
		{
			database = mongo.getDatabase("computer_builder");
			MongoCollection<Document> buildCollection = database.getCollection("builds");

			buildCollection.insertOne(createBuildDocument(build));
		}
		catch (MongoClientException e)
		{
			e.printStackTrace();
			throw new DataAccessException("Error inserting one build");
		}

	}

	public void insertBuilds(List<Build> builds) throws DataAccessException
	{
		List<Document> documentList = new ArrayList<>();

		MongoDatabase database;

		try (MongoClient mongoClient = MongoClients.create())
		{
			database = mongoClient.getDatabase("computer_builder");
			MongoCollection<Document> buildCollection = database.getCollection("builds");

			for (Build curr : builds)
			{
				documentList.add(createBuildDocument(curr));
			}

			buildCollection.insertMany(documentList);
		}
		catch (MongoClientException e)
		{
			e.printStackTrace();
			throw new DataAccessException("Error inserting many builds");
		}
	}

	public Build findBuild(String buildId) throws DataAccessException
	{
		MongoDatabase database;
		try (MongoClient mongoClient = MongoClients.create())
		{
			database = mongoClient.getDatabase("computer_builder");
			MongoCollection<Document> buildCollection = database.getCollection("builds");

			Document docBuild = buildCollection.find(eq("buildId", buildId)).first();

			if (docBuild == null)
			{
				return null;
			}

			Gson gson = new Gson();

			return gson.fromJson(docBuild.toJson(), Build.class);
		}
		catch (MongoClientException e)
		{
			throw new DataAccessException("Error finding one build");
		}
	}

	public List<Build> findUserBuilds(String userId) throws DataAccessException
	{
		MongoDatabase database;
		try (MongoClient mongoClient = MongoClients.create())
		{
			database = mongoClient.getDatabase("computer_builder");
			MongoCollection<Document> buildCollection = database.getCollection("builds");

//			Document queryDocument = new Document("userId", userId);
			Bson filter = eq("userId", userId);

			FindIterable<Document> buildDocList = buildCollection.find(filter);
			List<Build> buildList = new ArrayList<>();

			Gson gson = new Gson();

			for (Document document : buildDocList)
			{
				buildList.add(gson.fromJson(document.toJson(), Build.class));
			}

			return buildList;
		}
		catch (MongoClientException e)
		{
			throw new DataAccessException("Error finding user builds");
		}
	}

	public void deleteBuild(String buildId) throws DataAccessException
	{
		MongoDatabase database;
		try (MongoClient mongoClient = MongoClients.create())
		{
			database = mongoClient.getDatabase("computer_builder");
			MongoCollection<Document> buildCollection = database.getCollection("builds");

			// This should theoretically do the same thing as the deleteOne call below
//			Build deleteBuild = findOne(buildId);
//			Document deleteDocument = createBuildDocument(deleteBuild);
//			buildCollection.deleteOne(deleteDocument);

			Bson filter = eq("buildId", buildId);
			buildCollection.deleteOne(filter);
		}
		catch (MongoClientException e)
		{
			e.printStackTrace();
			throw new DataAccessException("Error deleting one build");
		}
	}

	public void deleteAllBuilds() throws DataAccessException
	{
		MongoDatabase database;
		try (MongoClient mongoClient = MongoClients.create())
		{
			database = mongoClient.getDatabase("computer_builder");
			MongoCollection<Document> componentCollection = database.getCollection("builds");

			Document deleteDocument = new Document();
			componentCollection.deleteMany(deleteDocument);
		}
		catch (MongoClientException e)
		{
			e.printStackTrace();
			throw new DataAccessException("Error deleting all builds");
		}
	}

	private Document createBuildDocument(Build build)
	{
		return new Document("_id", build.getBuildId())
				.append("buildId", build.getBuildId())
				.append("userId", build.getUserId())
				.append("buildName", build.getBuildName())
				.append("motherboard", build.getMotherboard())
				.append("processor", build.getProcessor())
				.append("cpuCooler", build.getCpuCooler())
				.append("memory", build.getMemory())
				.append("storage", build.getStorage())
				.append("graphicsCard", build.getGraphicsCard())
				.append("powerSupply", build.getPowerSupply())
				.append("pcCase", build.getPcCase());
	}
}
