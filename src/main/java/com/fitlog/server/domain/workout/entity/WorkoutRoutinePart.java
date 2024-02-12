package com.fitlog.server.domain.workout.entity;

import com.fitlog.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;
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
    @ManyToOne
    @JoinColumn(name = "workout_routine_id")
    private WorkoutRoutine workoutRoutine;
    @Column(name = "workout_part_id")
    private Long workoutPartId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;
    @Column(name = "`order`")
    private Integer order;
    @Column(name = "duration")
    private Integer duration;
    @OneToMany(mappedBy = "workoutRoutinePart", cascade = CascadeType.ALL)
    private List<WorkoutRoutineItem> workoutRoutineItems;

    public static WorkoutRoutinePart create (WorkoutRoutine workoutRoutine,
                                             Long workoutPartId,
                                             String name,
                                             String description,
                                             Integer order
    ) {
        WorkoutRoutinePart newPart = new WorkoutRoutinePart();
        newPart.status = "10";
        newPart.workoutRoutine = workoutRoutine;
        newPart.workoutPartId = workoutPartId;
        newPart.name = name;
        newPart.description = description;
        newPart.order = order;
        newPart.workoutRoutineItems = new ArrayList<>();
        return newPart;
    }
}
