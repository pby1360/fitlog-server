package com.fitlog.server.domain.workout.repository;

import com.fitlog.server.domain.workout.entity.WorkoutRoutine;
import com.fitlog.server.domain.workout.entity.WorkoutRoutinePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRoutinePartRepository extends JpaRepository<WorkoutRoutinePart, Long> {
}
