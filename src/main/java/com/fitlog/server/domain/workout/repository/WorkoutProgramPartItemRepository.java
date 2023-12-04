package com.fitlog.server.domain.workout.repository;

import com.fitlog.server.domain.workout.entity.WorkoutProgramPartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutProgramPartItemRepository extends JpaRepository<WorkoutProgramPartItem, Long> {

    List<WorkoutProgramPartItem> findByWorkoutProgramPartId(Long partId);
}
