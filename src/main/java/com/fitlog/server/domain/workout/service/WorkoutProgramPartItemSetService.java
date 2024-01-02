package com.fitlog.server.domain.workout.service;

import com.fitlog.server.domain.workout.dto.WorkoutProgramPartItemSetDto;
import com.fitlog.server.domain.workout.entity.WorkoutProgramPartItemSet;
import com.fitlog.server.domain.workout.repository.WorkoutProgramPartItemSetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutProgramPartItemSetService {

    private WorkoutProgramPartItemSetRepository repository;

    public WorkoutProgramPartItemSetService(WorkoutProgramPartItemSetRepository repository) {
        this.repository = repository;
    }

    public WorkoutProgramPartItemSetDto detail(Long id) {
        return repository.findById(id)
                .map(item -> WorkoutProgramPartItemSetDto.toDto(item))
                .orElseThrow();
    }

    public List<WorkoutProgramPartItemSetDto> list(Long itemId) {
        return repository.findByWorkoutProgramPartItemId(itemId)
                .stream()
                .map(item -> WorkoutProgramPartItemSetDto.toDto(item))
                .toList();
    }

    public void add (WorkoutProgramPartItemSetDto dto) {
        WorkoutProgramPartItemSet newItem = WorkoutProgramPartItemSet.create(dto);
        newItem.setOrder(repository.findByWorkoutProgramPartItemId(dto.workoutProgramPartItemId()).size() + 1);
        repository.save(newItem);
    }

    public void modify (WorkoutProgramPartItemSetDto dto) {
        WorkoutProgramPartItemSet entity = repository.findById(dto.id()).orElseThrow();
        entity.modify(dto);
        repository.save(entity);
    }

    public void delete (Long id) {
        repository.deleteById(id);
    }

    public void deleteByWorkoutProgramPartItemId (Long workoutProgramPartItemId) {
        repository.deleteAllByWorkoutProgramPartItemId(workoutProgramPartItemId);

    }




}
