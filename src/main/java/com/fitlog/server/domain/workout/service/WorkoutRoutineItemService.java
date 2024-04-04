package com.fitlog.server.domain.workout.service;

import com.fitlog.server.domain.workout.entity.WorkoutRoutineItem;
import com.fitlog.server.domain.workout.repository.WorkoutRoutineItemRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class WorkoutRoutineItemService {
    private WorkoutRoutineItemRepository repository;

    public void start (Long id) {
        WorkoutRoutineItem item = repository.findById(id).orElseThrow();
        item.start();
        repository.save(item);
    }
}
