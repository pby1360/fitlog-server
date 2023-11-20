package com.fitlog.server.domain.workout.service;

import com.fitlog.server.domain.workout.dto.WorkoutPartItemDto;
import com.fitlog.server.domain.workout.entity.WorkoutPartItem;
import com.fitlog.server.domain.workout.repository.WorkoutPartItemRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkoutPartItemService {

    WorkoutPartItemRepository repository;

    public WorkoutPartItemService(WorkoutPartItemRepository repository) {
        this.repository = repository;
    }

    public WorkoutPartItemDto getWorkoutPartItemDetail(Long workoutPartItemId) {
        return repository.findById(workoutPartItemId).map(WorkoutPartItemDto::toDto).orElseThrow();
    }

    public void addWorkoutPartItem(WorkoutPartItemDto workoutPartItemDto) {
        WorkoutPartItem item = WorkoutPartItem.create(workoutPartItemDto);
        repository.save(item);
    }

    public void modifyWorkoutPartItem(WorkoutPartItemDto workoutPartItemDto) {
        WorkoutPartItem item = repository.findById(workoutPartItemDto.getId()).orElseThrow();
        item.modify(workoutPartItemDto);
        repository.save(item);
    }

    public void deleteWorkoutPartItem(Long workoutPartItemId) {
        repository.deleteById(workoutPartItemId);
    }
}
