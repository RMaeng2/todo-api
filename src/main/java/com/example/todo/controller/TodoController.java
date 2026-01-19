package com.example.todo.controller;

import com.example.todo.dto.TodoCreateRequest;
import com.example.todo.dto.TodoResponse;
import com.example.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @GetMapping("/")
    public String home() {
        return "Todo API is running";
    }

    @PostMapping("/todos")
    public TodoResponse create(@Valid @RequestBody TodoCreateRequest request) {
        return todoService.create(request.getTitle());
    }

    @GetMapping("/todos")
    public List<TodoResponse> list() {
        return todoService.findAll();
    }
}
