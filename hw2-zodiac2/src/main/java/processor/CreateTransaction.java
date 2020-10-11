package processor;

import dto.ResponseDto;
import dto.*;
import dao.*;
import java.util.Collections;
import java.util.Map;

public class CreateTransaction implements Processor {
    public ResponseDto process(Map<String, String> args) {
		if (args.containsKey("itemCode") && args.containsKey("paymentMethod")) {
			ItemDAO itemDAO = ItemDAO.getInstance();
			PaymentDAO paymentDAO = PaymentDAO.getInstance();

			//Check that the item and payment exist.
			if(itemDAO.itemExists(args.get("itemCode"))
				&& paymentDAO.paymentExists(args.get("paymentMethod")))
			{
				TransactionDto transactionDTO = new TransactionDto(
						args.get("itemCode"),
						args.get("paymentMethod"));
				TransactionDAO transactionDAO = TransactionDAO.getInstance();
				transactionDAO.createTransaction(transactionDTO);
				return new ResponseDto
						.ResponseBuilder()
						.setResponseCode("OK")
						.setResponse(Collections.emptyList())
						.setDate()
						.setParams(args)
						.build();
			} else {
				return new ResponseDto.ResponseBuilder()
						.setDate().setParams(args).setResponseCode("ERROR")
						.setResponse(Collections.emptyList()).build();
			}
		} else {
			return new ResponseDto.ResponseBuilder()
					.setDate().setParams(args).setResponseCode("ERROR")
					.setResponse(Collections.emptyList()).build();
		}
    }
}
