package com.fitlog.server.domain.workout.entity;

import com.fitlog.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class WorkoutProgramPartItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long workoutProgramPartId;
    private Long workoutPartItemId;
    private int order;
    private int totalSet;
    private int totalCount;
    private int restTime;
    private String description;
}
