package dao;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Arrays.asList;

public class MongoDBDatabase {

    public static MongoDatabase getDB() {
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
        MongoDatabase db;
        try (MongoClient mongoClient = MongoClients.create()) {

            db = mongoClient.getDatabase("computer_builder");

            //Example of how to get a collection
            //MongoCollection<Document> gradesCollection = db.getCollection("users");

            //Example of inserting into a collection
            /*Random rand = new Random();
            Document student = new Document("_id", new ObjectId()); //Give entry unique ID
            student.append("student_id", 10000d) //Give entry an attribute
                    .append("class_id", 1d)
                    .append("scores", asList(new Document("type", "exam").append("score", rand.nextDouble() * 100), //Give entry a list of attributes
                            new Document("type", "quiz").append("score", rand.nextDouble() * 100),
                            new Document("type", "homework").append("score", rand.nextDouble() * 100),
                            new Document("type", "homework").append("score", rand.nextDouble() * 100)));

            gradesCollection.insertOne(student);*/
        }
        return db;
    }
}
