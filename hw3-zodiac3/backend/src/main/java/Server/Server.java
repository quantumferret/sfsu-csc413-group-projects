package Server;

import static spark.Spark.*;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import dto.*;
import javax.print.Doc;
import org.json.*;


public class Server {
    public static void main(String[] args){

        // open connection
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        // get ref to database
        MongoDatabase db = mongoClient.getDatabase("Homework3Store");
        // get ref to collection
        MongoCollection<Document> userCollection = db.getCollection("UserAccounts");
        MongoCollection<Document> itemCollection = db.getCollection("Items");
        MongoCollection<Document> transactionCollection = db.getCollection("Transactions");


        // initialize Gson
        Gson gson = new Gson();

        port(4000);
        // 2 way communication
        webSocket("/ws", WebSocketHandler.class); // open socket and leave it open

        post("/api/login", (req, res) -> {
            String bodyString = req.body();
            UserDto userDto = gson.fromJson(bodyString, UserDto.class);
            List<Document> potentialUser = userCollection.find(new Document("username", userDto.username))
                    .into(new ArrayList<>());

            if (potentialUser.size() != 1) {
                FailedAuthDto responseDto =
                        new FailedAuthDto(false, "User not found");
                return gson.toJson(responseDto);
            }
            Document userDocument = potentialUser.get(0); // should be one

            if (!userDocument.getString("password").equals(userDto.password)) {
                FailedAuthDto responseDto =
                        new FailedAuthDto(false, "Password is incorrect");
                return gson.toJson(responseDto);
            }

            Boolean adminStatus = userDocument.getBoolean("adminStatus");

            SuccessfulAuthDto responseDto = new SuccessfulAuthDto(true, adminStatus);
            return gson.toJson(responseDto);
        });

        post("/api/signup", (req, res) -> {
            String bodyString = req.body();
            UserDto userDto = gson.fromJson(bodyString, UserDto.class);

            List<Document> potentialUser = userCollection.find(new Document("username", userDto.username))
                    .into(new ArrayList<>());
            if (!potentialUser.isEmpty()) {
                FailedAuthDto responseDto =
                        new FailedAuthDto(false, "User already exists");
                return gson.toJson(responseDto);
            }

            Document newUser = new Document()
                    .append("username", userDto.username)
                    .append("password", userDto.password)
                    .append("adminStatus", userDto.adminStatus);
            userCollection.insertOne(newUser);

            SuccessfulAuthDto responseDto =
                    new SuccessfulAuthDto(true, userDto.adminStatus);
            return gson.toJson(responseDto);
        });

        post("/api/store", (req, res) -> {
            String bodyString = req.body();
            return gson.toJson("Store Placeholder");
                });

        post("/api/storemanagement", (req, res) -> {
            String bodyString = req.body();
            return gson.toJson("Store Management Placeholder");
        });

        post("/api/addtransaction", (req,res) -> {
            String bodyString = req.body();
            System.out.println(bodyString);
            TransactionDto transactionDto = gson.fromJson(bodyString, TransactionDto.class);
            Document newTransaction = new Document()
                    .append("user", transactionDto.user)
                    .append("item", transactionDto.item);
            transactionCollection.insertOne(newTransaction);

            StoreResponseDto authResponseDto = new StoreResponseDto(true,null);
            return gson.toJson(authResponseDto);
        });

        get("/api/listtransactions", (req,res) -> {
            TransactionListDto transactionList = new TransactionListDto();
            FindIterable<Document> cursor = transactionCollection.find();

            for(Document document : cursor) {
                if (document.containsKey("user") && document.containsKey("item")) {
                    TransactionDto transaction = new TransactionDto(document.getString("user"), document.getString("item"));
                    transactionList.transactionList.add(transaction);

                }
            }
            return gson.toJson(transactionList);
        }, gson::toJson);

        post("/api/additem", (req,res) -> {
            String bodyString = req.body();
            JSONObject jsonObj;
            jsonObj = new JSONObject(bodyString);
            ItemDto itemDto = new ItemDto(jsonObj.get("item").toString(), jsonObj.get("price").toString());
            Document newItem = new Document()
                    .append("name", itemDto.name)
                    .append("price", itemDto.price);
            itemCollection.insertOne(newItem);

            StoreResponseDto authResponseDto = new StoreResponseDto(true,null);
            return gson.toJson(authResponseDto);
        });

        post("/api/deleteitem", (req, res) -> {
            String bodyString = req.body();
            JSONObject jsonObj;
            jsonObj = new JSONObject(bodyString);
            ItemDto itemDto = new ItemDto(jsonObj.get("item").toString(), jsonObj.get("price").toString());
            Document deleteItem = new Document()
                    .append("name", itemDto.name)
                    .append("price", itemDto.price);
            itemCollection.deleteOne(deleteItem);
            StoreResponseDto authResponseDto = new StoreResponseDto(true,null);
            return gson.toJson(authResponseDto);
        });


        get("/api/listitems", (req, res) -> {
            // System.out.println("lis items req = " + req.body());
            ListItemDto listItemDto = new ListItemDto();
            FindIterable<Document> cursor = itemCollection.find();
            for (Document doc : cursor) {
                if(doc.containsKey("name") && doc.containsKey("price")) {
                    ItemDto newItem = new ItemDto(doc.getString("name"), doc.getDouble("price").toString());
                    listItemDto.AddItemDto(newItem);
                }
            }

            return gson.toJson(listItemDto);
        }, gson::toJson);

    }
}
