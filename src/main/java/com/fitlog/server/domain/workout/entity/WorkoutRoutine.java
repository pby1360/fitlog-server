package com.fitlog.server.domain.workout.entity;

import com.fitlog.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workout_routine", schema = "fitlog", catalog = "")
@Getter
@EntityListeners(AuditingEntityListener.class)
public class WorkoutRoutine extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "workout_program_id")
    private Long workoutProgramId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;
    @Column(name = "duration")
    private Integer duration;
    @OneToMany(mappedBy = "workoutRoutine", cascade = CascadeType.ALL)
    private List<WorkoutRoutinePart> workoutRoutineParts;

    public static WorkoutRoutine create (Long userId,
                                         Long workoutProgramId,
                                         String name,
                                         String description
    ) {
        WorkoutRoutine newRoutine = new WorkoutRoutine();
        newRoutine.status = "10";
        newRoutine.userId = userId;
        newRoutine.workoutProgramId = workoutProgramId;
        newRoutine.name = name;
        newRoutine.description = description;
        newRoutine.workoutRoutineParts = new ArrayList<>();
        return newRoutine;
    }
}
