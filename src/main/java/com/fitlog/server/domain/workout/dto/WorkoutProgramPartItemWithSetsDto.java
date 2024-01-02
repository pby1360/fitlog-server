package com.fitlog.server.domain.workout.dto;

import com.fitlog.server.domain.workout.entity.WorkoutProgramPartItem;
import com.fitlog.server.domain.workout.entity.WorkoutProgramPartItemSet;

import java.util.List;

public record WorkoutProgramPartItemWithSetsDto(Long id, String name, List<WorkoutProgramPartItemSetDto> WorkoutProgramPartItemSets) {

    public static WorkoutProgramPartItemWithSetsDto of (WorkoutProgramPartItemDto workoutProgramPartItem, List<WorkoutProgramPartItemSetDto> workoutProgramPartItemSets) {
        return new WorkoutProgramPartItemWithSetsDto(workoutProgramPartItem.id(), workoutProgramPartItem.name(), workoutProgramPartItemSets);
    }
}
