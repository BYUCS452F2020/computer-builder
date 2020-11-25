package dao;

import com.mongodb.MongoCredential;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class ComponentMDAO {
    /*
        - Need to establish the correct connection to the mongo database
        - Have insert method that is only used for data admin
        - Have find method for singular component and one for the conditional finds
        - Maybe include an update function
        - Query document will consist of at least type and then performance, price, and tdp
        - Structure of the collection will be all components in one collection identified by type.
     */

    MongoClient mongo;
    MongoDatabase database;

    public ComponentMDAO() {
        mongo = new MongoClient("localhost", 27017);
        database = mongo.getDatabase("computerbuildDB");
    }

}
