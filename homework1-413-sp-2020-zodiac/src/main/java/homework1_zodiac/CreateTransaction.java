package homework1_zodiac;

import homework1_zodiac.response.Response;
import homework1_zodiac.dtos.TransactionDTO;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CreateTransaction implements Processor {
    private Map<String,String> args = new HashMap<>();


    CreateTransaction(Map<String, String> args) {
        this.args = args;
    }


    public Response process() {
		if (args.containsKey("itemCode") && args.containsKey("paymentMethod")) {
			ItemDAO itemDAO = ItemDAO.getInstance();
			PaymentDAO paymentDAO = PaymentDAO.getInstance();

			//Check that the item and payment exist.
			if(itemDAO.getAllMachineCodes().contains(args.get("itemCode"))
				&& paymentDAO.getAllMachineCodes()
					.contains(args.get("paymentMethod")))
			{
				TransactionDTO transactionDTO = new TransactionDTO(
						args.get("itemCode"),
						args.get("paymentMethod"));
				TransactionDAO transactionDAO = TransactionDAO.getInstance();
				transactionDAO.createTransaction(transactionDTO);

				return new Response
						.ResponseBuilder()
						.setResponseCode("OK")
						.setResponse(Collections.emptyList())
						.setDate()
						.setParams(args)
						.build();
			} else {
				return new Response.ResponseBuilder()
						.setDate().setParams(args).setResponseCode("ERROR")
						.setResponse(Collections.emptyList()).build();
			}
		} else {
			return new Response.ResponseBuilder()
					.setDate().setParams(args).setResponseCode("ERROR")
					.setResponse(Collections.emptyList()).build();
		}
    }
}
