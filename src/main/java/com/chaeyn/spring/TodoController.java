package com.chaeyn.spring;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// 이 클래스가 API 요청을 처리하는 핸들러임을 선언
@RestController
@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE})
public class TodoController {
  // import 하는 느낌
  private final TodoService todoService;

  // 생성자를 통한 의존성 주입 (DI)
  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  // API 엔드포인트
  @GetMapping("/api/todos")
  public List<Todo> getTodos() {
    // Service의 findAll 메서드 호출
    List<Todo> todos = todoService.findAll();
    // List<Todo> 객체를 반환하면 자동으로 JSON으로 변환됨
    return todos;
  }

  @PostMapping("/api/todos")
  public Todo createTodo(@RequestBody TodoRequest request) {
    // @Request JSON 자동으로 TodoRequest 객체로 변환
    Todo savedTodo = todoService.save(request);
    return savedTodo;
  }

  @DeleteMapping("/api/todos/{id}")
  public void deleteTodo(@PathVariable Long id) {
    todoService.deleteById(id);
  }

  @PatchMapping("/api/todos/{id}/toggle")
  public Todo toggleTodoCompleted(@PathVariable Long id) {
    return todoService.toggleCompleted(id);
  }
}
