package com.vitaapp.backend.tesis.domain.message;

public class ResponsePersonalized {
    private Integer code;

    private String message;

    public ResponsePersonalized() {}

    public ResponsePersonalized(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
