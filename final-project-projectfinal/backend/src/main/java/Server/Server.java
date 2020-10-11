package Server;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import dto.*;
import org.bson.Document;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.print.Doc;
import javax.print.attribute.standard.DocumentName;
import dao.*;

import org.json.*;
import dto.*;


public class Server {
  public static void main(String[] args) {

    // open connection
    MongoClient mongoClient = new MongoClient("localhost", 27017);

    MongoDatabase db = mongoClient.getDatabase("MessageDB");

    MongoCollection<Document> userCollection = db.getCollection("UserAccounts");

    // initialize Gson
    Gson gson = new Gson();
    port(4000);
    webSocket("/ws", WebSocketHandler.class); // open socket and leave it open

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
              .append("password", userDto.password);
      userCollection.insertOne(newUser);

      SuccessfulAuthDto responseDto =
              new SuccessfulAuthDto(true);
      return gson.toJson(responseDto);
    });

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


      SuccessfulAuthDto responseDto = new SuccessfulAuthDto(true);
      return gson.toJson(responseDto);
    });
    
    post("/api/sendmessage", (req, res) -> {
        String bodyString = req.body();
        MessageDao messageDao = MessageDao.getInstance();
        messageDao.addMessage(bodyString);

        ResponseDto responseDto = new ResponseDto(null, true);
        return gson.toJson(responseDto);
      });

      get("/api/fetchmessages", (req,res) -> {
        MessageDao messageDao = MessageDao.getInstance();
        List<MessageDto> chat = messageDao.getMessages();

        return gson.toJson(chat);
      });

      post("/api/updateLikes", (req,res) -> {
        String bodyString = req.body();
        MessageDao messageDao = MessageDao.getInstance();
        messageDao.updateLikes(bodyString);



        ResponseDto responseDto = new ResponseDto(null, true);
        return gson.toJson(responseDto);
      });
  }
}