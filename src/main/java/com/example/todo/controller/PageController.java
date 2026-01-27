package com.example.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/ui/todos")
    public String todosPage() {
        return "todos"; // templates/todos.html
    }
}
