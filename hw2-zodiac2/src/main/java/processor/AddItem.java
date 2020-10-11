package processor;

import dao.ItemDAO;
import dto.ResponseDto;
import dto.ItemDto;
import java.util.Collections;
import java.util.Map;

public class AddItem implements Processor {
    @Override
    public ResponseDto process(Map<String, String> args) {
        ItemDAO db = ItemDAO.getInstance();
        ItemDto item = new ItemDto();

        if (args.containsKey("name") && args.containsKey("price")) {
            item.setName(args.get("name"));
            item.setPrice(args.get("price"));
            db.addItem(item);

            return new ResponseDto.ResponseBuilder()
                    .setDate().setParams(args).setResponseCode("OK")
                    .setResponse(Collections.emptyList()).build();
        } else {
            return new ResponseDto.ResponseBuilder()
                    .setDate().setParams(args).setResponseCode("ERROR")
                    .setResponse(Collections.emptyList()).build();

        }
    }
}