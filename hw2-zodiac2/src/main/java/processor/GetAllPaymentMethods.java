package processor;

import dto.ResponseDto;
import dao.PaymentDAO;
import java.util.Map;


public class GetAllPaymentMethods implements Processor {
    @Override
    public ResponseDto process(Map<String, String> args) {

        PaymentDAO pDAO = PaymentDAO.getInstance();

        ResponseDto.ResponseBuilder builder = new ResponseDto.ResponseBuilder().setResponseCode("OK").setDate().setResponse(pDAO.getAllPaymentMethods());

        return builder.build();
    }

}
