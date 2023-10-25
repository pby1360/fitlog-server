package com.fitlog.server.domain.workout.service;

import com.fitlog.server.domain.workout.dto.WorkoutPartDto;
import com.fitlog.server.domain.workout.dto.WorkoutPartItemDto;
import com.fitlog.server.domain.workout.entity.WorkoutPartItem;

import java.util.List;

public interface WorkoutPartService {
    void addWorkoutPart(WorkoutPartDto workoutPartDto);
    List<WorkoutPartDto> getWorkoutPartList(Long userId);
    WorkoutPartDto getWorkoutPart(Long partId);
    void modifyWorkoutPart(WorkoutPartDto workoutPartDto);
    void deleteWorkoutPart(Long workoutPartId);
    WorkoutPartDto getWorkoutPartWithItems(Long workoutPartId);
}
