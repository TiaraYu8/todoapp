package id.study.demo.common.model.vo.todo;

import id.study.demo.common.model.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoView extends BaseDTO {
    private String id;
    private String userId;
    private String title;
    private String description;
    private boolean completed;
}
