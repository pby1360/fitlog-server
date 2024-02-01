package com.fitlog.server.domain.workout.entity;

import com.fitlog.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Entity
@Table(name = "workout_routine_part", schema = "fitlog", catalog = "")
@EntityListeners(AuditingEntityListener.class)
@Getter
public class WorkoutRoutinePart extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "workout_routine_id")
    private Long workoutRoutineId;
    @Column(name = "workout_part_id")
    private Long workoutPartId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;
    @Column(name = "order")
    private Integer order;
    @Column(name = "duration")
    private Integer duration;
}
