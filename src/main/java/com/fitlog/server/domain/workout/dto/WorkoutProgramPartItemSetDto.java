package com.fitlog.server.domain.workout.dto;

import com.fitlog.server.domain.workout.entity.WorkoutProgramPartItemSet;

import java.time.format.DateTimeFormatter;

public record WorkoutProgramPartItemSetDto(
        Long id,
        Long workoutProgramPartItemId,
        int order,
        int count,
        int restTime,
        String description,
        String createdDate,
        String modifiedDate,
        String memo
) {
    public static WorkoutProgramPartItemSetDto toDto (WorkoutProgramPartItemSet entity) {
        return new WorkoutProgramPartItemSetDto(
                entity.getId(),
                entity.getWorkoutProgramPartItemId(),
                entity.getOrder(),
                entity.getCount(),
                entity.getRestTime(),
                entity.getDescription(),
                entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                entity.getMemo()
        );
    }


}
