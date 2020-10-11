package dao;

import com.mongodb.MongoClient;

public class MongoConnection {
    public static MongoClient mongoClient = new MongoClient("localhost", 27017);

}
