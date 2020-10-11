package homework1_zodiac.response;

import homework1_zodiac.dtos.DTO;

import java.util.Map;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;


public class Response {
  private final String date;
  private final Map <String, String> params;
  private final String responseCode;
  private final List<DTO> response;

  // private constructor enforces instantiation through ResponseBuilder.
  private Response(ResponseBuilder builder) {
    date = builder.date;
    params = builder.params;
    responseCode = builder.responseCode;
    response = builder.response;
  }

  public static class ResponseBuilder {
    private static String date;
    private Map <String, String> params;
    private static String responseCode;
    private static List<DTO> response;

    public ResponseBuilder() {

    }

    // All setters allow chained calls to the builder.
    public ResponseBuilder setResponseCode(String responseCode) {
      this.responseCode = responseCode;
      return this;
    }

    public ResponseBuilder setDate () {
      SimpleDateFormat simpleDateFormat =
              new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz");
      String date = simpleDateFormat.format(new Date());
      this.date = date;
      return this;
    }

    public ResponseBuilder setParams (Map <String, String> params) {
      this.params = params;
      return this;
    }

    public ResponseBuilder setResponse (List<DTO> response) {
      this.response = response;
      return this;
    }

    public Response build() {
      return new Response(this);
    }
  }
}