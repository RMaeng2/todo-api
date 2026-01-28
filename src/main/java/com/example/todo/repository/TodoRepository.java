package com.example.todo.repository;

import com.example.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // save() 메서드는 JpaRepository가 제공해준다. -> JPA가 자동으로 INSERT SQL을 실행한다.
}
