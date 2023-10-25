package com.fitlog.server.domain.workout.service;

import com.fitlog.server.domain.workout.dto.WorkoutPartItemDto;
import com.fitlog.server.domain.workout.entity.WorkoutPartItem;

import java.util.List;

public interface WorkoutPartItemService {
    void addWorkoutPartItem(WorkoutPartItemDto workoutPartItemDto);
}
