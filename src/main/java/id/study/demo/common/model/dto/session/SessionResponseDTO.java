package id.study.demo.common.model.dto.session;

import id.study.demo.common.model.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class SessionResponseDTO extends BaseDTO {
    private String id;
    private String userId;
    private boolean expired;
    private LocalDateTime expiredDate;
}
