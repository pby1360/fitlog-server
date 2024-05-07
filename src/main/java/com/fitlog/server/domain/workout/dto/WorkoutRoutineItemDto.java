package com.fitlog.server.domain.workout.dto;

import com.fitlog.server.domain.workout.entity.WorkoutRoutineItem;

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
    public static WorkoutRoutineItemDto toDto (WorkoutRoutineItem entity) {
        return new WorkoutRoutineItemDto(entity.getId(), entity.getWorkoutRoutinePart().getId(), entity.getWorkoutPartItemId(), null, entity.getName(), entity.getDescription(), entity.getStatus(), entity.getOrder(), entity.getDuration(), entity.getWorkoutRoutineSets().stream().map(WorkoutRoutineSetDto::toDto).toList());
    }
}
