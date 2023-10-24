package com.fitlog.server.domain.workout.entity;

import com.fitlog.server.common.BaseEntity;
import com.fitlog.server.domain.workout.dto.WorkoutPartDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class WorkoutPart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String name;
    private String description;

    public static WorkoutPart create (WorkoutPartDto dto) {
        WorkoutPart entity = new WorkoutPart();
        entity.userId = dto.getUserId();
        entity.name = dto.getName();
        entity.description = dto.getDescription();
        return entity;
    }

    @Override
    public String toString() {
        return "WorkoutPart{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
