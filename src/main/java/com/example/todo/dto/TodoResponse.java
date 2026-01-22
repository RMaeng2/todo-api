package com.example.todo.dto;

import com.example.todo.domain.Todo;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Todo 응답 DTO")
public class TodoResponse {

    @Schema(description = "Todo ID", example = "1")
    private Long id;
    
    @Schema(description = "Todo 제목", example = "Spring Boot 학습하기")
    private String title;
    
    @Schema(description = "완료 여부", example = "false")
    private boolean completed;

    public TodoResponse(Long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public static TodoResponse from(Todo todo) {
        return new TodoResponse(todo.getId(), todo.getTitle(), todo.isCompleted());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }
}
