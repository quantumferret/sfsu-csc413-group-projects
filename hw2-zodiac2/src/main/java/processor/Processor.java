package processor;

import dto.ResponseDto;

import java.util.Map;

public interface Processor {
    public ResponseDto process(Map<String, String> args);
}
