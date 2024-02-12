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
@Table(name = "workout_routine_item", schema = "fitlog", catalog = "")
@Getter
@EntityListeners(AuditingEntityListener.class)
public class WorkoutRoutineItem extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "workout_routine_part_id")
    private WorkoutRoutinePart workoutRoutinePart;
    @Column(name = "workout_part_item_id")
    private Long workoutPartItemId;
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
    @OneToMany(mappedBy = "workoutRoutineItem", cascade = CascadeType.ALL)
    private List<WorkoutRoutineSet> workoutRoutineSets;

    public static WorkoutRoutineItem create (WorkoutRoutinePart workoutRoutinePart,
                                             Long workoutPartItemId,
                                             String name,
                                             String description,
                                             Integer order
                                             ) {
        WorkoutRoutineItem newItem = new WorkoutRoutineItem();
        newItem.status = "10";
        newItem.workoutRoutinePart = workoutRoutinePart;
        newItem.workoutPartItemId = workoutPartItemId;
        newItem.name = name;
        newItem.description = description;
        newItem.order = order;
        newItem.workoutRoutineSets = new ArrayList<>();
        return newItem;
    }
}
