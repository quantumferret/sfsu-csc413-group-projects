package dto;
public class SuccessfulAuthDto {
    // server to client
    public final Boolean success;

    public SuccessfulAuthDto(Boolean success)
    {
        this.success = success;
    }
}
