package dto;

public class SuccessfulAuthDto {
    // server to client
    public final Boolean success;
    public final Boolean adminStatus;

    public SuccessfulAuthDto
            (
            Boolean success,
            Boolean adminStatus
            )
    {
        this.success = success;
        this.adminStatus = adminStatus;
    }
}
