package com.disributors.API.Response;

public class SuccessResponse<T> extends GeneralResult<T> {
    private T result;

    public SuccessResponse() {}

    public SuccessResponse(T result) {
        this.result = result;
    }

    public GeneralResult<T> getResponse(String responseMessage) {
        GeneralResult<T> resp = new GeneralResult<T>(false, this.result, responseMessage);
        return resp;
    }
}
