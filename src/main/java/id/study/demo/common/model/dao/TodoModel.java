package id.study.demo.common.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "todo")
@Setter
@Getter
@ToString
public class TodoModel implements Serializable {
    private static final long serialVersionUID = -2452540545116811713L;

    @Id
    @Column(updatable = false, nullable = false)
    private String id;

    @Column(nullable = false, updatable = false)
    private String userId;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    private boolean completed;

    @Column
    private boolean deleted;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;

}
