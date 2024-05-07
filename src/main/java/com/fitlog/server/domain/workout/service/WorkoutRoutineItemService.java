package com.fitlog.server.domain.workout.service;

import com.fitlog.server.domain.workout.entity.WorkoutRoutineItem;
import com.fitlog.server.domain.workout.repository.WorkoutRoutineItemRepository;
import com.fitlog.server.domain.workout.type.ProgressStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WorkoutRoutineItemService {
    private WorkoutRoutineItemRepository repository;

    public void startItem(Long id) {
        WorkoutRoutineItem item = repository.findById(id).orElseThrow();
        if (item.getStatus().equals(ProgressStatus.대기.getCode())) {
            item.start();
            repository.save(item);
        }
    }

    public void clearItem(Long id) {
        WorkoutRoutineItem item = repository.findById(id).orElseThrow();
        if (!item.getStatus().equals(ProgressStatus.대기.getCode())) {
            item.clear();
            repository.save(item);
        }
    }

    public void finishItem(Long id) {
        WorkoutRoutineItem item = repository.findById(id).orElseThrow();
        item.finish();
        repository.save(item);
    }
}
