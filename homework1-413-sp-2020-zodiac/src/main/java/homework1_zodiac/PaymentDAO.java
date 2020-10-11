package homework1_zodiac;

import homework1_zodiac.dtos.DTO;
import homework1_zodiac.dtos.PaymentDTO;
import homework1_zodiac.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class PaymentDAO {
    List<DTO> paymentList = new ArrayList<>();
    Set<String> idSet = new HashSet<>();

    //Singleton w/ Lazy Loading
    private static PaymentDAO instance;

    public static PaymentDAO getInstance() {
        if (instance == null) {
            instance = new PaymentDAO();
        }

        return instance;
    }

    //private constructor ensures people use getInstance()
    private PaymentDAO() {
    }

    public void addPaymentMethod(PaymentDTO paymentMethod) {
        paymentList.add(paymentMethod);
         idSet.add(paymentMethod.getMachineCode());
    }

    public Set<String> getAllMachineCodes() {
        return idSet;
    }

    public List<DTO> getAllPaymentMethods() {
        return paymentList;
    }

}
