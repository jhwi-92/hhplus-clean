package com.hhplus.cleanarchitecture.exception;

public record ErrorResponse(
    String code,
    String message
) {

}
