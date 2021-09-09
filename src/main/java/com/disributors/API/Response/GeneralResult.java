package com.disributors.API.Response;

public class GeneralResult<T> {
    private Boolean error;
    private T result;
    private String message;

    public GeneralResult() {}

    public GeneralResult(Boolean error, T result, String message) {
        this.setError(error);
        this.setResult(result);
        this.setMessage(message);
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Boolean getError() {
        return this.error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }
}
