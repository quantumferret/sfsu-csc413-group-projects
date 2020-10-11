package homework1_zodiac;

import homework1_zodiac.response.Response;
import java.util.HashMap;
import java.util.Map;

public class ListItems implements Processor {
    private Map<String,String> args = new HashMap<>();


    ListItems(Map<String, String> args) {
        this.args = args;
    }


    @Override
    public Response process() {
        ItemDAO db = ItemDAO.getInstance();
        return new Response.ResponseBuilder()
                .setDate().setParams(args).setResponseCode("OK")
                .setResponse(db.listItems()).build();
    }
}