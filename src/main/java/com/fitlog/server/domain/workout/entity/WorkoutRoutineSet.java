package com.fitlog.server.domain.workout.entity;

import com.fitlog.server.common.BaseEntity;
import com.fitlog.server.domain.workout.type.ProgressStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
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
    @ManyToOne
    @JoinColumn(name = "workout_routine_item_id")
    private WorkoutRoutineItem workoutRoutineItem;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;
    @Column(name = "`order`")
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

    public static WorkoutRoutineSet create(WorkoutRoutineItem workoutRoutineItem, String description, Integer order, Integer targetCount, Integer targetRestTime) {
        WorkoutRoutineSet newSet = new WorkoutRoutineSet();
        newSet.status = "10";
        newSet.workoutRoutineItem = workoutRoutineItem;
        newSet.description = description;
        newSet.order = order;
        newSet.targetCount = targetCount;
        newSet.targetRestTime = targetRestTime;
        return newSet;
    }

    public void complete(int count) {
        this.count = count;
        this.status = ProgressStatus.완료.getCode();
    }

    public void clear () {
        this.count = 0;
        this.status = ProgressStatus.대기.getCode();
    }
}
