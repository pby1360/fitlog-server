package com.fitlog.server.domain.workout.service;

import com.fitlog.server.domain.workout.dto.WorkoutProgramDto;
import com.fitlog.server.domain.workout.entity.WorkoutProgram;
import com.fitlog.server.domain.workout.repository.WorkoutProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WorkoutProgramService {
    private WorkoutProgramRepository repository;

    public WorkoutProgramService(WorkoutProgramRepository repository) {
        this.repository = repository;
    }

    public void add (WorkoutProgramDto dto) {
        WorkoutProgram newProgram = WorkoutProgram.create(dto);
        repository.save(newProgram);
    }

    public List<WorkoutProgramDto> list (Long userId) {
        return repository
                .findByUserId(userId)
                .stream()
                .map(entity -> WorkoutProgramDto.toDto(entity))
                .toList();
    }

    public WorkoutProgramDto detail (Long id) {
        return repository.findById(id).map(entity -> WorkoutProgramDto.toDto(entity)).orElseThrow();
    }

    public void modify (WorkoutProgramDto dto) {
        Optional<WorkoutProgram> program = repository.findById(dto.id());
        program.map(item -> repository.save(item.modify(dto))).orElseThrow();
    }

    public void delete (Long id) {
        Optional<WorkoutProgram> program = repository.findById(id);
        if (program.isPresent()) {
            repository.delete(program.get());
        } else {
            throw new NoSuchElementException();
        }
    }
}
