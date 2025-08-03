package id.study.demo.common.model.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Setter
@Getter
@ToString(exclude = "password")
public class UserModel implements Serializable{
    private static final long serialVersionUID = -2452540545116811713L;

    @Id
    @Column(updatable = false, nullable = false)
    private String id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
