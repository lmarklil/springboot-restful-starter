package com.neko.seed.common.result;

public class Result {
    public static ResultDTO success() {
        return new ResultDTO(200, "SUCCESS", null);
    }

    public static <T> ResultDTO success(T data) {
        return new ResultDTO(200, "SUCCESS", data);
    }

    public static ResultDTO fail(int errorCode, String message) {
        return new ResultDTO(400, message, errorCode);
    }

    public static ResultDTO fail(int errorCode, String message,int code) {
        return new ResultDTO(code, message, errorCode);
    }

    public static ResultDTO error(String message,int code) {
        return new ResultDTO(code, message, null);
    }
}