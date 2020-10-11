package homework1_zodiac;

import homework1_zodiac.response.Response;
import homework1_zodiac.dtos.ItemDTO;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class AddItem implements Processor {
    private Map<String,String> args = new HashMap<>();


    AddItem(Map<String, String> args) {
        this.args = args;
    }


    @Override
    public Response process() {
        ItemDAO db = ItemDAO.getInstance();
        ItemDTO item = new ItemDTO();

        if (args.containsKey("name") && args.containsKey("price")) {
            item.setName(args.get("name"));
            item.setPrice(args.get("price"));
            item.setMachineCode(UUID.randomUUID().toString());
            db.addItem(item);

            return new Response.ResponseBuilder()
                    .setDate().setParams(args).setResponseCode("OK")
                    .setResponse(Collections.emptyList()).build();
        } else {
            return new Response.ResponseBuilder()
                    .setDate().setParams(args).setResponseCode("ERROR")
                    .setResponse(Collections.emptyList()).build();

        }
    }
}