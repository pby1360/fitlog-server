package com.fitlog.server.domain.workout.dto;

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
}
