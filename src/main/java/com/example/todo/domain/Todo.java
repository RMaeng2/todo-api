package com.example.todo.domain;

import jakarta.persistence.*;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private boolean completed = false;

    protected Todo() {
        // JPA 기본 생성자
    }

    public Todo(String title) {
        this.title = title;
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
