package ink.qwer.susulinkservice.controller.dto;

import java.util.HashMap;
import java.util.Map;

public final class ResponseDTO {

    private String code;

    private String message;

    private final Map<String, Object> data = new HashMap<String, Object>(0);

    public ResponseDTO() {
        this("0", "");
    }

    public ResponseDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public final ResponseDTO set(String code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public final ResponseDTO reset() {
        this.code = "0";
        this.message = "";
        this.data.clear();
        return this;
    }

    public final <T> T putDatum(String key, Object value) {
        Object o = this.data.put(key, value);
        return o == null ? null : (T) o;
    }

    public final <T> T getDatum(String key) {
        Object o = this.data.get(key);
        return o == null ? null : (T) o;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getData() {
        return data;
    }

}
