package id.study.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import id.study.demo.common.model.dto.todos.TodoCreateRequestDTO;
import id.study.demo.common.wrapper.ApiResponse;
import id.study.demo.core.callback.ProcessCallbackSupport;
import id.study.demo.core.callback.ProcessorCallback;
import id.study.demo.core.processors.ProcessorTemplate;
import id.study.demo.core.processors.todo.TodoCreateProcessors;

@Controller
@RequestMapping("/task")
public class TodoController {
    private final TodoCreateProcessors todoCreateProcessor;

    @Autowired
    public TodoController(TodoCreateProcessors todoCreateProcessor) {
        this.todoCreateProcessor = todoCreateProcessor;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<String>> todoCreate(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                                          @RequestBody TodoCreateRequestDTO requestDTO) {
        var result = ProcessCallbackSupport
            .execute(new ProcessorCallback<TodoCreateRequestDTO, String>() {
                @Override
                public TodoCreateRequestDTO composeRequest() {
                    requestDTO.setSessionToken(token);
                    return requestDTO;
                }

                @Override
                public ProcessorTemplate<TodoCreateRequestDTO, String> getProcessors() {
                    return todoCreateProcessor;
                }
            });

        return ResponseEntity.ok(result);
    }
}
