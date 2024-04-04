package com.fitlog.server.domain.workout.repository;

import com.fitlog.server.domain.workout.entity.WorkoutRoutineSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRoutineSetRepository extends JpaRepository<WorkoutRoutineSet, Long> {

    List<WorkoutRoutineSet> findByWorkoutRoutineItemId(Long workoutRoutineItemId);
}
