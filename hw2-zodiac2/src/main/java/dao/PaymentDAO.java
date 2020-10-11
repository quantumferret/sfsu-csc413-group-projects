package dao;

import com.google.gson.Gson;
import dto.Dto;
import dto.PaymentDto;
import java.util.*;
import java.util.stream.Collectors;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;


public class PaymentDAO {
    //Singleton w/ Lazy Loading
    private static PaymentDAO instance;
    private static MongoDatabase db = MongoConnection.mongoClient.getDatabase("ZodiacHomework2");
    private static MongoCollection<Document> paymentMethodCollection = db.getCollection("PaymentMethods");
    private static Gson gson = new Gson();


    public static PaymentDAO getInstance() {
        if (instance == null) {
            instance = new PaymentDAO();
        }
        return instance;
    }

    //private constructor ensures people use getInstance()
    private PaymentDAO() {
    }

    public void addPaymentMethod(PaymentDto paymentMethod) {
        String jsonString = gson.toJson(paymentMethod);
        Document newPaymentMethod = new Document("paymentMethod", jsonString);
        paymentMethodCollection.insertOne(newPaymentMethod);
    }


    public List<Dto> getAllPaymentMethods() {
        List<Dto> paymentList = paymentMethodCollection.find().into(new ArrayList<>())
                .stream()
                .map(document -> {
                    String fields = document.getString("paymentMethod");
                    PaymentDto payment = gson.fromJson(fields, PaymentDto.class);
                    payment.setMachineCode(document.getObjectId("_id").toString());
                    return payment;
                })
                .collect(Collectors.toList());
        return paymentList;
    }

    public Boolean paymentExists(String paymentCode) {
        Document doc = new Document();
        try {
            doc = paymentMethodCollection.
                    find(eq("_id", new ObjectId(paymentCode))).first();
        } finally {
            return !doc.isEmpty();
        }
    }


}
