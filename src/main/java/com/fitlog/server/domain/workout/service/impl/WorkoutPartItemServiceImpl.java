package com.fitlog.server.domain.workout.service.impl;

import com.fitlog.server.domain.workout.dto.WorkoutPartItemDto;
import com.fitlog.server.domain.workout.entity.WorkoutPartItem;
import com.fitlog.server.domain.workout.repository.WorkoutPartItemRepository;
import com.fitlog.server.domain.workout.service.WorkoutPartItemService;
import com.fitlog.server.domain.workout.service.WorkoutPartService;
import org.springframework.stereotype.Service;

@Service
public class WorkoutPartItemServiceImpl implements WorkoutPartItemService {

    WorkoutPartItemRepository repository;

    public WorkoutPartItemServiceImpl(WorkoutPartItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public WorkoutPartItemDto getWorkoutPartItemDetail(Long workoutPartItemId) {
        return repository.findById(workoutPartItemId).map(WorkoutPartItemDto::toDto).orElseThrow();
    }

    @Override
    public void addWorkoutPartItem(WorkoutPartItemDto workoutPartItemDto) {
        WorkoutPartItem item = WorkoutPartItem.create(workoutPartItemDto);
        repository.save(item);
    }

    @Override
    public void modifyWorkoutPartItem(WorkoutPartItemDto workoutPartItemDto) {
        WorkoutPartItem item = repository.findById(workoutPartItemDto.getId()).orElseThrow();
        item.modify(workoutPartItemDto);
        repository.save(item);
    }

    @Override
    public void deleteWorkoutPartItem(Long workoutPartItemId) {
        repository.deleteById(workoutPartItemId);
    }
}
