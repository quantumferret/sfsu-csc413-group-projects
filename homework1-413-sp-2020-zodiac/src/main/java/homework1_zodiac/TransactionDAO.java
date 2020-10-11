package homework1_zodiac;

import homework1_zodiac.dtos.DTO;
import homework1_zodiac.dtos.TransactionDTO;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    // Map of transactions, stored as homework1_zodiac.TransactionDTO POJOs
    private List<DTO> transactionList = new ArrayList<>();

    private static TransactionDAO instance;

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
    public void createTransaction(TransactionDTO newTransaction) {
        transactionList.add(newTransaction);
    }

    // Returns all transactions as a list
    public List<DTO> listTransactions() {
        return transactionList;
    }

}
