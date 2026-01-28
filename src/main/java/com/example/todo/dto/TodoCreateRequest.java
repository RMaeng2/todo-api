package com.example.todo.dto;

import com.example.todo.domain.Priority;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

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

    @Schema(
            description = "우선순위 (HIGH, MEDIUM, LOW)",
            example = "HIGH",
            allowableValues = {"HIGH", "MEDIUM", "LOW"}
    )
    private Priority priority;

    @Schema(
            description = "마감일 (YYYY-MM-DD)",
            example = "2026-01-27"
    )
    private LocalDate dueDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
