package homework1_zodiac;

import java.util.Map;
import java.util.HashMap;

class ProcessorFactory {


    static Processor parseRoute(String route) {
        Map<String, String> initialArgs = new HashMap<>();
        if (route != null) {
            String path = route.split("\\s+")[1];

            String[] splitRequest = path.split("\\?");
            String endpoint = splitRequest[0];

            // check if query is included in url, if so parse params into k-v pairs
            // and add them to the initialArgs HashMap.
            if (splitRequest.length > 1) {
                String query = splitRequest[1];
                for (String param : query.split("&")) {
                    String[] pair = param.split("=", 2);
                    String key = pair[0];
                    String value = pair[1];
                    initialArgs.put(key, value);
                }
            }

            // Create new Processor according to parsed route and pass it
            // the date and any input parameters via HashMap.
            switch (endpoint) {
                case "/addPaymentMethod":
                    return new AddPaymentMethod(initialArgs);

                case "/getAllPaymentMethods":
                    return new GetAllPaymentMethods(initialArgs);

                case "/addItem":
                    return new AddItem(initialArgs);

                case "/listItems":
                    return new ListItems(initialArgs);

                case "/createTransaction":
                    return new CreateTransaction(initialArgs);

                case "/listTransactions":
                    return new ListTransactions(initialArgs);

                default:
                    return null;

            }
        } else {
            return null;
        }
    }
}