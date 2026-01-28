package com.example.todo.dto;

import com.example.todo.domain.Priority;
import com.example.todo.domain.Todo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Todo 응답 DTO")
public class TodoResponse {

    @Schema(description = "Todo ID", example = "1")
    private Long id;
    
    @Schema(description = "Todo 제목", example = "Spring Boot 학습하기")
    private String title;
    
    @Schema(description = "완료 여부", example = "false")
    private boolean completed;

    @Schema(description = "우선순위", example = "HIGH")
    private Priority priority;

    @Schema(description = "마감일", example = "2026-01-27")
    private LocalDate dueDate;

//    기존 사용하던 생성자는 주석 처리
//    public TodoResponse(Long id, String title, boolean completed) {
//        this.id = id;
//        this.title = title;
//        this.completed = completed;
//    }

//    우선순위, 마감일 컬럼 추가로 인해 수정된 생성자
    public TodoResponse(Long id, String title, boolean completed, Priority priority, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public static TodoResponse from(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.isCompleted(),
                todo.getPriority(),
                todo.getDueDate()
        );
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

    public Priority getPriority() {
        return priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}