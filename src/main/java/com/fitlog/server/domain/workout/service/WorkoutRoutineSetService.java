package com.fitlog.server.domain.workout.service;

import com.fitlog.server.domain.workout.entity.WorkoutRoutineSet;
import com.fitlog.server.domain.workout.repository.WorkoutRoutineSetRepository;
import com.fitlog.server.domain.workout.type.ProgressStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutRoutineSetService {

    private WorkoutRoutineSetRepository repository;

    public WorkoutRoutineSetService(WorkoutRoutineSetRepository repository) {
        this.repository = repository;
    }

    public void set (Long itemId, int count) {
        List<WorkoutRoutineSet> list = repository.findByWorkoutRoutineItemId(itemId);
        list.stream().filter(set -> set.getStatus().equals(ProgressStatus.대기.getCode())).findFirst().ifPresent(set -> {
            set.complete(count);
            repository.save(set);
        });
    }

    public void clear (Long itemId) {
        List<WorkoutRoutineSet> list = repository.findByWorkoutRoutineItemId(itemId);
        list.stream().filter(set -> set.getStatus().equals(ProgressStatus.완료.getCode())).forEach(set -> {
            set.clear();
            repository.save(set);
        });
    }
}
