package com.fitlog.server.domain.workout.service;

import com.fitlog.server.domain.workout.dto.WorkoutProgramPartDto;
import com.fitlog.server.domain.workout.entity.WorkoutProgramPart;
import com.fitlog.server.domain.workout.repository.WorkoutProgramPartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutProgramPartService {

    private WorkoutProgramPartRepository repository;

    public WorkoutProgramPartService(WorkoutProgramPartRepository repository) {
        this.repository = repository;
    }

    public void add (WorkoutProgramPartDto dto) {
        repository.save(WorkoutProgramPart.create(dto));
    }

    public WorkoutProgramPartDto detail (Long id) {
        return repository.findById(id).map(part -> WorkoutProgramPartDto.toDto(part)).orElseThrow();
    }

    public List<WorkoutProgramPartDto> list (Long programId) {
        return repository.findByWorkoutProgramId(programId)
                .stream()
                .map(part -> WorkoutProgramPartDto.toDto(part))
                .toList();
    }

    public void delete (Long id) {
        repository.deleteById(id);
    }

    public void modify (WorkoutProgramPartDto dto) {
        WorkoutProgramPart entity = repository.findById(dto.id()).orElseThrow();
        entity.modify(dto);
        repository.save(entity);
    }

    public void deleteAll(Long programId) {
        repository.deleteById(programId);
    }
}
