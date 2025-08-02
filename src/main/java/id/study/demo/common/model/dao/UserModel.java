package id.study.demo.common.model.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "user")
@Setter
@Getter
public class UserModel implements Serializable{
    private static final long serialVersionUID = -2452540545116811713L;

    private String UUID;
    private String username;
    private String email;
    private String password;
}
