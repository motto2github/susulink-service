package ink.qwer.susulinkservice.dto;

import java.util.HashMap;
import java.util.Map;

public final class ResponseDTO {

    private String code;

    private String msg;

    private final Map<String, Object> data = new HashMap<String, Object>();

    public ResponseDTO() {
        this("0", "");
    }

    public ResponseDTO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public final ResponseDTO set(String code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public final ResponseDTO reset() {
        this.code = "0";
        this.msg = "";
        this.data.clear();
        return this;
    }

    /*
    // deprecated
    public final <T> T putDatum(String key, Object value) {
        // 返回这个key之前的值
        Object o = this.data.put(key, value);
        return o == null ? null : (T) o;
    }
    */

    public ResponseDTO putDatum(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public final <T> T getDatum(String key) {
        Object o = this.data.get(key);
        return o == null ? null : (T) o;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

}
