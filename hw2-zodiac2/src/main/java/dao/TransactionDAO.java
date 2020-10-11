package dao;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dto.Dto;
import dto.TransactionDto;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionDAO {
    private static TransactionDAO instance;
    private static MongoDatabase db = MongoConnection.mongoClient.getDatabase("ZodiacHomework2");
    private static MongoCollection<Document> transactionCollection = db.getCollection("Transactions");
    private static Gson gson = new Gson();

    // Enforces Singleton-ness and lazy loading via indirect instantiation
    public static TransactionDAO getInstance() {
        if (instance == null) {
            instance = new TransactionDAO();
        }
        return instance;
    }

    // A private constructor, purely decorative.
    private TransactionDAO() {}

    // new transaction is stored in DAO as a homework1_zodiac.TransactionDTO
    public void createTransaction(TransactionDto newTransaction) {
        String jsonString = gson.toJson(newTransaction);
        Document newTransactionDocument = new Document("transaction", jsonString);
        transactionCollection.insertOne(newTransactionDocument);
    }

    // Returns all transactions as a list
    public List<Dto> listTransactions() {
        List<Dto> transactionList = transactionCollection.find().into(new ArrayList<>())
                .stream()
                .map(document -> {
                    String fields = document.getString("transaction");
                    TransactionDto transaction = gson.fromJson(fields, TransactionDto.class);
                    return transaction;
                })
                .collect(Collectors.toList());
        return transactionList;
    }

}
