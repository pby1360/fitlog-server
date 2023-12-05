package com.fitlog.server.domain.workout.entity;

import com.fitlog.server.common.BaseEntity;
import com.fitlog.server.domain.workout.dto.WorkoutProgramPartItemSetDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class WorkoutProgramPartItemSet extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long workoutProgramPartItemId;
    private int count;
    @Column(name = "`order`")
    private int order;
    private int restTime;
    private String description;

    public static WorkoutProgramPartItemSet create (WorkoutProgramPartItemSetDto dto) {

        WorkoutProgramPartItemSet newEntity = new WorkoutProgramPartItemSet();

        newEntity.workoutProgramPartItemId = dto.workoutProgramPartItemId();
        newEntity.count = dto.count();
        newEntity.order = dto.order();
        newEntity.restTime = dto.restTime();
        newEntity.description = dto.description();

        return newEntity;
    }

    public void modify (WorkoutProgramPartItemSetDto dto) {
        this.workoutProgramPartItemId = dto.workoutProgramPartItemId();
        this.count = dto.count();
        this.order = dto.order();
        this.restTime = dto.restTime();
        this.description = dto.description();
    }
}
