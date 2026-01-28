package com.example.todo.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity // DB 테이블이라는 걸 명시
public class Todo {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가
    private Long id;

    @Column(nullable = false) //null 허용 X
    private String title;

    private boolean completed = false;

    // 우선순위
    @Enumerated(EnumType.STRING) // enum을 DB에 String 타입으로 저장하겠다.
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

    public Long getId() { // id를 읽는다.
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
