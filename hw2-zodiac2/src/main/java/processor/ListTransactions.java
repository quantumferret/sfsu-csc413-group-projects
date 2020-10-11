package processor;

import dao.TransactionDAO;
import dto.ResponseDto;
import java.util.Map;

public class ListTransactions implements Processor {
    @Override
    public ResponseDto process(Map<String, String> args) {
    	TransactionDAO transactionDAO = TransactionDAO.getInstance();
        return new ResponseDto
        			.ResponseBuilder()
                	.setDate()
                	.setParams(args)
                	.setResponseCode("OK")
                	.setResponse(transactionDAO.listTransactions())
                	.build();
    }
}
