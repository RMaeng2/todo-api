package com.example.todo.controller;

import com.example.todo.dto.ErrorResponse;
import com.example.todo.dto.TodoCreateRequest;
import com.example.todo.dto.TodoResponse;
import com.example.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(
        name = "Todo List API",
        description = "할 일(Todo) 관리를 위한 CRUD API입니다."
)
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/health")
    @Operation(
            summary = "헬스 체크",
            description = "서버가 정상적으로 실행 중인지 확인"
    )
    @ApiResponse(
            responseCode = "200",
            description = "서버 정상 작동중",
            content = @Content(
                    mediaType = "text/plain",
                    schema = @Schema(type = "string", example = "OK")
            )
    )
    public String health() {
        return "OK";
    }

    @GetMapping("/")
    public String home() {
        return "Todo API is running";
    }

    @PostMapping("/todos")
    @Operation (
            summary = "Todo 생성",
            description = """
                    새로운 할 일을 생성합니다.
                    
                    ### 요청 본문
                    - **title**:할 일 내용(필수, 공백 불가)
                    
                    ### 주의사항
                    - 제목이 비어있거나 공백만 있을 경우 400에러 발생합니다.
                    """
    )
    public TodoResponse create(@Valid @RequestBody TodoCreateRequest request) {
        return todoService.create(request.getTitle());
    }

    @GetMapping("/todos")
    @Operation(
            summary = "Todo 목록 조회",
            description = """
                    모든 할 일 목록을 조회합니다.
                    
                    ### 반환값
                    - 등록된 모든 Todo를 배열로 반환합니다.
                    - 데이터가 없으면 빈 배열([])을 반환합니다.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "조회 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TodoResponse.class)
            )
    )
    public List<TodoResponse> list() {
        return todoService.findAll();
    }

    @PatchMapping("/todos/{id}")
    @Operation(
            summary = "Todo 완료 처리",
            description = """
                    특정 Todo를 완료 상태로 변경합니다.
                    
                    ### 동작
                    - completed 필드를 false → true로 변경
                    - 이미 완료된 Todo를 다시 호출해도 에러 없이 성공
                    
                    ### 에러 케이스
                    - 존재하지 않는 ID: 404 Not Found
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "완료 처리 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TodoResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Todo를 찾을 수 없음",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    public TodoResponse complete(
            @io.swagger.v3.oas.annotations.Parameter(
                    description = "완료 처리할 Todo의 ID",
                    required = true,
                    example = "1"
            )
            @PathVariable Long id
    ) {
        return todoService.complete(id);
    }

    @DeleteMapping("/todos/{id}")
    @Operation(
            summary = "Todo 삭제",
            description = """
                    특정 Todo를 영구적으로 삭제합니다.
                    
                    ### 주의사항
                    - 삭제된 데이터는 복구할 수 없습니다.
                    - 성공 시 204 No Content 응답 (본문 없음)
                    
                    ### 에러 케이스
                    - 존재하지 않는 ID: 404 Not Found
                    """
    )
    @ApiResponse(
            responseCode = "204",
            description = "삭제 성공 (응답 본문 없음)"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Todo를 찾을 수 없음",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    public ResponseEntity<Void> delete(
            @io.swagger.v3.oas.annotations.Parameter(
                    description = "삭제할 Todo의 ID",
                    required = true,
                    example = "1"
            )
            @PathVariable Long id
    ) {
        todoService.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }

    @GetMapping("/todos/{id}")
    @Operation(
            summary = "Todo 단건 조회",
            description = """
                    특정 ID의 할 일을 조회합니다.
                    
                    ### Path Parameter
                    - **id**: 조회할 Todo의 ID (Long 타입)
                    
                    ### 에러 케이스
                    - 존재하지 않는 ID: 404 Not Found
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "조회 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TodoResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Todo를 찾을 수 없음",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    public TodoResponse get(
            @io.swagger.v3.oas.annotations.Parameter(
                    description = "조회할 Todo의 ID",
                    required = true,
                    example = "1"
            )
            @PathVariable Long id
    ) {
        return todoService.findByID(id);
    }

}
