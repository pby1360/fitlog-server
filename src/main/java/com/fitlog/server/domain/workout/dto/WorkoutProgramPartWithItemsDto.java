package com.fitlog.server.domain.workout.dto;

import com.fitlog.server.domain.workout.entity.WorkoutProgramPart;

import java.time.format.DateTimeFormatter;
import java.util.List;

public record WorkoutProgramPartWithItemsDto(
        Long id
        , Long workoutProgramId
        , Long workoutPartId
        , String workoutPartName
        , String description
        , String createdDate
        , String modifiedDate
        , String memo
        , List <WorkoutProgramPartItemDto> items
) {
    public static WorkoutProgramPartWithItemsDto of (WorkoutProgramPartDto part, List<WorkoutProgramPartItemDto> items) {
        return new WorkoutProgramPartWithItemsDto(part.id(), part.workoutProgramId(), part.workoutPartId(), part.workoutPartName(), part.description(), part.createdDate(), part.modifiedDate(), part.memo(), items);
    }
}
