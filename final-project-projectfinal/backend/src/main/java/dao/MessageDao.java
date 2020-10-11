package dao;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import dto.MessageDto;
import dto.MessageListDto;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

public class MessageDao {
    private static MessageDao instance;
    private static Gson gson = new Gson();
    private static MongoDatabase db = MongoConnection.mongoClient.getDatabase("MessageDB");
    MongoCollection<Document> messageCollection = db.getCollection("Messages");

    public static MessageDao getInstance() {
        if (instance == null) {
            instance = new MessageDao();
        }

        return instance;
    }

    private MessageDao() {}

    public void addMessage(String bodyString) {
        MessageDto messageDto = gson.fromJson(bodyString, MessageDto.class);

        Document newMessage = new Document()
                .append("username", messageDto.username)
                .append("message", messageDto.message)
                .append("date", messageDto.date)
                .append("likes", messageDto.likes);

        messageCollection.insertOne(newMessage);
    }

    public List<MessageDto> getMessages() {
        MessageListDto messageListDto = new MessageListDto();

        //Iterates through all documents in the message collection
        MongoCursor<Document> cursor = messageCollection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document nextDocument = cursor.next();
                String message = nextDocument.getString("message");
                String date = nextDocument.getString("date");
                String username = nextDocument.getString("username");
                int likes = nextDocument.getInteger("likes");
                MessageDto messageDto = new MessageDto(message, date, username, likes);

                messageListDto.chat.add(messageDto);


            }
        } finally {
            cursor.close();
        }
        return messageListDto.chat;
    }

    public void updateLikes(String bodyString) {
       // System.out.println(bodyString);
        MessageDto messageDto = gson.fromJson(bodyString, MessageDto.class);
        Document newMessage = new Document()
                .append("username", messageDto.username)
                .append("message", messageDto.message)
                .append("date", messageDto.date)
                .append("likes", messageDto.likes);

         Bson messageFilter = Filters.eq("message", messageDto.message);
         Bson userFilter = Filters.eq("username", messageDto.username);
         Bson dateFilter = Filters.eq("date", messageDto.date);
         Bson bsonFilter = Filters.and(userFilter, messageFilter, dateFilter);

        System.out.println(newMessage);

        messageCollection.updateOne(bsonFilter, new Document("$set", new Document("likes", messageDto.likes+1)));
        System.out.println("Likes updated");
    }
}
