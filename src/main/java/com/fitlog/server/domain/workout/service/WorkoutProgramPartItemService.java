package com.fitlog.server.domain.workout.service;

import com.fitlog.server.domain.workout.dto.WorkoutProgramPartItemDto;
import com.fitlog.server.domain.workout.entity.WorkoutProgramPartItem;
import com.fitlog.server.domain.workout.repository.WorkoutProgramPartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutProgramPartItemService {

    private WorkoutProgramPartItemRepository repository;

    public WorkoutProgramPartItemService(WorkoutProgramPartItemRepository repository) {
        this.repository = repository;
    }

    public WorkoutProgramPartItemDto detail(Long id) {
        return repository.findById(id)
                .map(item -> WorkoutProgramPartItemDto.toDto(item))
                .orElseThrow();
    }

    public List<WorkoutProgramPartItemDto> list(Long partId) {
        return repository.findByWorkoutProgramPartId(partId)
                .stream()
                .map(item -> WorkoutProgramPartItemDto.toDto(item))
                .toList();
    }

    public void add (WorkoutProgramPartItemDto dto) {
        WorkoutProgramPartItem newItem = WorkoutProgramPartItem.create(dto);
        repository.save(newItem);
    }

    public void modify (WorkoutProgramPartItemDto dto) {
        WorkoutProgramPartItem entity = repository.findById(dto.id()).orElseThrow();
        entity.modify(dto);
        repository.save(entity);
    }

    public void delete (Long id) {
        repository.deleteById(id);
    }

}
