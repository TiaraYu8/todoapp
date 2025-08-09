package id.study.demo.controllers;


import id.study.demo.common.model.dto.todos.TodoCreateRequestDTO;
import id.study.demo.common.wrapper.ApiResponse;
import id.study.demo.core.callback.ProcessCallback;
import id.study.demo.core.processors.todo.TodoCreateProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/task")
public class TodoController {
    private final TodoCreateProcessor todoCreateProcessor;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Map<String, String>>> todoCreate(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
            @RequestBody TodoCreateRequestDTO requestDTO){
        return ProcessCallback.process(() -> {
            String taskId = todoCreateProcessor.process(token, requestDTO);
            return Map.of("taskId", taskId);
        });
    }
}
