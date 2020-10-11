package Server;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import dao.MessageDao;
import dto.MessageDto;
import dto.MessageListDto;
import org.bson.Document;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

@WebSocket
public class WebSocketHandler {
  // Store sessions if you want to, for example, broadcast a message to all users
  static Map<Session, Session> sessionMap = new ConcurrentHashMap<>();


  public static void broadcast(MessageDto message){
    sessionMap.keySet().forEach(session -> {
      // loop over sessions
      Gson gson = new Gson();
      try{

        session.getRemote().sendString(gson.toJson(message)); // send same message to all
      } catch (Exception e){
        e.printStackTrace();
      }
    });
  }

  @OnWebSocketConnect
  public void connected(Session session) throws IOException {
    System.out.println("A client has connected");
    sessionMap.put(session, session);
  }

  @OnWebSocketClose
  public void closed(Session session, int statusCode, String reason) {
    System.out.println("A client has disconnected");
    sessionMap.remove(session);
  }

  @OnWebSocketMessage
  public void message(Session session, String message) throws IOException {
    Gson gson = new Gson();
    MessageDto messageDto = gson.fromJson(message, MessageDto.class);
    System.out.println("Got: " + gson.toJson(messageDto));
    broadcast(messageDto);
  }
}