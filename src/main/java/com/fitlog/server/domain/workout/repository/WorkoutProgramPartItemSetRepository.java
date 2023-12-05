package com.fitlog.server.domain.workout.repository;

import com.fitlog.server.domain.workout.entity.WorkoutProgramPartItemSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutProgramPartItemSetRepository extends JpaRepository<WorkoutProgramPartItemSet, Long> {

    List<WorkoutProgramPartItemSet> findByWorkoutProgramPartItemId(Long itemId);
}
