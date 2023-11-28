package com.fitlog.server.domain.workout.entity;

import com.fitlog.server.common.BaseEntity;
import com.fitlog.server.domain.workout.dto.WorkoutPartDto;
import com.fitlog.server.domain.workout.dto.WorkoutPartItemDto;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class WorkoutPartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn
//    private WorkoutPart workoutPart;
    private Long workoutPartId;
    private String name;
    private String description;

    public static WorkoutPartItem create (WorkoutPartItemDto dto) {
        WorkoutPartItem entity = new WorkoutPartItem();
//        entity.workoutPart = WorkoutPart.of(dto.getWorkoutPartId());
        entity.workoutPartId = dto.getWorkoutPartId();
        entity.name = dto.getName();
        entity.description = dto.getDescription();
        return entity;
    }

    public void modify (WorkoutPartItemDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.setMemo(dto.getMemo());
    }
}
