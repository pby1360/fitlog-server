package com.fitlog.server.domain.workout.repository;

import com.fitlog.server.domain.workout.entity.WorkoutRoutineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRoutineItemRepository extends JpaRepository<WorkoutRoutineItem, Long> {
}
