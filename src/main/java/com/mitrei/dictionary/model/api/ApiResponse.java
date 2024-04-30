package com.mitrei.dictionary.model.api;

public class ApiResponse<T> {
    private T data;
    private ApiMetaData meta;
    private ApiError error;

    public ApiResponse(T data, ApiMetaData meta) {
        this.data = data;
        this.meta = meta;
        this.error = null;
    }

    public ApiResponse(ApiError error, ApiMetaData meta) {
        this.error = error;
        this.meta = meta;
        this.data = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiMetaData getMeta() {
        return meta;
    }

    public void setMeta(ApiMetaData meta) {
        this.meta = meta;
    }

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }
}
