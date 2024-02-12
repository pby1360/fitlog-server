package com.fitlog.server.domain.workout.dto;

import java.util.List;

public record WorkoutRoutineItemDto(
        Long id,
        Long workoutRoutinePartId,
        Long workoutPartItemId,
        Long workoutProgramPartItemId,
        String name,
        String description,
        String status,
        Integer order,
        Integer duration,
        List<WorkoutRoutineSetDto> workoutRoutineSetList
) {
}
