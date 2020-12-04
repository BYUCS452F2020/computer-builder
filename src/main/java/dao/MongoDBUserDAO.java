package dao;

import com.mongodb.MongoClientException;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import models.User;
import models.request.SaveBuildRequest;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.sql.*;

public class MongoDBUserDAO {


    public void insert(User user) throws DataAccessException {
        System.out.println("inserting new user into mongodb");

        MongoDatabase db;
        try (MongoClient mongoClient = MongoClients.create()) {

            db = mongoClient.getDatabase("computer_builder");
            MongoCollection<Document> usersCollection = db.getCollection("users");
            Document newUser2 = new Document("_id", user.getUsername())
                    .append("username", user.getUsername())
                    .append("first_name", user.getFirstName())
                    .append("last_name", user.getLastName())
                    .append("password", user.getPassword())
                    .append("email", user.getEmail());
            usersCollection.insertOne(newUser2);
        } catch(MongoClientException e) {
            System.out.println("user already exists!");
        }
        //userCollection.insertOne(newUser);
        System.out.println("inserted");
    }

    public User find(String username, String password) throws DataAccessException {

        MongoDatabase db;
        try (MongoClient mongoClient = MongoClients.create()) {
            db = mongoClient.getDatabase("computer_builder");
            MongoCollection<Document> usersCollection = db.getCollection("users");
            Document docUsername = new Document("username", username);
            Document docUser = usersCollection.find(eq("username", username)).first();

            Gson gson = new Gson();

            User user = gson.fromJson(docUser.toJson(), User.class);
            user.setPassword(password);
            int matchingPassword = (Integer) docUser.get("password");

            if (matchingPassword == user.getPassword()) {
                System.out.println("Found user!");
                return user;
            } else {
                return null;
            }
        }


    }

    /*public int deleteAll() throws DataAccessException {
        int count;
        String sql = "DELETE FROM Users";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            count = stmt.executeUpdate();

            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while deleting all users");
        }
    }

    public int deleteOne(String userID) throws DataAccessException {
        int count;
        String sql = "DELETE FROM Users WHERE username = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userID);
            count = stmt.executeUpdate();

            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while deleting one user");
        }
    }*/
}
