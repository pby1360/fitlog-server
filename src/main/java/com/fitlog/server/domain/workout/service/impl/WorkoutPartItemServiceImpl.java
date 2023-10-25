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
    public void addWorkoutPartItem(WorkoutPartItemDto workoutPartItemDto) {
        WorkoutPartItem item = WorkoutPartItem.create(workoutPartItemDto);
        repository.save(item);
    }
}
