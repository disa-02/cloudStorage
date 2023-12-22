package com.cloudStorage.exception;


public record ApiErrorDto(
    String message,
    String backendMessage,
    String method,
    String url
){

}
