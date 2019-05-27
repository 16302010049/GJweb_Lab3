package com.example.hello.response;

public class ErrorResponse {
    String info;

    public ErrorResponse(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
