package com.api.constants;

public enum StatusCode {

    SUCCESS(200),
    CREATED(201),
    BAD_REQUEST(400),
    NO_CONTENT(204),
    NO_RESOURCES(404);

    public final int code;

    StatusCode (int code ){
        this.code = code;
    }

}
