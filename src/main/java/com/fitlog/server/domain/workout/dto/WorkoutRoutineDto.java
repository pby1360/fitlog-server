package com.fitlog.server.domain.workout.dto;

import com.fitlog.server.domain.workout.entity.WorkoutRoutine;

import java.util.List;

public record WorkoutRoutineDto(
        Long id,
        Long userId,
        Long workoutProgramId,
        String name,
        String description,
        String status,
        Integer duration,
        List<WorkoutRoutinePartDto> workoutRoutinePartList
) {
    public static WorkoutRoutineDto toDto (WorkoutRoutine entity) {
        return new WorkoutRoutineDto (entity.getId(), entity.getUserId(),entity.getWorkoutProgramId(), entity.getName(), entity.getDescription(), entity.getStatus(), entity.getDuration(), null);
    }
}
