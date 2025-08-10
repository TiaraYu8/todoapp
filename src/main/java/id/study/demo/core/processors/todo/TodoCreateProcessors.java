package id.study.demo.core.processors.todo;

import java.util.Optional;

import org.springframework.stereotype.Component;

import id.study.demo.common.exception.BadRequestException;
import id.study.demo.common.model.dto.session.SessionResponseDTO;
import id.study.demo.common.model.dto.todos.TodoCreateRequestDTO;
import id.study.demo.common.model.dto.todos.TodoResponseDTO;
import id.study.demo.common.utils.ParamChecker;
import id.study.demo.core.processors.ProcessorTemplate;
import id.study.demo.services.SessionService;
import id.study.demo.services.TodoService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TodoCreateProcessors implements ProcessorTemplate<TodoCreateRequestDTO, String> {
    private final SessionService sessionService;
    private final TodoService    todoService;

    public String process(TodoCreateRequestDTO requestDTO) {
        var sessionToken = requestDTO.getSessionToken();

        ParamChecker.notEmpty(requestDTO.getTitle(), "title");
        ParamChecker.notEmpty(requestDTO.getDescription(), "description");

        System.out.println("session token:" + sessionToken);
        System.out.println("title" + requestDTO.getTitle());
        System.out.println("description" + requestDTO.getDescription());

        Optional<SessionResponseDTO> session = sessionService.findUserId(sessionToken);
        System.out.println("session token: " + session);
        if (session.isEmpty()) {
            throw new BadRequestException("Session is invalid or expired");
        }

        String userId = session.get().getUserId();
        System.out.println("userId" + userId);
        TodoResponseDTO todoId = todoService.createTodo(requestDTO, String.valueOf(userId));

        return todoId.getId();
    }
}
