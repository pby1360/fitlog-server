package com.fitlog.server.domain.workout.dto;

import com.fitlog.server.domain.workout.entity.WorkoutRoutine;
import com.fitlog.server.domain.workout.type.ProgressStatus;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record WorkoutRoutineDto(
        Long id,
        Long userId,
        Long workoutProgramId,
        String name,
        String description,
        String status,
        Integer duration,
        List<WorkoutRoutinePartDto> workoutRoutinePartList,
        String startedAt,
        String finishedAt,
        String statusName
) {
    public static WorkoutRoutineDto toDto (WorkoutRoutine entity) {
        return new WorkoutRoutineDto (entity.getId(), entity.getUserId(),entity.getWorkoutProgramId(), entity.getName(), entity.getDescription(), entity.getStatus(), entity.getDuration(), entity.getWorkoutRoutineParts().stream().map(WorkoutRoutinePartDto::toDto).toList(), entity.getStartedAt() != null ? entity.getStartedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null, entity.getFinishedAt() != null ? entity.getFinishedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null, ProgressStatus.of(entity.getStatus()).name());
    }

    public static WorkoutRoutineDto toDto (Long id, Long userId, String name, String description, String status, String statusName, String startedAt, String finishedAt) {
        return new WorkoutRoutineDto (id, userId, null, name, description, status, null, null, startedAt, finishedAt, statusName);
    }

}
