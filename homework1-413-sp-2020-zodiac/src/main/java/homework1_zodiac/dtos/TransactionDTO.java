package homework1_zodiac.dtos;

public class TransactionDTO implements DTO {
    private String itemCode;
    private String paymentMethod;

    public TransactionDTO(String itemCode, String paymentMethod) {
        this.itemCode = itemCode;
        this.paymentMethod = paymentMethod;
    }

    public TransactionDTO() {

    }

    public String getItemCode() {
        return itemCode;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}
