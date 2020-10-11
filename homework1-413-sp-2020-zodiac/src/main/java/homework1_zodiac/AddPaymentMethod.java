package homework1_zodiac;


import homework1_zodiac.dtos.PaymentDTO;
import homework1_zodiac.response.Response;

import java.util.Collections;
import java.util.UUID;


import java.util.Map;
import java.util.HashMap;

class AddPaymentMethod implements Processor {

    private Map<String,String> args = new HashMap<>();


    AddPaymentMethod(Map<String, String> args) {
        this.args = args;
    }


    @Override
    public Response process() {

        if (args.containsKey("method")) {
            String name = (String)args.get("method");
            String machineCode = UUID.randomUUID().toString(); //placeholder

            PaymentDTO pDTO = new PaymentDTO(name,machineCode);
            PaymentDAO pDAO = PaymentDAO.getInstance();
            pDAO.addPaymentMethod(pDTO);
            Response.ResponseBuilder builder = new Response.ResponseBuilder().setResponseCode("OK").setResponse(Collections.emptyList()).setDate().setParams(args);
            return builder.build();
        } else {

            Response.ResponseBuilder builder = new Response.ResponseBuilder().setResponseCode("ERROR").setResponse(Collections.emptyList()).setDate().setParams(args);


            return builder.build();
        }

    }
}
