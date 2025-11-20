package com.chaeyn.spring;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@Transactional // 메서드 실행 시 DB 트랜잭션을 자동으로 관리
public class TodoService {
  // Repository 주입
  private final TodoRepository todorepository;

  public TodoService(TodoRepository todorepository) {
    this.todorepository = todorepository;
  }

  public List<Todo> findAll() {
    return todorepository.findAll();
  }

  @Transactional
  public Todo save(TodoRequest request) {
    // Request DTO를 Entity로 변환
    Todo newTodo = new Todo(request.getText());
    // Repository를 통해 DB에 저장하고 Entity에 변환
    return todorepository.save(newTodo);
  }

  @PostConstruct // 서버 시작된 직후 이 메서드 자동 실행
  public void initDate() {
    if (todorepository.count() == 0) {
      todorepository.save(new Todo("Spring 공부하기"));
      todorepository.save(new Todo("쉐어 개발하기"));
      todorepository.save(new Todo("조상철 개발 공부 시키기"));
    }
  }

  @Transactional
  public void deleteById(Long id) {
    todorepository.deleteById(id);
  }

  @Transactional
  public Todo toggleCompleted(Long id) {
    // 해당 Id 엔티티 찾고 없으면 예외
    Todo todo = todorepository.findById(id).orElseThrow(() -> new RuntimeException("ID" + id + "번 할 일을 찾을 수 없습니다."));
    todo.setCompleted(!todo.isCompleted());
    return todorepository.save(todo);
  }
}