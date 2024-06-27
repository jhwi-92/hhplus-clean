package com.hhplus.cleanarchitecture.exception;

import java.util.List;

public record ErrorResponse(
    String code,
    String message,

    List<String> error
) {
    // 기본 생성자
    public ErrorResponse(String code, String message) {
        this(code, message, null);
    }
}
