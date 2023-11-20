package com.fitlog.server.domain.workout.entity;

import com.fitlog.server.common.BaseEntity;
import com.fitlog.server.domain.workout.dto.WorkoutProgramDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class WorkoutProgram extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String name;
    private String description;

    public static WorkoutProgram create(WorkoutProgramDto dto) {
        WorkoutProgram newProgram = new WorkoutProgram();
        newProgram.userId = dto.userId();
        newProgram.name = dto.name();
        newProgram.description = dto.description();
        newProgram.setMemo(dto.memo());
        return newProgram;
    }

    public WorkoutProgram modify(WorkoutProgramDto dto) {
        this.name = dto.name();
        this.description = dto.description();
        this.setMemo(dto.memo());
        return this;
    }
}
