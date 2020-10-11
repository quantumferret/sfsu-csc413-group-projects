import static spark.Spark.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ResponseDto;
import java.util.Map;
import java.util.HashMap;
import processor.*;


/*
 * This replaces the current Main.java file, which will be removed once
 * SparkApp.java is fully working.
 */
public class SparkApp {
    public static void main(String[] args) {
        port(1299);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Each endpoint will have a lambda expresssion that handles the route
        // and instantiates that endpoint's processor.
        get("/addPaymentMethod", (request, response) -> {
            response.type("application/json");
            Processor proc = new AddPaymentMethod();
            Map<String, String> queryArgs = new HashMap<>();
            for (String param : request.queryParams()) {
                queryArgs.put(param, request.queryParams(param));
            }
            ResponseDto responseBody = proc.process(queryArgs);
            return responseBody;
        }, gson::toJson);


        get("getAllPaymentMethods", (request, response) -> {
            response.type("application/json");
            Processor proc = new GetAllPaymentMethods();
            Map<String, String> queryArgs = new HashMap<>();
            for (String param : request.queryParams()) {
                queryArgs.put(param, request.queryParams(param));
            }
            ResponseDto responseBody = proc.process(queryArgs);
            return responseBody;
        }, gson::toJson);


        get("/addItem", (request, response) -> {
            response.type("application/json");
            Processor proc = new AddItem();
            Map<String,String> queryArgs = new HashMap<>();
            for (String param: request.queryParams()) {
                queryArgs.put(param, request.queryParams(param));
            }
            ResponseDto responseBody = proc.process(queryArgs);
            return responseBody;
        }, gson::toJson);


        get("/listItems", (request, response) -> {
            response.type("application/json");
            Processor proc = new ListItems();
            Map<String,String> queryArgs = new HashMap<>();
            for (String param: request.queryParams()) {
                queryArgs.put(param, request.queryParams(param));
            }
            ResponseDto responseBody = proc.process(queryArgs);
            return responseBody;
        }, gson::toJson);


        get("createTransaction", (request, response) -> {
            response.type("application/json");
            Processor proc = new CreateTransaction();
            Map<String,String> queryArgs = new HashMap<>();
            for (String param: request.queryParams()) {
                queryArgs.put(param, request.queryParams(param));
            }
            ResponseDto responseBody = proc.process(queryArgs);
            return responseBody;
        }, gson::toJson);


        get("/listTransactions", (request, response) -> {
            response.type("application/json");
            Processor proc = new ListTransactions();
            Map<String,String> queryArgs = new HashMap<>();
            for (String param: request.queryParams()) {
                queryArgs.put(param, request.queryParams(param));
            }
            ResponseDto responseBody = proc.process(queryArgs);
            return responseBody;
        }, gson::toJson);
    }
}
