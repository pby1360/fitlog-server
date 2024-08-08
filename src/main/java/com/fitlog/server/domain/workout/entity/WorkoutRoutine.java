package com.fitlog.server.domain.workout.entity;

import com.fitlog.server.common.BaseEntity;
import com.fitlog.server.domain.workout.type.ProgressStatus;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
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
    @Column(name = "started_at")
    private LocalDateTime startedAt;
    @Column(name = "finished_at")
    private LocalDateTime finishedAt;
    @OneToMany(mappedBy = "workoutRoutine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public void start () {
        if (!ProgressStatus.대기.getCode().equals(this.status) && !ProgressStatus.완료.getCode().equals(this.status)) {
            throw new IllegalStateException("대기 상태에서만 시작할 수 있습니다.");
        }
        this.startedAt = LocalDateTime.now();
        this.status = ProgressStatus.진행중.getCode();
        this.finishedAt = null;
    }

    public void finish () {
        if (!ProgressStatus.진행중.getCode().equals(this.status)) {
            throw new IllegalStateException("진행 중 상태에서만 종료할 수 있습니다.");
        }
        this.finishedAt = LocalDateTime.now();
        this.status = ProgressStatus.완료.getCode();
        // TODO: 2024-04-02 duration에 값 넣기
    }

    public void clear () {
        if (ProgressStatus.진행중.getCode().equals(this.status)) {
            throw new IllegalStateException("진행중인 상태에선 초기화할 수 없습니다.");
        }
        this.startedAt = null;
        this.finishedAt = null;
        this.status = ProgressStatus.대기.getCode();
    }
}
