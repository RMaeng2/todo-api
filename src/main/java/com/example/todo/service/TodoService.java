package com.example.todo.service;

import com.example.todo.domain.Todo;
import com.example.todo.dto.TodoResponse;
import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoResponse create(String title) {
        Todo saved = todoRepository.save(new Todo(title));
        return TodoResponse.from(saved);
    }

    public List<TodoResponse> findAll() {
        return todoRepository.findAll()
                .stream()
                .map(TodoResponse::from)
                .toList();
    }
}
