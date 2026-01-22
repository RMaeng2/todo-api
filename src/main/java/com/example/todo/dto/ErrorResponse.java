package com.example.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "에러 응답 DTO")
public class ErrorResponse {
    @Schema(description = "에러 코드", example = "404 NOT_FOUND")
    private final String code;
    
    @Schema(description = "에러 메시지", example = "Todo not found : 1")
    private final String message;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
}