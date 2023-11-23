package com.fitlog.server.domain.workout.service;

import com.fitlog.server.domain.workout.dto.WorkoutProgramPartDto;
import com.fitlog.server.domain.workout.entity.WorkoutPart;
import com.fitlog.server.domain.workout.entity.WorkoutProgram;
import com.fitlog.server.domain.workout.entity.WorkoutProgramPart;
import com.fitlog.server.domain.workout.repository.WorkoutProgramPartRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkoutProgramPartService {

    private WorkoutProgramPartRepository repository;

    public WorkoutProgramPartService(WorkoutProgramPartRepository repository) {
        this.repository = repository;
    }

    public void add (WorkoutProgramPartDto dto) {
        repository.save(WorkoutProgramPart.create(dto));
    }

    public WorkoutProgramPart detail (Long id) {
        return repository.findById(id).map(part -> part).orElseThrow();
    }
}
