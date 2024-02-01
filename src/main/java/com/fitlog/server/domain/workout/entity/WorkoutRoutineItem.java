package com.fitlog.server.domain.workout.entity;

import com.fitlog.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Entity
@Table(name = "workout_routine_item", schema = "fitlog", catalog = "")
@Getter
@EntityListeners(AuditingEntityListener.class)
public class WorkoutRoutineItem extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "workout_routine_part_id")
    private Long workoutRoutinePartId;
    @Column(name = "workout_part_item_id")
    private Long workoutPartItemId;
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
