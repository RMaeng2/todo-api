package com.example.todo.service;

import com.example.todo.domain.Priority;
import com.example.todo.domain.Todo;
import com.example.todo.dto.TodoResponse;
import com.example.todo.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoResponse create(String title, Priority priority, LocalDate dueDate) {
        Todo todo = new Todo(title, priority, dueDate); // 새 todo 객체를 생성한다.
        Todo saved = todoRepository.save(todo); // Repository에 저장을 요청한다.
        return TodoResponse.from(saved); // Entity를 DTO로 변환한다.
    }
    // 불변성 유지, 생성 시점에 모든 값 설정, 더 안전함

    public List<TodoResponse> findAll() {
        return todoRepository.findAll()
                .stream()
                .map(TodoResponse::from)
                .toList();
    }

    @Transactional
    public TodoResponse complete(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found : " + id));

        todo.complete();
        return TodoResponse.from(todo);
    }

    @Transactional
    public TodoResponse toggleComplete(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found : " + id));

        todo.toggleComplete();
        return TodoResponse.from(todo);
    }

    @Transactional
    public void delete(Long id) {
        if(!todoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found : " + id);
        }
        todoRepository.deleteById(id);
    }

    public TodoResponse findByID(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found : " + id)
                );
        return TodoResponse.from(todo);
    }

    @Transactional
    public TodoResponse updateTitle(Long id, String newTitle) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found : " + id));

        todo.updateTitle(newTitle);

        return TodoResponse.from(todo);
    }

}
