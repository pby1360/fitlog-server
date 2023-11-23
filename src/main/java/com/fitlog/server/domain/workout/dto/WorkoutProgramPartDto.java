package com.fitlog.server.domain.workout.dto;

public record WorkoutProgramPartDto(Long id, Long workoutProgramId, Long workoutPartId, String workoutPartName, String description, String createdDate, String modifiedDate, String memo) {
}
