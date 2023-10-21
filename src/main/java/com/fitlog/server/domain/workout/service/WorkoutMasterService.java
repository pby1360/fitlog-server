package com.fitlog.server.domain.workout.service;

import com.fitlog.server.domain.workout.entity.WorkoutPart;

import java.util.List;

public interface WorkoutMasterService {
    void addWorkoutPart(WorkoutPart workoutPart);
    List<WorkoutPart> getWorkoutPartList(Long userId);
}
