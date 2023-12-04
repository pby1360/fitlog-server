package com.fitlog.server.domain.workout.service;

import com.fitlog.server.domain.workout.repository.WorkoutProgramPartItemRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkoutProgramPartItemSetService {

    private WorkoutProgramPartItemRepository repository;

    public WorkoutProgramPartItemSetService(WorkoutProgramPartItemRepository repository) {
        this.repository = repository;
    }


}
