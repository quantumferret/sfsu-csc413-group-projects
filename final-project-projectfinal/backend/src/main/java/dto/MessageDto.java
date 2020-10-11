package dto;

public class MessageDto {
    public final String message;
    public final String date;
    public final String username;
    public final int likes;

    public MessageDto(String message, String date, String username, int likes) {
        this.message = message;
        this.date = date;
        this.username = username;
        this.likes = likes;
    }

}
