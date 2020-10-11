package homework1_zodiac;

import homework1_zodiac.response.Response;

import java.util.HashMap;
import java.util.Map;


public class GetAllPaymentMethods implements Processor {
    private Map<String,String> args = new HashMap<>();


    GetAllPaymentMethods(Map<String, String> args) {
        this.args = args;
    }


    @Override
    public Response process() {

        PaymentDAO pDAO = PaymentDAO.getInstance();

        Response.ResponseBuilder builder = new Response.ResponseBuilder().setResponseCode("OK").setDate().setResponse(pDAO.getAllPaymentMethods());

        return builder.build();
    }

}
