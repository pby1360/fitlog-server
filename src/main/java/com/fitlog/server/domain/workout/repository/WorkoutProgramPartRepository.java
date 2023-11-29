package com.fitlog.server.domain.workout.repository;

import com.fitlog.server.domain.workout.entity.WorkoutProgramPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutProgramPartRepository extends JpaRepository<WorkoutProgramPart, Long> {

    List<WorkoutProgramPart> findByWorkoutProgramId(Long id);
    void deleteByWorkoutProgramId(Long programId);
}
