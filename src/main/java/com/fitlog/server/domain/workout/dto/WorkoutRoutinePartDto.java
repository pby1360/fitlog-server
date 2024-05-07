package com.fitlog.server.domain.workout.dto;

import com.fitlog.server.domain.workout.entity.WorkoutRoutinePart;

import java.util.List;

public record WorkoutRoutinePartDto(
        Long id,
        Long workoutRoutineId,
        Long workoutPartId,
        String name,
        String description,
        String status,
        Integer order,
        Integer duration,
        List<WorkoutRoutineItemDto> workoutRoutineItemList
) {

    public static WorkoutRoutinePartDto toDto (WorkoutRoutinePart entity) {
        return new WorkoutRoutinePartDto(entity.getId(), entity.getWorkoutRoutine().getId(), entity.getWorkoutPartId(), entity.getName(), entity.getDescription(), entity.getStatus(), entity.getOrder(), entity.getDuration(), entity.getWorkoutRoutineItems().stream().map(WorkoutRoutineItemDto::toDto).toList());
    }
}
