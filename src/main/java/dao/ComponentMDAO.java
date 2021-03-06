package dao;

import com.google.gson.Gson;
import com.mongodb.MongoClientException;
import com.mongodb.client.*;

import static com.mongodb.client.model.Filters.*;

import models.Component;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ComponentMDAO {

    public void insert(Component component) throws DataAccessException {

        MongoDatabase database;

        try (MongoClient mongo = MongoClients.create()) {

            database = mongo.getDatabase("computer_builder");
            MongoCollection<Document> componentCollection = database.getCollection("components");

            componentCollection.insertOne(createComponentDocument(component));
        } catch (MongoClientException e) {
            e.printStackTrace();
            throw new DataAccessException("Error inserting one component");
        }

    }

    public void insertMany(List<Component> components) throws DataAccessException {
        List<Document> documentList = new ArrayList<>();

        MongoDatabase database;

        try (MongoClient mongoClient = MongoClients.create()){

            database = mongoClient.getDatabase("computer_builder");
            MongoCollection<Document> componentCollection = database.getCollection("components");

            for (Component curr : components) {
                documentList.add(createComponentDocument(curr));
            }

            componentCollection.insertMany(documentList);
        } catch (MongoClientException e) {
            e.printStackTrace();
            throw new DataAccessException("Error inserting many components");
        }
    }

    public List<Component> findMany(String componentType, double maxPrice, int performanceRating,
                                    String cpuFamily, int maxTDP) throws DataAccessException {
        MongoDatabase database;
        try (MongoClient mongoClient = MongoClients.create()) {
            database = mongoClient.getDatabase("computer_builder");
            MongoCollection<Document> componentCollection = database.getCollection("components");
            Document queryDocument = new Document("componentType", componentType);
            if(performanceRating != 0) {
                queryDocument.append("performanceRating", new Document().append("$gte", performanceRating));
            }
            if(maxPrice != 0) {
                queryDocument.append("price", new Document().append("$lte", maxPrice));
            }
            if(cpuFamily != null) {
                queryDocument.append("cpuFamily", cpuFamily);
            }
            if(componentType.equals("Power-Supply")) {
                if(maxTDP == 0) {
                    queryDocument.append("tdp", new Document().append("$lte", 100000));
                }
                else {
                    queryDocument.append("tdp", new Document().append("$lte", maxTDP));
                }
            }

            FindIterable<Document> componentDocList = componentCollection.find(queryDocument);
            List<Component> componentList = new ArrayList<>();

            Gson gson = new Gson();

            for(Document document : componentDocList) {
                componentList.add(gson.fromJson(document.toJson(), Component.class));
            }

            return componentList;
        } catch (MongoClientException e) {
            throw new DataAccessException("Error finding many components");
        }
    }

    public Component findOne(String componentId) throws DataAccessException {

        MongoDatabase database;
        try (MongoClient mongoClient = MongoClients.create()) {
            database = mongoClient.getDatabase("computer_builder");
            MongoCollection<Document> componentCollection = database.getCollection("components");

            Document docComponent = componentCollection.find(eq("componentID", componentId)).first();

            Gson gson = new Gson();

            return gson.fromJson(docComponent.toJson(), Component.class);
        } catch (MongoClientException e) {
            throw new DataAccessException("Error finding one component");
        }
    }

    public void deleteAll() throws DataAccessException {
        MongoDatabase database;
        try (MongoClient mongoClient = MongoClients.create()) {
            database = mongoClient.getDatabase("computer_builder");
            MongoCollection<Document> componentCollection = database.getCollection("components");

            Document deleteDocument = new Document();
            componentCollection.deleteMany(deleteDocument);

        } catch (MongoClientException e) {
            e.printStackTrace();
            throw new DataAccessException("Error removing all components");
        }
    }

    private Document createComponentDocument(Component component) {

        return new Document("_id", component.getComponentID())
                .append("componentID", component.getComponentID())
                .append("componentName", component.getComponentName())
                .append("componentType", component.getComponentType())
                .append("manufacturer", component.getManufacturer())
                .append("performanceRating", component.getPerformanceRating())
                .append("price", component.getPrice())
                .append("cpuFamily", component.getCpuFamily())
                .append("tdp", component.getTpd())
                .append("imageURL", component.getImageURL());
    }

}
