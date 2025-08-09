package id.study.demo.common.model.dto.todos;

import id.study.demo.common.model.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoResponseDTO extends BaseDTO {
    private String id;
    private String userId;
    private String title;
    private String description;
    private boolean completed;
    private boolean deleted;
}
