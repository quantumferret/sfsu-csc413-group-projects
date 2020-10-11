package dto;

public class StoreResponseDto {
    public final boolean success;
    public final String error;

    public StoreResponseDto(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

}