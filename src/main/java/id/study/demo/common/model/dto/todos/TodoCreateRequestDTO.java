package id.study.demo.common.model.dto.todos;

import id.study.demo.common.model.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoCreateRequestDTO extends BaseDTO {
    private String title;
    private String description;
}
