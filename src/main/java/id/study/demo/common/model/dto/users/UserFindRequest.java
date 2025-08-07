package id.study.demo.common.model.dto.users;

import id.study.demo.common.model.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserFindRequest extends BaseDTO {
    private String sessionId;
}
