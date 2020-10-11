package processor;

import dao.PaymentDAO;
import dto.PaymentDto;
import dto.ResponseDto;
import java.util.Collections;
import java.util.UUID;
import java.util.Map;

public class AddPaymentMethod implements Processor {
    @Override
    public ResponseDto process(Map<String, String> args) {

        if (args.containsKey("method")) {
            String name = (String)args.get("method");
            String machineCode = UUID.randomUUID().toString();

            PaymentDto pDTO = new PaymentDto(name,machineCode);
            PaymentDAO pDAO = PaymentDAO.getInstance();
            pDAO.addPaymentMethod(pDTO);
            ResponseDto.ResponseBuilder builder = new ResponseDto.ResponseBuilder().setResponseCode("OK").setResponse(Collections.emptyList()).setDate().setParams(args);
            return builder.build();
        } else {

            ResponseDto.ResponseBuilder builder = new ResponseDto.ResponseBuilder().setResponseCode("ERROR").setResponse(Collections.emptyList()).setDate().setParams(args);


            return builder.build();
        }

    }
}
