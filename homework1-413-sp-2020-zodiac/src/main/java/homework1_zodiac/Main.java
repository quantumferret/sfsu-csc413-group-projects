package homework1_zodiac;


import homework1_zodiac.response.*;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

  public static void main(String[] args) throws IOException {
    ServerSocket ding;
    Socket dong = null;
    try {
      ding = new ServerSocket(1299);
      System.out.println("Opened socket " + 1299);
      while (true) {
        // keeps listening for new clients, one at a time
        try {
          dong = ding.accept(); // waits for client here
        } catch (IOException e) {
          System.out.println("Error opening socket");
          System.exit(1);
        }

        InputStream stream = dong.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));

        String jsonResponse = "";
        String firstline = "";

        try {
          // read the first line to get the request method, URI and HTTP version
          String line = in.readLine();
          firstline = line;
          System.out.println("----------REQUEST START---------");
          System.out.println(line);

          // read only headers
          line = in.readLine();
          System.out.println(line);
          while (line != null && line.trim().length() > 0) {
            int index = line.indexOf(": ");
            if (index > 0) {
              System.out.println(line);
            } else {
              break;
            }
            line = in.readLine();
          }
          System.out.println("----------REQUEST END---------\n\n");
        } catch (IOException e) {
          System.out.println("Error reading");
          System.exit(1);
        }



        BufferedOutputStream out = new BufferedOutputStream(dong.getOutputStream());
        PrintWriter writer = new PrintWriter(out, true);  // char output to the client

        // every response will always have the status-line, date, and server name
        writer.println("HTTP/1.1 200 OK");
        writer.println("Server: TEST");
        writer.println("Connection: close");
        writer.println("Content-type: text/html");
        writer.println("");


          Processor p = ProcessorFactory.parseRoute(firstline);
          if (p != null) {
            Response response = p.process();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            jsonResponse = gson.toJson(response);
          }
        // Body of our response
        if (jsonResponse.length() > 0) {
          writer.println(jsonResponse);
        } else {
          writer.println("<h1>Please input a valid request.</h1>");
        }

        dong.close();
      }
    } catch (IOException e) {
      System.out.println("Error opening socket");
      System.exit(1);
    }
  }
}
