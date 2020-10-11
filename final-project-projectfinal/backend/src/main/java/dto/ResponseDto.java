package dto;

public class ResponseDto {

    public final String error;
    public final boolean success;

    public ResponseDto(String error, boolean success) {
        this.error = error;
        this.success = success;
    }



}
