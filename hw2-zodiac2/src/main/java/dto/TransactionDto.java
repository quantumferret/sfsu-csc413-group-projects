package dto;

public class TransactionDto implements Dto {
    private String itemCode;
    private String paymentMethod;

    public TransactionDto(String itemCode, String paymentMethod) {
        this.itemCode = itemCode;
        this.paymentMethod = paymentMethod;
    }

    public TransactionDto() {

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
