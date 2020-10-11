package dto;

public class TransactionDto {
    public final String user;
    public final String item;

    public TransactionDto(String user, String item) {
        this.user = user;
        this.item = item;
    }
}