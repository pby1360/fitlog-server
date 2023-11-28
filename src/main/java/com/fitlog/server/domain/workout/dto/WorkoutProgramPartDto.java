package com.fitlog.server.domain.workout.dto;

import com.fitlog.server.domain.workout.entity.WorkoutProgramPart;

import java.time.format.DateTimeFormatter;

public record WorkoutProgramPartDto(
        Long id
        , Long workoutProgramId
        , Long workoutPartId
        , String workoutPartName
        , String description
        , String createdDate
        , String modifiedDate
        , String memo
) {

    public static WorkoutProgramPartDto  toDto (WorkoutProgramPart entity) {
        WorkoutProgramPartDto dto = new WorkoutProgramPartDto(
                entity.getId()
                , entity.getWorkoutProgramId()
                , entity.getWorkoutPart().getId()
                , entity.getWorkoutPart().getName()
                , entity.getDescription()
                , entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                , entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                , entity.getMemo()
                );
        return dto;
    }
}
