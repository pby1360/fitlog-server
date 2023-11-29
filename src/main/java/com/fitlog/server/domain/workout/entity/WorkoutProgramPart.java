package com.fitlog.server.domain.workout.entity;

import com.fitlog.server.common.BaseEntity;
import com.fitlog.server.domain.workout.dto.WorkoutProgramPartDto;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class WorkoutProgramPart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long workoutProgramId;
    @ManyToOne
    private WorkoutPart workoutPart;
//    private Long workoutPartId;
    private String description;

    public static WorkoutProgramPart create (WorkoutProgramPartDto dto) {
        WorkoutProgramPart entity = new WorkoutProgramPart();
        entity.workoutProgramId = dto.workoutProgramId();
        entity.workoutPart = WorkoutPart.of(dto.workoutPartId());
//        entity.workoutPartId = dto.workoutPartId();
        entity.description = dto.description();
        return entity;
    }

    public void modify (WorkoutProgramPartDto dto) {
        this.description = dto.description();
    }
}
