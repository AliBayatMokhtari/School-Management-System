package com.disributors.API.Response;

public class NotFoundResponse<T> extends GeneralResult<T>{
    private T result;

    public NotFoundResponse() {}

    public NotFoundResponse(T result) {
        this.result = result;
    }

    public GeneralResult<T> getResponse(String responseMessage) {
        GeneralResult<T> resp = new GeneralResult<T>(true, this.result, responseMessage);
        return resp;
    }
}
