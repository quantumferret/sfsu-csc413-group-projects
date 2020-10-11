package dto;

// from front end to back end
public class UserDto {
    public final String username;
    public final String password;
    public final Boolean adminStatus;

    public UserDto(String username, String password, Boolean adminStatus) {
        this.username = username;
        this.password = password;
        this.adminStatus = adminStatus;
    }
}
