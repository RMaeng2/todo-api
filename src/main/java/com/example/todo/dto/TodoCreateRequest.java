package com.example.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(
        description = "Todo 생성 요청 DTO",
        example = """
                {
                    "title": "Todo 프로젝트 만들기"
                }
                """
)
public class TodoCreateRequest {

    @Schema(
            description = "할 일 내용",
            example = "Spring Boot 학습하기",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 1,
            maxLength = 100
    )

    @NotBlank (message = "제목은 필수입니다.")
    @Size(max = 100, message = "제목은 100자를 초과할 수 없습니다.")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
