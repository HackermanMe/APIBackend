package com.api.backend.utils;

import java.util.Date;

public class GlobalResponse<T> {
    private Date timestamp;
    private boolean isError;
    private String message;
    private T data;

    public GlobalResponse(Date timestamp, boolean isError, String message, T data) {
        this.timestamp = timestamp;
        this.isError = isError;
        this.message = message;
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}