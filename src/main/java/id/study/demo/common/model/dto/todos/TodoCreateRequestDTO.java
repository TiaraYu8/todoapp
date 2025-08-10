package id.study.demo.common.model.dto.todos;

import id.study.demo.common.model.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

@Setter
@Getter
public class TodoCreateRequestDTO extends BaseDTO {
    private String title;
    private String description;

    @JsonIgnore
    private String sessionToken;
}
