package com.fitlog.server.domain.workout.entity;

import com.fitlog.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class WorkoutProgramPartItemSetting extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long workoutProgramPartItemId;
    private int order;
    private int count;
    private String description;
}
