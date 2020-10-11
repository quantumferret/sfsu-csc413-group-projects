package dto;

import java.util.ArrayList;
import java.util.List;

public class MessageListDto {
    public List<MessageDto> chat;

    public MessageListDto() {
        this.chat = new ArrayList<MessageDto>();
    }

}
