package com.fitlog.server.domain.workout.dto;

import com.fitlog.server.domain.workout.entity.WorkoutProgramPartItem;

import java.time.format.DateTimeFormatter;
import java.util.List;

public record WorkoutProgramPartItemDto(
        Long id,
        Long workoutProgramPartId,
        Long workoutPartItemId,
        int order,
//        int totalSet,
//        int totalCount,
        String description,
        String createdDate,
        String modifiedDate,
        String memo
//        , List<WorkoutProgramPartItemSetDto> setList
) {
    public static WorkoutProgramPartItemDto toDto (WorkoutProgramPartItem entity) {
        return new WorkoutProgramPartItemDto(
                entity.getId(),
                entity.getWorkoutProgramPartId(),
                entity.getWorkoutPartItemId(),
                entity.getOrder(),
//                entity.getSetList().size(),
//                entity.getSetList().stream().mapToInt(set -> set.getCount()).sum(),
                entity.getDescription(),
                entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                entity.getMemo()
//                entity.getSetList().stream().map(set -> WorkoutProgramPartItemSetDto.toDto(set)).toList()
        );
    }
}
