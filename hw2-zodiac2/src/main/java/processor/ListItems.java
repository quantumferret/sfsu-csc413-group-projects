package processor;

import dto.ResponseDto;
import dao.ItemDAO;
import java.util.Map;

public class ListItems implements Processor {
    @Override
    public ResponseDto process(Map<String, String> args) {
        ItemDAO db = ItemDAO.getInstance();
        return new ResponseDto.ResponseBuilder()
                .setDate().setParams(args).setResponseCode("OK")
                .setResponse(db.listItems()).build();
    }
}