package com.fitlog.server.domain.workout.entity;

import com.fitlog.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Entity
@Table(name = "workout_routine_set", schema = "fitlog", catalog = "")
@EntityListeners(AuditingEntityListener.class)
@Getter
public class WorkoutRoutineSet extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "workout_routine_item_id")
    private Long workoutRoutineItemId;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;
    @Column(name = "order")
    private Integer order;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "target_count")
    private Integer targetCount;
    @Column(name = "count")
    private Integer count;
    @Column(name = "target_rest_time")
    private Integer targetRestTime;
    @Column(name = "rest_time")
    private Integer restTime;
}
