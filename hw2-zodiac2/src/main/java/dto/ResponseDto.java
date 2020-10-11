package dto;

import java.util.Map;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;


public class ResponseDto {
  private final String date;
  private final Map <String, String> params;
  private final String responseCode;
  private final List<Dto> response;

  // private constructor enforces instantiation through ResponseBuilder.
  private ResponseDto(ResponseBuilder builder) {
    date = builder.date;
    params = builder.params;
    responseCode = builder.responseCode;
    response = builder.response;
  }

  public static class ResponseBuilder {
    private static String date;
    private Map <String, String> params;
    private static String responseCode;
    private static List<Dto> response;

    public ResponseBuilder() {

    }

    // All setters allow chained calls to the homework2_zodiac.builder.
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

    public ResponseBuilder setResponse (List<Dto> response) {
      this.response = response;
      return this;
    }

    public ResponseDto build() {
      return new ResponseDto(this);
    }
  }
}