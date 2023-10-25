package com.fitlog.server.domain.workout.repository;

import com.fitlog.server.domain.workout.entity.WorkoutPartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutPartItemRepository extends JpaRepository<WorkoutPartItem, Long> {
}
