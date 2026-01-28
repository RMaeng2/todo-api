package com.example.todo.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private boolean completed = false;

    // 우선순위
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Priority priority = Priority.MEDIUM;

    // 마감일
    private LocalDate dueDate;

    protected Todo() {
        // JPA 기본 생성자
    }

    public Todo(String title, Priority priority, LocalDate dueDate) {
        this.title = title;
        this.priority = priority != null ? priority : Priority.MEDIUM;
        this.dueDate = dueDate;
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

    public void complete() {
        this.completed = true;
    }

    public void toggleComplete() {
        this.completed = !this.completed;
    }

    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }
}
