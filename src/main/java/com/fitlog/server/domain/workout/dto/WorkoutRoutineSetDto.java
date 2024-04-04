package com.fitlog.server.domain.workout.dto;

import com.fitlog.server.domain.workout.entity.WorkoutRoutineSet;

public record WorkoutRoutineSetDto(
        Long id,
        Long workoutRoutineItemId,
        String description,
        String status,
        Integer order,
        Integer duration,
        Integer targetCount,
        Integer count,
        Integer targetRestTime,
        Integer restTime
) {
    public static WorkoutRoutineSetDto toDto (WorkoutRoutineSet entity) {
        return new WorkoutRoutineSetDto(entity.getId(), entity.getWorkoutRoutineItem().getId(), entity.getDescription(), entity.getStatus(), entity.getOrder(), entity.getDuration(), entity.getTargetCount(), entity.getCount(), entity.getTargetRestTime(), entity.getRestTime());
    }
}
