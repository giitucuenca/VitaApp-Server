package com.vitaapp.backend.tesis.domain.message;

import java.util.ArrayList;
import java.util.List;

public class ResponsePersonalized {
    private Integer code;

    private String message;

    private Object data;

    private List<String> errors = new ArrayList<>();

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

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
