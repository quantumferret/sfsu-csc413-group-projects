package dto;

public class FailedAuthDto {
    public final Boolean success;
    public final String error;

    public FailedAuthDto(Boolean success, String error) {
        this.success = success;
        this.error = error;
    }
}
