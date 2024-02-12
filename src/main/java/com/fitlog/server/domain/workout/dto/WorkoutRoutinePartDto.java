package com.fitlog.server.domain.workout.dto;

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
}
