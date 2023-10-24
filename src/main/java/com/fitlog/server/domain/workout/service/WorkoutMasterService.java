package com.fitlog.server.domain.workout.service;

import com.fitlog.server.domain.workout.dto.WorkoutPartDto;
import com.fitlog.server.domain.workout.entity.WorkoutPart;
import com.fitlog.server.domain.workout.entity.WorkoutPartItem;

import java.util.List;

public interface WorkoutMasterService {
    void addWorkoutPart(WorkoutPartDto workoutPartDto);
    List<WorkoutPartDto> getWorkoutPartList(Long userId);
    WorkoutPartDto getWorkoutPart(Long partId);
    List<WorkoutPartItem> getWorkoutPartItemList(Long partId);
}
