package com.fitlog.server.domain.workout.entity;

import com.fitlog.server.common.BaseEntity;
import com.fitlog.server.domain.workout.dto.WorkoutPartDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class WorkoutPart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String name;
    private String description;
    @OneToMany(mappedBy = "workoutPart", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WorkoutPartItem> workoutPartItems = new ArrayList<>();

    public static WorkoutPart create (WorkoutPartDto dto) {
        WorkoutPart entity = new WorkoutPart();
        entity.userId = dto.getUserId();
        entity.name = dto.getName();
        entity.description = dto.getDescription();
        return entity;
    }

    public void modify (WorkoutPartDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.setMemo(dto.getMemo());
    }

    public static WorkoutPart of (Long id) {
        WorkoutPart entity = new WorkoutPart();
        entity.id = id;
        return entity;
    }

    @Override
    public String toString() {
        return "WorkoutPart{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", workoutPartItems=" + workoutPartItems +
                '}';
    }
}
