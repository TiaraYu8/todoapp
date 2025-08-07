package id.study.demo.common.model.vo.users;

import id.study.demo.common.model.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserView extends BaseVO {
    private String id;
    private String username;
}
