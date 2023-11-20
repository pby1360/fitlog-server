package com.fitlog.server.domain.workout.repository;

import com.fitlog.server.domain.workout.entity.WorkoutProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutProgramRepository extends JpaRepository<WorkoutProgram, Long> {
    List<WorkoutProgram> findByUserId(Long userId);
}
