package com.fitlog.server.domain.workout.entity;

import com.fitlog.server.common.BaseEntity;
import com.fitlog.server.domain.workout.dto.WorkoutProgramPartItemDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class WorkoutProgramPartItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long workoutProgramPartId;
    private Long workoutPartItemId;
    @Column(name = "`order`")
    private int order;
    private String description;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "workoutProgramPardItemId")
//    private List<WorkoutProgramPartItemSet> setList = new ArrayList<>();

    public static WorkoutProgramPartItem create (WorkoutProgramPartItemDto dto) {

        WorkoutProgramPartItem newEntity = new WorkoutProgramPartItem();

        newEntity.workoutProgramPartId = dto.workoutProgramPartId();
        newEntity.workoutPartItemId = dto.workoutPartItemId();
        newEntity.order = dto.order();
        newEntity.description = dto.description();
//        newEntity.setList = dto.setList().stream().map(set -> WorkoutProgramPartItemSet.create(set)).toList();
        return newEntity;
    }

    public void modify (WorkoutProgramPartItemDto dto) {
        this.workoutPartItemId = dto.workoutPartItemId();
        this.description = dto.description();
    }
}
