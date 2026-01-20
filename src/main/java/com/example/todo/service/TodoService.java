package com.example.todo.service;

import com.example.todo.domain.Todo;
import com.example.todo.dto.TodoResponse;
import com.example.todo.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    @Transactional
    public TodoResponse complete(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found" + id));

        todo.complete();
        return TodoResponse.from(todo);
    }
}
