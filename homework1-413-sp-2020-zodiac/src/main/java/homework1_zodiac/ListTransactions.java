package homework1_zodiac;

import homework1_zodiac.response.Response;

import java.util.Map;
import java.util.HashMap;

public class ListTransactions implements Processor {
    private Map<String,String> args = new HashMap<>();


    ListTransactions(Map<String, String> args) {
        this.args = args;
    }


    @Override
    public Response process() {
    	TransactionDAO transactionDAO = TransactionDAO.getInstance();
        return new Response
        			.ResponseBuilder()
                	.setDate()
                	.setParams(args)
                	.setResponseCode("OK")
                	.setResponse(transactionDAO.listTransactions())
                	.build();
    }
}
