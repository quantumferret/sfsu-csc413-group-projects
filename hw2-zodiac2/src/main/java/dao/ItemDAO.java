package dao;

import com.google.gson.Gson;
import dto.Dto;
import dto.ItemDto;
import java.util.*;
import java.util.stream.Collectors;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class ItemDAO {
    //Singleton w/ Lazy Loading
    private static ItemDAO instance;
    private static MongoDatabase db = MongoConnection.mongoClient.getDatabase("ZodiacHomework2");
    private static MongoCollection<Document> itemCollection = db.getCollection("Items");
    private static Gson gson = new Gson();


    public static ItemDAO getInstance() {
        if (instance == null) {
            instance = new ItemDAO();
        }
        return instance;
    }

    //private constructor ensures people use getInstance()
    private ItemDAO() {
    }

    public void addItem(ItemDto newItem) {
        String itemJson = gson.toJson(newItem);
        Document newItemDocument = new Document("item", itemJson);
        itemCollection.insertOne(newItemDocument);
    }

    public List<Dto> listItems() {
        List<Dto> paymentList = itemCollection.find().into(new ArrayList<>())
                .stream()
                .map(document -> {
                    String fields = document.getString("item");
                    ItemDto item = gson.fromJson(fields, ItemDto.class);
                    item.setMachineCode(document.getObjectId("_id").toString());
                    return item;
                })
                .collect(Collectors.toList());
        return paymentList;
    }

    public Boolean itemExists(String itemCode) {
        Document doc = new Document();
        try {
            doc = itemCollection.
                    find(eq("_id", new ObjectId(itemCode))).first();
        } finally {
            return !doc.isEmpty();
        }
    }
}