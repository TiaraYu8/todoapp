package id.study.demo.common.model.dto.session;

import id.study.demo.common.model.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SessionCheckRequestDTO extends BaseDTO {
    private String sessionId;
}
